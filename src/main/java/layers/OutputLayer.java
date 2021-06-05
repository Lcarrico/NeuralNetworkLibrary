package layers;

import java.util.ArrayList;

public interface OutputLayer<T, O> {
    O calc(T input);
    ArrayList<Node> getNodes();
    OutputLayer<T, O> clone();
    void setNodes(ArrayList<Node> nodes);
}
