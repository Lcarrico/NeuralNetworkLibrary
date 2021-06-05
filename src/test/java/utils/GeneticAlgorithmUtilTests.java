package utils;

import base.NeuralNetwork;
import layers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

public class GeneticAlgorithmUtilTests {
    @Test
    void getSampleNodes(){
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(0.22f));
        nodes.add(new Node(0.11f));
        nodes.add(new Node(0.43f));
        nodes.add(new Node(0.522f));
        nodes.add(new Node(0.1746f));
        nodes.add(new Node(0.82f));
        nodes.add(new Node(0.234f));
        nodes.add(new Node(0.1548f));
        nodes.add(new Node(0.573f));
        nodes.add(new Node(0.329548f));

        ArrayList<Node> sampleNodes = GeneticAlgorithmUtil.getSampleNodes(nodes, 0.2f);
        Assertions.assertEquals(2, sampleNodes.size());

    }

    @Test
    void flattenNodesTest(){
        ArrayList<Node> nodeList1 = new ArrayList<Node>();
        nodeList1.add(new Node(.001f));
        nodeList1.add(new Node(.002f));

        ArrayList<Node> nodeList2 = new ArrayList<Node>();
        nodeList2.add(new Node(.003f));
        nodeList2.add(new Node(.004f));


        ArrayList<ArrayList<Node>> fatList = new ArrayList<ArrayList<Node>>();
        fatList.add(nodeList1);
        fatList.add(nodeList2);

        ArrayList<Node> slimList = GeneticAlgorithmUtil.flattenNodes(fatList);
        Assertions.assertEquals(4, slimList.size());
    }

    @Test
    void mutate(){
        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<>();

        InputLayer inputLayer = new InputLayer();
        HiddenLayer hiddenLayer1 = new HiddenLayer(5);
        HiddenLayer hiddenLayer2 = new HiddenLayer(7);
        HiddenLayer hiddenLayer3 = new HiddenLayer(5);
        OutputLayer<ArrayList<Float>, ArrayList<Integer>> outputLayer = new HighestOutputLayer();

        nn.addLayer(inputLayer);
        nn.addLayer(hiddenLayer1);
        nn.addLayer(hiddenLayer2);
        nn.addLayer(hiddenLayer3);
        nn.setOutputLayer(outputLayer);

        ArrayList<Node> nnNodes = GeneticAlgorithmUtil.flattenNodes(nn.getNodes());

        NeuralNetwork<ArrayList<Integer>> mutatedNN = GeneticAlgorithmUtil.mutate(nn.clone(), 0.2f, 1);
        ArrayList<Node> mutatedNNnodes = GeneticAlgorithmUtil.flattenNodes((mutatedNN.getNodes()));

        Assertions.assertFalse(NodeUtil.compareNodeList(nnNodes, mutatedNNnodes));
    }

    @Test
    void crossover(){
        NeuralNetwork nn = new NeuralNetwork("Parent 1");
        InputLayer inputLayer = new InputLayer();
        HiddenLayer hiddenLayer1 = new HiddenLayer(5);
        HiddenLayer hiddenLayer2 = new HiddenLayer(7);
        HiddenLayer hiddenLayer3 = new HiddenLayer(5);
        OutputLayer<ArrayList<Float>, ArrayList<Integer>> outputLayer = new HighestOutputLayer();
        nn.addLayer(inputLayer);
        nn.addLayer(hiddenLayer1);
        nn.addLayer(hiddenLayer2);
        nn.addLayer(hiddenLayer3);
        nn.setOutputLayer(outputLayer);

        NeuralNetwork nn2 = new NeuralNetwork("Parent 2");
        inputLayer = new InputLayer();
        hiddenLayer1 = new HiddenLayer(5);
        hiddenLayer2 = new HiddenLayer(7);
        hiddenLayer3 = new HiddenLayer(5);
        outputLayer = new HighestOutputLayer();
        nn2.addLayer(inputLayer);
        nn2.addLayer(hiddenLayer1);
        nn2.addLayer(hiddenLayer2);
        nn2.addLayer(hiddenLayer3);
        nn2.setOutputLayer(outputLayer);

        NeuralNetwork child = GeneticAlgorithmUtil.crossover(nn, nn2);
        ArrayList<Node> childNodes = GeneticAlgorithmUtil.flattenNodes(child.getNodes());

        ArrayList<Node> nnNodes = GeneticAlgorithmUtil.flattenNodes(nn.getNodes());
        ArrayList<Node> nn2Nodes = GeneticAlgorithmUtil.flattenNodes(nn2.getNodes());

        Assertions.assertFalse(NodeUtil.compareNodeList(nnNodes, childNodes));
        Assertions.assertFalse(NodeUtil.compareNodeList(nn2Nodes, childNodes));
    }

    @Test
    void floatReverseComparatorTest(){
        ArrayList<Float> floats = new ArrayList<>();
        floats.add(3.14159f);
        floats.add(4.22f);
        floats.add(2.15f);

        Collections.sort(floats, GeneticAlgorithmUtil.floatReverseComparator);

        System.out.println(floats);
        Assertions.assertEquals(4.22f,floats.get(0));
        Assertions.assertEquals(3.14159f,floats.get(1));
        Assertions.assertEquals(2.15f,floats.get(2));
    }
}
