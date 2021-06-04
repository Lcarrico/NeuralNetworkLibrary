package layers;

import java.util.ArrayList;
import java.util.Collections;

public class OutputLayer implements Layer<ArrayList<Float>>{


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
}
