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
    void addLayersTest(){

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
    void getNodesTest(){
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

    @Test
    void cloneTest(){
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
        nn.setOutputLayer(outputLayer);

        NeuralNetwork nnClone = nn.clone();

        Assertions.assertNotEquals(nn, nnClone);
    }

    @Test
    void toStringTest(){
        NeuralNetwork nn = new NeuralNetwork("Artificial Neural Network 1");

        InputLayer inputLayer = new InputLayer();
        HiddenLayer hiddenLayer1 = new HiddenLayer("Small Hidden Layer", 5);
        HiddenLayer hiddenLayer2 = new HiddenLayer("Large Hidden Layer", 7);
        HiddenLayer hiddenLayer3 = new HiddenLayer(6);
        OutputLayer outputLayer = new OutputLayer();

        nn.addLayer(inputLayer);
        nn.addLayer(hiddenLayer1);
        nn.addLayer(hiddenLayer2);
        nn.addLayer(hiddenLayer3);
        nn.setOutputLayer(outputLayer);

        System.out.println(nn);
    }

}
