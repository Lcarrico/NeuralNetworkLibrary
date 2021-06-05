package learning;

import base.NeuralNetwork;
import layers.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GeneticAlgorithmTests {

    @Test
    void evolveTest(){
        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<ArrayList<Integer>>("Test Network");
        nn.addLayer(new InputLayer());
        nn.addLayer(new HiddenLayer(2));
        nn.addLayer(new HiddenLayer(4));
        nn.addLayer(new HiddenLayer(8));
        nn.addLayer(new HiddenLayer(1));
        nn.setOutputLayer(new RoundedOutputLayer());

        ArrayList<Float> input1 = new ArrayList<>();
        input1.add(0f);
        input1.add(0f);

        ArrayList<Float> input2 = new ArrayList<>();
        input2.add(0f);
        input2.add(1f);

        ArrayList<Float> input3 = new ArrayList<>();
        input3.add(1f);
        input3.add(0f);

        ArrayList<Float> input4 = new ArrayList<>();
        input4.add(1f);
        input4.add(1f);

        GeneticAlgorithm ga = new GeneticAlgorithm(nn, 20, (NeuralNetwork<ArrayList<Integer>> neuralNetwork) -> {
            float score = 0f;

            score -= Math.abs(neuralNetwork.calc(input1).get(0));
            score -= Math.abs(neuralNetwork.calc(input2).get(0));
            score -= Math.abs(neuralNetwork.calc(input3).get(0));
            score -= Math.abs(neuralNetwork.calc(input4).get(0) - 1);

            return score;
        });

        for (int i = 0; i < 10; i++){
            ga.evolve(0.25f, 0.9f, 0.1f);
            System.out.println(ga.scores);
        }

        System.out.println("Best NN: " + ga.bestNeuralNetwork);
        System.out.println("0 0 -> " +  ga.bestNeuralNetwork.calc(input1));
        System.out.println("0 1 -> " +  ga.bestNeuralNetwork.calc(input2));
        System.out.println("1 0 -> " +  ga.bestNeuralNetwork.calc(input3));
        System.out.println("1 1 -> " +  ga.bestNeuralNetwork.calc(input4));

    }

    @Test
    void mutateAllTest(){
        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<ArrayList<Integer>>("Test Network");
        nn.addLayer(new InputLayer());
        nn.addLayer(new HiddenLayer(2));
        nn.addLayer(new HiddenLayer(1));
        nn.setOutputLayer(new HighestOutputLayer());
    }
}
