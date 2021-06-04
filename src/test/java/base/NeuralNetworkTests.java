package base;

import layers.HiddenLayer;
import layers.InputLayer;
import layers.Node;
import layers.OutputLayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class NeuralNetworkTests {

    @Test
    void addLayers(){

        NeuralNetwork nn = new NeuralNetwork();

        InputLayer inputLayer = new InputLayer();
        HiddenLayer hiddenLayer1 = new HiddenLayer(5);
        HiddenLayer hiddenLayer2 = new HiddenLayer(7);
        HiddenLayer hiddenLayer3 = new HiddenLayer(5);
        OutputLayer outputLayer = new OutputLayer();

        nn.addLayer(inputLayer);
        nn.addLayer(hiddenLayer1);
        nn.addLayer(hiddenLayer2);
        nn.addLayer(hiddenLayer3);
        nn.addLayer(outputLayer);

        Assertions.assertEquals(5, nn.getLayers().size());
    }

    @Test
    void getNodes(){
        NeuralNetwork nn = new NeuralNetwork();

        InputLayer inputLayer = new InputLayer();
        HiddenLayer hiddenLayer1 = new HiddenLayer(5);
        HiddenLayer hiddenLayer2 = new HiddenLayer(7);
        HiddenLayer hiddenLayer3 = new HiddenLayer(5);
        OutputLayer outputLayer = new OutputLayer();

        nn.addLayer(inputLayer);
        nn.addLayer(hiddenLayer1);
        nn.addLayer(hiddenLayer2);
        nn.addLayer(hiddenLayer3);
        nn.addLayer(outputLayer);

        int countNodes = 0;
        for (ArrayList<Node> nodeList: nn.getNodes()){
            countNodes += nodeList.size();
        }

        Assertions.assertEquals(17, countNodes);

    }

}
