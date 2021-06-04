package layers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class HiddenLayerTests {

    @Test
    void calc(){

        int numNodes = 5;
        HiddenLayer hiddenLayer = new HiddenLayer(numNodes);

        ArrayList<Float> input = new ArrayList<>();
        input.add(23.4f);
        input.add(12.5f);
        input.add(06.3f);

        ArrayList<Float> totals = hiddenLayer.calc(input);
        Assertions.assertEquals(numNodes, totals.size());

    }

    @Test
    void getNodes(){
        int numNodes = 5;
        HiddenLayer hiddenLayer = new HiddenLayer(numNodes);
        Assertions.assertEquals(numNodes, hiddenLayer.getNodes().size());
    }

}
