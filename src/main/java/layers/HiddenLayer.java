package layers;

import utils.NodeGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HiddenLayer implements Layer<ArrayList<Float>>{

    String name;
    ArrayList<Node> nodes = new ArrayList<>();

    public HiddenLayer(int numNodes){
        this.name = "Hidden Layer";
        generateNodes(numNodes);
    }

    public HiddenLayer(String name, int numNodes){
        this.name = name;
        generateNodes(numNodes);
    }

    private void generateNodes(int numNodes){
        for (int i = 0; i < numNodes; i++){
            nodes.add(NodeGenerator.generate());
        }
    }

    @Override
    public ArrayList<Float> calc(ArrayList<Float> input) {
        ArrayList<Float> outputNums = new ArrayList<>();

        for (Node node : nodes){
            float total = 0;

            for (Float value : input){
                total += node.getWeight() * value;
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
    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public Layer<ArrayList<Float>> clone() {
        HiddenLayer clone = new HiddenLayer(nodes.size());

        ArrayList<Node> nodeClones = new ArrayList<>();

        for (Node node : getNodes()){
            nodeClones.add(node.clone());
        }

        clone.setNodes(nodeClones);
        return clone;
    }

    @Override
    public String toString() {
        return "\n\t" + this.name + "{" +
                "\n\t\tnodes=" + nodes +
                '}';
    }
}
