package learning;

import base.NeuralNetwork;
import layers.Node;
import utils.GeneticAlgorithmUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class GeneticAlgorithm {
    private FitnessFunction scoring;

    // TODO save most recent scores

    private ArrayList<NeuralNetwork> neuralNetworkArrayList;
    private NeuralNetwork base;
    private int population;

    public GeneticAlgorithm(NeuralNetwork nn, int population, FitnessFunction scoring) {
        this.scoring = scoring;
        this.neuralNetworkArrayList = new ArrayList<>();
        this.base = nn;
        this.population = population;

        for (int i = 0; i < population; i++){
            neuralNetworkArrayList.add(this.base.clone());
        }
    }

    public ArrayList<NeuralNetwork> getNeuralNetworkArrayList() {
        return neuralNetworkArrayList;
    }

    public void setNeuralNetworkArrayList(ArrayList<NeuralNetwork> neuralNetworkArrayList) {
        this.neuralNetworkArrayList = neuralNetworkArrayList;
    }

    public NeuralNetwork getBase() {
        return base;
    }

    public void setBase(NeuralNetwork base) {
        this.base = base;
    }

    // mutate function
    public void mutateAll(float percent, float magnitude){
        NeuralNetwork mutatedNN;
        for (int i = 0; i < neuralNetworkArrayList.size(); i++){
            mutatedNN = GeneticAlgorithmUtil.mutate(neuralNetworkArrayList.get(i), percent, magnitude);
            this.neuralNetworkArrayList.set(i, mutatedNN);
        }
    }

    public ArrayList<Float> getAllScores(){
        ArrayList<Float> scores = new ArrayList<>();
        for (NeuralNetwork nn : this.neuralNetworkArrayList){
            scores.add(scoring.calculate(nn));
        }
        return scores;
    }

    public ArrayList<NeuralNetwork> getTopNNs(ArrayList<Float> scores, Float percent){
        ArrayList<NeuralNetwork> topNNs = new ArrayList<>();
        ArrayList<Float> topScores = new ArrayList<>();
        ArrayList<Float> sortedScores = new ArrayList<>(scores);
        Collections.sort(sortedScores, GeneticAlgorithmUtil.floatReverseComparator);

        int sampleScores = Math.round(scores.size() * percent);

        for (int i = 0; i < scores.size(); i++){
            if (scores.get(i) > percent){
                topNNs.add(this.neuralNetworkArrayList.get(i));
            }
        }
        return topNNs;
    }

    public NeuralNetwork evolve(float topPercentToKeep, float mutatePercent, float mutateMagnitude){

        ArrayList<Float> scores = getAllScores();
        ArrayList<NeuralNetwork> topNNs = getTopNNs(scores, .5f);
        NeuralNetwork newNetwork;

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

        }





//        NeuralNetwork child = GeneticAlgorithmUtil.crossover(nn1, nn2);
        NeuralNetwork child = null;
        return GeneticAlgorithmUtil.mutate(child, mutatePercent, mutateMagnitude);

    }

}
