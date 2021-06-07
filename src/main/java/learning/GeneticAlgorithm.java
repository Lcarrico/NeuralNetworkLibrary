package learning;

import base.NeuralNetwork;
import utils.GeneticAlgorithmUtil;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is the genetic algorithm base used for training a neural network structure.
 */
public class GeneticAlgorithm {

    /**
     * This Neural Network is the current network with the highest score.
     * @see NeuralNetwork
     */
    public NeuralNetwork<ArrayList<Integer>> bestNeuralNetwork;

    private final FitnessFunction scoring;

    /**
     * This list of floats will contain the current scores for the neural networks.
     * @see FitnessFunction
     */
    public ArrayList<Float> scores = new ArrayList<>();
    private ArrayList<NeuralNetwork<ArrayList<Integer>>> neuralNetworkArrayList;
    private NeuralNetwork<ArrayList<Integer>> base;
    private final int population;

    /**
     * This constructor will instantiate the Genetic algorithm with a base neural network, population, and a fitness function.
     * @param nn This parameter will be the base neural network that will be trained.
     * @param population This parameter will be the number of neural networks that may be created at one time for training.
     * @param scoring This parameter will be the function that determines and scores the fitness of a neural network.
     */
    public GeneticAlgorithm(NeuralNetwork<ArrayList<Integer>> nn, int population, FitnessFunction scoring) {
        this.scoring = scoring;
        this.neuralNetworkArrayList = new ArrayList<>();
        this.base = nn;
        this.population = population;

        for (int i = 0; i < population; i++){
            neuralNetworkArrayList.add(GeneticAlgorithmUtil.mutate(this.base.clone(), 1f, 1f));
        }

        this.bestNeuralNetwork = neuralNetworkArrayList.get(0);
    }


    /**
     * This method returns the current list of neural networks within the genetic algorithm.
     * @return List of NeuralNetwork's
     */
    public ArrayList<NeuralNetwork<ArrayList<Integer>>> getNeuralNetworkArrayList() {
        return neuralNetworkArrayList;
    }

    /**
     * This method will replace the current list of neural networks with the one provided.
     * @param neuralNetworkArrayList This parameter will become the new list of neural networks.
     */
    public void setNeuralNetworkArrayList(ArrayList<NeuralNetwork<ArrayList<Integer>>> neuralNetworkArrayList) {
        this.neuralNetworkArrayList = neuralNetworkArrayList;
    }

    /**
     * This method returns the original neural network provided during instantiation.
     * @return NeuralNetwork
     */
    public NeuralNetwork<ArrayList<Integer>> getBase() {
        return base;
    }

    /**
     * This method will replace the current base neural network provided at instantiation.
     * @param base This parameter will become the new base neural network.
     */
    public void setBase(NeuralNetwork<ArrayList<Integer>> base) {
        this.base = base;
    }

    /**
     * This method will mutate all the current neural networks. 
     * It will mutate rate amount of their nodes by the magnitude amount.
     * @param rate This parameter will be the rate nodes from each neural network to mutate.
     * @param magnitude This parameter will be the total amount that a node may mutate by.
     */
    public void mutateAll(float rate, float magnitude){
        NeuralNetwork<ArrayList<Integer>> mutatedNN;
        for (int i = 0; i < neuralNetworkArrayList.size(); i++){
            mutatedNN = GeneticAlgorithmUtil.mutate(neuralNetworkArrayList.get(i), rate, magnitude);
            this.neuralNetworkArrayList.set(i, mutatedNN);
        }
    }

    /**
     * This method will return the fitness score of all the current neural networks.
     * @return List of floats
     * @see FitnessFunction
     * @see utils.FitnessFunctions
     */
    public ArrayList<Float> getAllScores(){
        ArrayList<Float> scores = new ArrayList<>();
        for (NeuralNetwork<ArrayList<Integer>> nn : this.neuralNetworkArrayList){
            scores.add(scoring.calculate(nn));
        }

        this.scores = scores;

        return scores;
    }

    /**
     * This method will return the top percent of Neural Networks, given their score.
     * @param scores This parameter is the scores of the current Neural Network.
     * @param percent This parameter is the percent of the top neural networks to retrieve.
     * @return List of NeuralNetwork's
     */
    public ArrayList<NeuralNetwork<ArrayList<Integer>>> getTopNNs(ArrayList<Float> scores, Float percent){
        ArrayList<Float> scoresCopy = new ArrayList<>(scores);

        ArrayList<NeuralNetwork<ArrayList<Integer>>> topNNs = new ArrayList<>();
        ArrayList<Float> topScores = new ArrayList<>();
        ArrayList<Float> sortedScores = new ArrayList<>(scores);
        sortedScores.sort(GeneticAlgorithmUtil.floatReverseComparator);

        int sampleScoresCount = Math.round(scores.size() * percent);
        ArrayList<Float> sampleScores = new ArrayList<>();
        for (int i = 0; i < sampleScoresCount; i++){
            sampleScores.add(sortedScores.get(i));
        }

        ArrayList<Integer> sampleScoresIndices = new ArrayList<>();
        for (Float score: sampleScores){
            sampleScoresIndices.add(scoresCopy.indexOf(score));
            scoresCopy.remove(score);
        }

        for (int index: sampleScoresIndices){
            topNNs.add(this.neuralNetworkArrayList.get(index));
        }

        this.bestNeuralNetwork = topNNs.get(0);

        return topNNs;
    }

    /**
     * This method will go through the next generation of neural networks by evolving the current ones. 
     * This will
     * <ul>
     *     <li>Score and retrieve the top Neural Networks</li>
     *     <li>Breed the top Neural Networks to replenish the population</li>
     *     <li>Mutate the new generation of Neural Networks</li>
     * </ul>
     * @param topPercentToKeep This parameter is the percent of top parents breed for the next generation
     * @param mutatePercent This parameter will be the percent nodes from each neural network to mutate.
     * @param mutateMagnitude This parameter will be the total amount that a node may mutate by.
     * @see GeneticAlgorithmUtil#mutate(NeuralNetwork, float, float)
     * @see GeneticAlgorithmUtil#crossover(NeuralNetwork, NeuralNetwork)
     */
    public void evolve(float topPercentToKeep, float mutatePercent, float mutateMagnitude){

        ArrayList<Float> scores = getAllScores();
        ArrayList<NeuralNetwork<ArrayList<Integer>>> topNNs = getTopNNs(scores, topPercentToKeep);
        NeuralNetwork<ArrayList<Integer>> newNetwork;

        Random random = new Random();
        int randomIndex1;
        int randomIndex2;

        for (int i = 0; i < this.population; i++){
            randomIndex1 = random.nextInt(topNNs.size());

            do{
                randomIndex2 = random.nextInt(topNNs.size());
            } while (randomIndex1 == randomIndex2);

            newNetwork = GeneticAlgorithmUtil.crossover(topNNs.get(randomIndex1), topNNs.get(randomIndex2));
            newNetwork = GeneticAlgorithmUtil.mutate(newNetwork, mutatePercent, mutateMagnitude);

            this.neuralNetworkArrayList.set(i, newNetwork);
        }

    }

}
