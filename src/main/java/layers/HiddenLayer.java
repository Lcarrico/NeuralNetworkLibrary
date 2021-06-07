package layers;

import utils.NodeGenerator;

import java.util.ArrayList;

/**
 * This class is the hidden layer for the Neural Network.
 * The majority of the weights and computation will be held and done within this layer.
 */
public class HiddenLayer implements Layer<ArrayList<Float>>{

    private final String name;
    private ArrayList<Node> nodes = new ArrayList<>();

    /**
     * This constructor instantiates the HiddenLayer with the provided number of nodes
     * and sets the Layer name to <i>Hidden Layer</i>.
     * Node weights will be randomly generated floats between 0 and 1.
     *
     * @param numNodes This parameter will be the number of nodes for this hidden layer.
     * @see Node
     */
    public HiddenLayer(int numNodes){
        this.name = "Hidden Layer";
        generateNodes(numNodes);
    }

    /**
     * This constructor instantiates the HiddenLayer with the provided number of nodes
     * and sets the Layer name to the name provided.
     * Node weights will be randomly generated floats between 0 and 1.
     *
     * @param name This parameter will be the name of this hidden layer.
     * @param numNodes This parameter will be the number of nodes for this hidden layer.
     */
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
