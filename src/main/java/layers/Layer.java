package layers;

import java.util.ArrayList;

/**
 * This interface will be the individual computational layers for the Neural Network.
 * @param <T> This generic is the type for the inputs passed through this layer.
 */
public interface Layer<T> {

    /**
     * This method will return output of the layer after computational of the input.
     * @param input This parameter will be the input provided for computation.
     * @return Expected out after computation.
     */
    Object calc(T input);

    /**
     * This will return the list of nodes used in the computation by this layer.
     * @return List of Nodes
     */
    ArrayList<Node> getNodes();

    /**
     * This will generate a <b>deep</b> clone of this layer.
     * @return Layer
     */
    Layer<T> clone();

    /**
     * This method will replace the current list of nodes with the one provided.
     * @param nodes This parameter is the list of nodes to replace with.
     */
    void setNodes(ArrayList<Node> nodes);
}
