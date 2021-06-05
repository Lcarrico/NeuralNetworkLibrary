package utils;

import base.NeuralNetwork;
import layers.Layer;
import layers.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GeneticAlgorithmUtil {
    public static ArrayList<Node> flattenNodes(ArrayList<ArrayList<Node>> nodes){
        ArrayList<Node> flattenedNodeList = new ArrayList<Node>();
        for (ArrayList<Node> nodeArray: nodes) {
            flattenedNodeList.addAll(nodeArray);
        }
        return flattenedNodeList;
    }

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


    public static NeuralNetwork mutate(NeuralNetwork nn, float percent, float magnitude){
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

    public static NeuralNetwork crossover(NeuralNetwork nn1, NeuralNetwork nn2){
        NeuralNetwork child = nn1.clone();

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

    public static Comparator<Float> floatReverseComparator = new Comparator<Float>() {
        @Override
        public int compare(Float o1, Float o2) {
            return o2.compareTo(o1);
        }
    };
}
