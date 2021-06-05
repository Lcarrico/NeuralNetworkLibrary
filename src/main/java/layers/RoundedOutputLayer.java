package layers;

import java.util.ArrayList;
import java.util.Collections;

public class RoundedOutputLayer implements OutputLayer<ArrayList<Float>, ArrayList<Integer>>{

    @Override
    public ArrayList<Integer> calc(ArrayList<Float> input) {
        ArrayList<Integer> sol = new ArrayList<>();
        sol.add(Math.round(input.get(0)));
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
        return new RoundedOutputLayer();
    }

    @Override
    public String toString() {
        return "RoundedOutputLayer";
    }
}
