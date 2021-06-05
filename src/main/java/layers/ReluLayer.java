package layers;

import utils.NodeGenerator;

import java.util.ArrayList;

public class ReluLayer implements Layer<ArrayList<Float>>{

    String name;
    ArrayList<Node> nodes = new ArrayList<>();

    public ReluLayer(int numNodes){
        this.name = "ReLU Layer";
        generateNodes(numNodes);
    }

    @Override
    public ArrayList<Float> calc(ArrayList<Float> input) {
        ArrayList<Float> outputNums = new ArrayList<>();

        for (Node node : nodes){
            float total = 0;

            for (Float value : input){
                total += Math.max(0, node.getWeight() * value);
            }

            outputNums.add(total);
        }

        return outputNums;
    }

    @Override
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    @Override
    public Layer<ArrayList<Float>> clone() {
        ReluLayer clone = new ReluLayer(nodes.size());

        ArrayList<Node> nodeClones = new ArrayList<>();

        for (Node node : getNodes()){
            nodeClones.add(node.clone());
        }

        clone.setNodes(nodeClones);
        return clone;
    }

    @Override
    public void setNodes(ArrayList<Node> arrayList) {
        this.nodes = arrayList;
    }

    private void generateNodes(int numNodes){
        for (int i = 0; i < numNodes; i++){
            nodes.add(NodeGenerator.generate());
        }
    }
}
