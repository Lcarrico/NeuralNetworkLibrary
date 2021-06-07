package learningTests;

import base.NeuralNetwork;
import layers.HiddenLayer;
import layers.HighestOutputLayer;
import layers.InputLayer;
import layers.RoundedOutputLayer;
import learning.GeneticAlgorithm;
import org.junit.jupiter.api.Test;
import utils.FitnessFunctions;
import utils.ListUtil;

import java.util.ArrayList;

/**
 * This test class is for the GeneticAlgorithm class
 * @see GeneticAlgorithm
 */
public class GeneticAlgorithmTests {

    /**
     * This test is for GeneticAlgorithm.evolve()
     * @see GeneticAlgorithm#evolve(float, float, float) 
     */
    @Test
    void evolveTest(){
        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<ArrayList<Integer>>("Test Network");
        nn.addLayers(
                new InputLayer(),
                new HiddenLayer(2),
                new HiddenLayer(4),
                new HiddenLayer(8),
                new HiddenLayer(1)
        );
        nn.setOutputLayer(new RoundedOutputLayer());

        Float[][] inputsArray = {{0f, 0f}, {0f, 1f}, {1f, 0f}, {1f, 1f}};
        ArrayList<ArrayList<Float>> inputsList = ListUtil.twoDArrayToList(inputsArray);

        Integer[][] outputsArray = {{0}, {0}, {0}, {1}};
        ArrayList<ArrayList<Integer>> outputsList = ListUtil.twoDArrayToList(outputsArray);

        GeneticAlgorithm ga = new GeneticAlgorithm(nn, 20, FitnessFunctions.NegativeSumOfAbsoluteDifferences(inputsList, outputsList));

        System.out.println("Teaching the NN the AND expression.");
        System.out.println("Training now...");
        ga.evolve(0.2f, 0.5f, 0.5f);

        System.out.println("\nBest NN: " + ga.bestNeuralNetwork);

        System.out.println("\nOutputs from Best NN: ");
        for (ArrayList<Float> input : inputsList){
            System.out.println(input + " -> " + ga.bestNeuralNetwork.calc(input));
        }
    }

    /**
     * This test is for Genetic Algorithm.mutateAll()
     * @see GeneticAlgorithm#mutateAll(float, float) 
     */
    @Test
    void mutateAllTest(){
        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<ArrayList<Integer>>("Test Network");
        nn.addLayer(new InputLayer());
        nn.addLayer(new HiddenLayer(2));
        nn.addLayer(new HiddenLayer(1));
        nn.setOutputLayer(new HighestOutputLayer());
    }
}
