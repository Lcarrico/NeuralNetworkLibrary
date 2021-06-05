package layers;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This is a base input layer and simply outputs the input values received.
 */
public class InputLayer implements Layer<ArrayList<Float>>{

    @Override
    public ArrayList<Float> calc(ArrayList<Float> input) {
        return (ArrayList<Float>) input;
    }

    @Override
    public ArrayList<Node> getNodes() {
        return new ArrayList<Node>();
    }

    @Override
    public void setNodes(ArrayList<Node> nodes) {

    }

    @Override
    public Layer<ArrayList<Float>> clone() {
        return new InputLayer();
    }

    @Override
    public String toString() {
        return "InputLayer";
    }
}
