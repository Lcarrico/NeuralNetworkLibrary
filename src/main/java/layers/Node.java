package layers;

/**
 * This class is the node generated for the layers within the neural network.
 */
public class Node {

    private float weight;

    /**
     * This constructor instantiates the node to the weight provided.
     * @param weight This parameter will become the weight of the node.
     */
    public Node(float weight){
        this.weight = weight;
    }

    /**
     * This method will return the provided input * weight.
     * @param input This parameter is the input to be multiplied by the weight.
     * @return float
     */
    public float calc(float input){
        return weight * input;
    }

    /**
     * This method will return the current weight of the node.
     * @return float
     */
    public float getWeight() {
        return weight;
    }

    /**
     * This method will set the current weight of the node to the weight provided.
     * @param weight This parameter will become the new weight of the node.
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public Node clone(){
        return new Node(getWeight());
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                '}';
    }
}
