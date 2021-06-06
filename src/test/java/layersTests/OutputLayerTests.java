package layersTests;

import layers.HighestOutputLayer;
import layers.OutputLayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This test class is for the OutputLayer class.
 * @see OutputLayer
 */
public class OutputLayerTests {

    /**
     * This test is for OutputLayer.getNodes().
     * @see OutputLayer#getNodes()
     */
    @Test
    void getNodes(){
        OutputLayer<ArrayList<Float>, ArrayList<Integer>> outputLayer = new HighestOutputLayer();

        Assertions.assertEquals(0, outputLayer.getNodes().size());

    }

    /**
     * This test is for OutputLayer.calc().
     * @see OutputLayer#calc(Object)
     */
    @Test
    void calc(){
        OutputLayer<ArrayList<Float>, ArrayList<Integer>> outputLayer = new HighestOutputLayer();

        ArrayList<Float> input = new ArrayList<>();
        input.add(23.4f);
        input.add(12.5f);
        input.add(06.3f);
        input.add(200.4f);
        input.add(80.92f);
        input.add(113.24f);


        ArrayList<Integer> output = outputLayer.calc(input);

        Assertions.assertEquals(output.get(0), 3);
    }

}
