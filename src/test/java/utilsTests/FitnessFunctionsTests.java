package utilsTests;

import base.NeuralNetwork;
import layers.HiddenLayer;
import layers.InputLayer;
import layers.Node;
import layers.RoundedOutputLayer;
import learning.FitnessFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FitnessFunctions;
import utils.ListUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This test class is for the FitnessFunctions class.
 */
public class FitnessFunctionsTests {

    /**
     * This test is for FitnessFunctions.NegativeSumOfAbsoluteDifferences()
     * @see FitnessFunctions#NegativeSumOfAbsoluteDifferences(ArrayList, ArrayList) 
     */
    @Test
    void NegativeSumOfAbsoluteDifferences(){
        ArrayList<ArrayList<Node>> nnNodes = new ArrayList<>();
        ArrayList<Node> layerNodes;

        layerNodes = new ArrayList<>();
        nnNodes.add(new ArrayList<>());

        layerNodes.add(new Node(0.5f));
        layerNodes.add(new Node(0.5f));
        nnNodes.add(layerNodes);

        layerNodes = new ArrayList<>();
        layerNodes.add(new Node(1f));
        nnNodes.add(layerNodes);

        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<>("Test Network");

        nn.addLayer(new InputLayer());
        nn.addLayer(new HiddenLayer(2));
        nn.addLayer(new HiddenLayer(1));
        nn.setOutputLayer(new RoundedOutputLayer());
        nn.setNodes(nnNodes);

        System.out.println(nn);


        Float[][] data = {{0f, 0f}, {0f, 1f}, {1f, 0f}, {1f, 1f}};
        ArrayList<ArrayList<Float>> inputs = ListUtil.twoDArrayToList(data);

        Integer[][] outputs = {{0}, {1}, {1}, {0}};
        ArrayList<ArrayList<Integer>> expectedValues = ListUtil.twoDArrayToList(outputs);

        FitnessFunction scoring = FitnessFunctions.NegativeSumOfAbsoluteDifferences(inputs, expectedValues);

        System.out.println("Inputs: " + inputs);
        System.out.println("Expected Outputs: " + expectedValues);
        System.out.println("Actual Outputs: " + nn.calcAll(inputs));

        Float score = scoring.calculate(nn);

        System.out.println(score);

    }
}
