package layers;

import utils.NodeGenerator;

import java.util.ArrayList;

/**
 * Rectified Linear Unit layer. This will give the neural network an almost linear learning.
 */
public class ReluLayer implements Layer<ArrayList<Float>>{

    /** Name of the layer */
    String name;
    /** Nodes used for this layer */
    ArrayList<Node> nodes = new ArrayList<>();

    /** Constructor for setting up the ReLU layer */
    public ReluLayer(int numNodes){
        this.name = "ReLU Layer";
        generateNodes(numNodes);
    }

    /**
     * After some computational math, the output is returned
     * @param input This parameter will be the input provided for computation.
     * @return ArrayList of output values
     */
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

    /** Gets the nodes of this layer */
    @Override
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /** Performs a deep clones of this layer */
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

    /**
     * Set new layer nodes
     * @param arrayList List of new nodes
     */
    @Override
    public void setNodes(ArrayList<Node> arrayList) {
        this.nodes = arrayList;
    }

    /** Create the nodes to this layer based on the number of nodes to create */
    private void generateNodes(int numNodes){
        for (int i = 0; i < numNodes; i++){
            nodes.add(NodeGenerator.generate());
        }
    }
}
