package layers;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InputLayer implements Layer{

    @Override
    public ArrayList<Integer> calc(Object input) {
        return (ArrayList<Integer>) input;
    }

    @Override
    public ArrayList<Node> getNodes() {
        return new ArrayList<Node>();
    }
}
