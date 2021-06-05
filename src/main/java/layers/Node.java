package layers;

public class Node {

    float weight;

    public Node(float weight){
        this.weight = weight;
    }

    public float calc(float input){
        return weight * input;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

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
