package layers;

import java.util.ArrayList;

public interface Layer<T> {
    Object calc(T input);
    ArrayList<Node> getNodes();
}
