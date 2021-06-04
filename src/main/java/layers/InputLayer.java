package layers;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InputLayer implements Layer<ArrayList<Float>>{

    @Override
    public ArrayList<Float> calc(ArrayList<Float> input) {
        return input;
    }

    @Override
    public ArrayList<Node> getNodes() {
        return new ArrayList<Node>();
    }
}
