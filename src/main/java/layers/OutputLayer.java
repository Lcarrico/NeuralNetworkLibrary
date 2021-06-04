package layers;

import java.util.ArrayList;
import java.util.Collections;

public class OutputLayer implements Layer{


    @Override
    public ArrayList<Integer> calc(Object input) {
        ArrayList<Integer> sol = new ArrayList<>();
        ArrayList<Float> inputNums = (ArrayList<Float>) input;

        Float max_value = Collections.max(inputNums);
        int max_index = inputNums.indexOf(max_value);

        sol.add(max_index);

        return sol;

    }

    @Override
    public ArrayList<Node> getNodes() {
        return new ArrayList<Node>();
    }
}
