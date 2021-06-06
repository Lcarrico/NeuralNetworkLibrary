package utils;

import base.NeuralNetwork;
import layers.Layer;
import layers.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * This class will contain useful utility methods for the genetic algorithm class.
 * @see learning.GeneticAlgorithm
 */
public class GeneticAlgorithmUtil {

    /**
     * This method will turn a 2d array of nodes into a single 1d array of nodes.
     * @param nodes This parameter is the 2d array of nodes to convert.
     * @return List of Node's
     */
    public static ArrayList<Node> flattenNodes(ArrayList<ArrayList<Node>> nodes){
        ArrayList<Node> flattenedNodeList = new ArrayList<Node>();
        for (ArrayList<Node> nodeArray: nodes) {
            flattenedNodeList.addAll(nodeArray);
        }
        return flattenedNodeList;
    }

    /**
     * This method will grab a random sample from a list of nodes.
     * @param nodes This parameter is the list of nodes provided to sample from.
     * @param percent This parameter is the percent of nodes to sample.
     * @return List of Node's
     */
    public static ArrayList<Node> getSampleNodes(ArrayList<Node> nodes, Float percent){
        int sampleAmount = Math.round(nodes.size() * percent);
        Random random = new Random();
        ArrayList<Node> sampledNodes = new ArrayList<Node>();
        for (int i = 0; i < sampleAmount; i++){
            Node tempNode;
            do {
                int tempNodeIndex = random.nextInt(nodes.size());
                tempNode = nodes.get(tempNodeIndex);
            }
            while (sampledNodes.contains(tempNode));

            sampledNodes.add(tempNode);
        }
        return sampledNodes;
    }

    /**
     * This method will return a random sample of the indices of the list provided.
     * @param nodes This parameter will be the list of nodes provided to sample from.
     * @param percent This parameter is the percent of indices to sample.
     * @return List of Integer's
     */
    public static ArrayList<Integer> getSampleIndices(ArrayList<Node> nodes, Float percent){
        int sampleAmount = Math.round(nodes.size() * percent);
        Random random = new Random();
        ArrayList<Integer> sampledNodeIndices = new ArrayList<Integer>();
        for (int i = 0; i < sampleAmount; i++){
            int tempNodeIndex;
            do {
                tempNodeIndex = random.nextInt(nodes.size());
            }
            while (sampledNodeIndices.contains(tempNodeIndex));

            sampledNodeIndices.add(tempNodeIndex);
        }
        return sampledNodeIndices;
    }


    /**
     * This method will mutate or change the weights of the nodes of a Neural Network based on
     * the percent of nodes of a networks and the magnitude or the amount to change
     * the individual nodes by.
     * @param nn This parameter will be the neural network whose nodes will be mutated.
     * @param percent This parameter will be the percent of nodes to mutate.
     * @param magnitude This parameter will be the amount the nodes weights may change by.
     * @return NeuralNetwork
     */
    public static NeuralNetwork<ArrayList<Integer>> mutate(NeuralNetwork<ArrayList<Integer>> nn, float percent, float magnitude){
        ArrayList<ArrayList<Node>> nodes = nn.getNodes();
        ArrayList<Node> flattenedNodes = GeneticAlgorithmUtil.flattenNodes(nodes);
        ArrayList<Integer> sampledIndices = GeneticAlgorithmUtil.getSampleIndices(flattenedNodes, percent);

        Random random = new Random();

        ArrayList<ArrayList<Node>> mutatedNodes = new ArrayList<ArrayList<Node>>();

        Integer count = 0;
        for (ArrayList<Node> nodeArrayList : nodes) {
            ArrayList<Node> mutatedLayerNodes = new ArrayList<>();
            for (Node node : nodeArrayList) {
                float randomMagnitude = 0;
                if (sampledIndices.contains(count)){
                    randomMagnitude = 2 * magnitude * random.nextFloat() - magnitude;
                }
                mutatedLayerNodes.add(new Node(randomMagnitude + node.getWeight()));
                count++;
            }
            mutatedNodes.add(mutatedLayerNodes);
        }

        nn.setNodes(mutatedNodes);

        return nn;
    }

    /**
     * This method will create a child neural network from two neural networks
     * by randomly selecting nodes from both parents.
     * @param nn1 This parameter is the first neural network parent.
     * @param nn2 This parameter is the second neural network parent.
     * @return NeuralNetwork
     */
    public static NeuralNetwork<ArrayList<Integer>> crossover(NeuralNetwork<ArrayList<Integer>> nn1, NeuralNetwork<ArrayList<Integer>> nn2){
        NeuralNetwork<ArrayList<Integer>> child = nn1.clone();

        ArrayList<ArrayList<Node>> nn1Nodes = nn1.getNodes();
        ArrayList<ArrayList<Node>> nn2Nodes = nn2.getNodes();

        ArrayList<ArrayList<Node>> crossed = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < nn1Nodes.size(); i++){

            ArrayList<Node> nodeLayer = new ArrayList<>();
            for (int j = 0; j < nn1Nodes.get(i).size(); j++){
                Node curNode;

                if (random.nextInt() % 2 == 1){
                    curNode = nn1Nodes.get(i).get(j).clone();
                } else {
                    curNode = nn2Nodes.get(i).get(j).clone();
                }
                nodeLayer.add(curNode);
            }
            crossed.add(nodeLayer);
        }

        child.setNodes(crossed);

        return child;
    }

    /**
     * This comparator will be used to sort a list of floats descending.
     */
    public static Comparator<Float> floatReverseComparator = new Comparator<Float>() {
        @Override
        public int compare(Float o1, Float o2) {
            return o2.compareTo(o1);
        }
    };

}
