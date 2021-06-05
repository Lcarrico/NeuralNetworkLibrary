package layers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is the output layer for a neural network where the index of the highest value is returned.
 */
public class HighestOutputLayer implements OutputLayer<ArrayList<Float>, ArrayList<Integer>>{

    @Override
    public ArrayList<Integer> calc(ArrayList<Float> input) {
        ArrayList<Integer> sol = new ArrayList<>();
        Float max_value = Collections.max(input);
        int max_index = input.indexOf(max_value);

        sol.add(max_index);
        return sol;
    }

    @Override
    public ArrayList<Node> getNodes() {
        return new ArrayList<Node>();
    }

    @Override
    public void setNodes(ArrayList<Node> nodes) {

    }
    @Override
    public OutputLayer<ArrayList<Float>, ArrayList<Integer>> clone() {
        return new HighestOutputLayer();
    }

    @Override
    public String toString() {
        return "OutputLayer";
    }
}
