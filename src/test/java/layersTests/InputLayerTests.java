package layersTests;

import layers.InputLayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This test class is for the InputLayer class.
 * @see InputLayer
 */
public class InputLayerTests {

    /**
     * This test is for InputLayer.calc().
     * @see InputLayer#calc(ArrayList)
     */
    @Test
    void calc(){
        InputLayer inputLayer = new InputLayer();

        ArrayList<Float> input = new ArrayList<>();
        input.add(23.4f);
        input.add(12.5f);
        input.add(06.3f);


        ArrayList<Float> output = inputLayer.calc(input);

        Assertions.assertNotNull(output);
        Assertions.assertEquals(3, output.size());

    }

    /**
     * This test is for InputLayer.getNodes().
     * @see InputLayer#getNodes()
     */
    @Test
    void getNodes(){
        InputLayer inputLayer = new InputLayer();
        Assertions.assertEquals(0, inputLayer.getNodes().size());
    }
}
