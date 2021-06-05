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

    public NeuralNetwork<ArrayList<Integer>> bestNeuralNetwork;

    private final FitnessFunction scoring;
    public ArrayList<Float> scores = new ArrayList<>();
    private ArrayList<NeuralNetwork<ArrayList<Integer>>> neuralNetworkArrayList;
    private NeuralNetwork<ArrayList<Integer>> base;
    private final int population;

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

    public ArrayList<NeuralNetwork<ArrayList<Integer>>> getNeuralNetworkArrayList() {
        return neuralNetworkArrayList;
    }

    public void setNeuralNetworkArrayList(ArrayList<NeuralNetwork<ArrayList<Integer>>> neuralNetworkArrayList) {
        this.neuralNetworkArrayList = neuralNetworkArrayList;
    }

    public NeuralNetwork<ArrayList<Integer>> getBase() {
        return base;
    }

    public void setBase(NeuralNetwork<ArrayList<Integer>> base) {
        this.base = base;
    }

    // mutate function
    public void mutateAll(float percent, float magnitude){
        NeuralNetwork<ArrayList<Integer>> mutatedNN;
        for (int i = 0; i < neuralNetworkArrayList.size(); i++){
            mutatedNN = GeneticAlgorithmUtil.mutate(neuralNetworkArrayList.get(i), percent, magnitude);
            this.neuralNetworkArrayList.set(i, mutatedNN);
        }
    }

    public ArrayList<Float> getAllScores(){
        ArrayList<Float> scores = new ArrayList<>();
        for (NeuralNetwork<ArrayList<Integer>> nn : this.neuralNetworkArrayList){
            scores.add(scoring.calculate(nn));
        }

        this.scores = scores;

        return scores;
    }

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
