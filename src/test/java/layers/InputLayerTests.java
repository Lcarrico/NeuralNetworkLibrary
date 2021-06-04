package layers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class InputLayerTests {

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

    @Test
    void getNodes(){
        InputLayer inputLayer = new InputLayer();
        Assertions.assertEquals(0, inputLayer.getNodes().size());
    }
}
