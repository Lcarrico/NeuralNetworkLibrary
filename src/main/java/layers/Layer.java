package layers;

import java.util.ArrayList;

public interface Layer {
    Object calc(Object input);
    ArrayList<Node> getNodes();
}
