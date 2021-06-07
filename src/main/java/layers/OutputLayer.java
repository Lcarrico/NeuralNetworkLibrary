package layers;

import java.util.ArrayList;

/**
 * This interface will be the final layer or the output layer for the Neural Network.
 * Any final conversions or type change should be done here.
 *
 * @param <T> This generic is the type for the input of the output layer.
 * @param <O> This generic is the type for the output of the output layer.
 */
public interface OutputLayer<T, O> extends Layer<T> {
    O calc(T input);

    /**
     * This method will return a <b>deep</b> clone of the current layer.
     * @return OutputLayer
     */
    OutputLayer<T, O> clone();
}
