package base;

import layers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.GeneticAlgorithmUtil;
import utils.ListUtil;
import utils.NodeUtil;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetworkTests {

    @Test
    void setNodesTest(){
        ArrayList<ArrayList<Node>> nnNodes = new ArrayList<>();
        ArrayList<Node> layerNodes;

        layerNodes = new ArrayList<>();
        nnNodes.add(new ArrayList<>());


        layerNodes.add(new Node(0.5f));
        layerNodes.add(new Node(0.5f));
        nnNodes.add(layerNodes);

        layerNodes = new ArrayList<>();
        layerNodes.add(new Node(1f));
        nnNodes.add(layerNodes);



        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<>("Test Network");

        nn.addLayer(new InputLayer());
        nn.addLayer(new HiddenLayer(2));
        nn.addLayer(new HiddenLayer(1));

        nn.setNodes(nnNodes);

        nnNodes = new ArrayList<>();

        layerNodes = new ArrayList<>();
        nnNodes.add(new ArrayList<>());


        layerNodes.add(new Node(0.2f));
        layerNodes.add(new Node(0.8f));
        nnNodes.add(layerNodes);

        layerNodes = new ArrayList<>();
        layerNodes.add(new Node(0.5f));

        nnNodes.add(layerNodes);
        nn.setNodes(nnNodes);

        ArrayList<Node> addedNodesFlattened = GeneticAlgorithmUtil.flattenNodes(nn.getNodes());
        ArrayList<Node> changedToNodes = GeneticAlgorithmUtil.flattenNodes(nnNodes);

        Assertions.assertTrue(NodeUtil.compareNodeList(addedNodesFlattened, changedToNodes));

    }


    @Test
    void calcTest(){
        ArrayList<ArrayList<Node>> nnNodes = new ArrayList<>();
        ArrayList<Node> layerNodes;

        layerNodes = new ArrayList<>();
        nnNodes.add(new ArrayList<>());

        layerNodes.add(new Node(0.5f));
        layerNodes.add(new Node(0.5f));
        nnNodes.add(layerNodes);

        layerNodes = new ArrayList<>();
        layerNodes.add(new Node(1f));
        nnNodes.add(layerNodes);

        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<>("Test Network");

        nn.addLayer(new InputLayer());
        nn.addLayer(new HiddenLayer(2));
        nn.addLayer(new HiddenLayer(1));
        nn.setOutputLayer(new RoundedOutputLayer());
        nn.setNodes(nnNodes);

        System.out.println(nn);

        ArrayList<Float> input = new ArrayList<>();
        input.add(1f);
        input.add(1f);

        int calculatedValue = nn.calc(input).get(0);
        Assertions.assertEquals(2, calculatedValue);
    }

    @Test
    void calcAllTest(){
        ArrayList<ArrayList<Node>> nnNodes = new ArrayList<>();
        ArrayList<Node> layerNodes;

        layerNodes = new ArrayList<>();
        nnNodes.add(new ArrayList<>());

        layerNodes.add(new Node(0.5f));
        layerNodes.add(new Node(0.5f));
        nnNodes.add(layerNodes);

        layerNodes = new ArrayList<>();
        layerNodes.add(new Node(1f));
        nnNodes.add(layerNodes);

        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<>("Test Network");

        nn.addLayer(new InputLayer());
        nn.addLayer(new HiddenLayer(2));
        nn.addLayer(new HiddenLayer(1));
        nn.setOutputLayer(new RoundedOutputLayer());
        nn.setNodes(nnNodes);

        System.out.println(nn);

        Float[][] data = {{0f, 0f}, {0f, 1f}, {1f, 0f}, {1f, 1f}};

        ArrayList<ArrayList<Float>> inputs = ListUtil.<Float>twoDArrayToList(data);

        ArrayList<ArrayList<Integer>> calculatedValues = nn.calcAll(inputs);

        System.out.println(inputs);
        System.out.println(calculatedValues);

        Integer[] expectedValues = {0, 1, 1, 2};

        for (int i = 0; i < calculatedValues.size(); i++){
            Assertions.assertEquals(expectedValues[i], calculatedValues.get(i).get(0));
        }

    }

    @Test
    void addLayersTest(){

        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<>();

        InputLayer inputLayer = new InputLayer();
        HiddenLayer hiddenLayer1 = new HiddenLayer(5);
        HiddenLayer hiddenLayer2 = new HiddenLayer(7);
        HiddenLayer hiddenLayer3 = new HiddenLayer(5);
        OutputLayer<ArrayList<Float>, ArrayList<Integer>> outputLayer = new HighestOutputLayer();

        nn.addLayer(inputLayer);
        nn.addLayer(hiddenLayer1);
        nn.addLayer(hiddenLayer2);
        nn.addLayer(hiddenLayer3);
        nn.setOutputLayer(outputLayer);

        Assertions.assertEquals(4, nn.getLayers().size());
    }

    @Test
    void getNodesTest(){
        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<>();

        InputLayer inputLayer = new InputLayer();
        HiddenLayer hiddenLayer1 = new HiddenLayer(5);
        HiddenLayer hiddenLayer2 = new HiddenLayer(7);
        HiddenLayer hiddenLayer3 = new HiddenLayer(5);
        OutputLayer<ArrayList<Float>, ArrayList<Integer>> outputLayer = new HighestOutputLayer();

        nn.addLayer(inputLayer);
        nn.addLayer(hiddenLayer1);
        nn.addLayer(hiddenLayer2);
        nn.addLayer(hiddenLayer3);
        nn.setOutputLayer(outputLayer);

        int countNodes = 0;
        for (ArrayList<Node> nodeList: nn.getNodes()){
            countNodes += nodeList.size();
        }

        Assertions.assertEquals(17, countNodes);

    }

    @Test
    void cloneTest(){
        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<>();

        InputLayer inputLayer = new InputLayer();
        HiddenLayer hiddenLayer1 = new HiddenLayer(5);
        HiddenLayer hiddenLayer2 = new HiddenLayer(7);
        HiddenLayer hiddenLayer3 = new HiddenLayer(5);
        OutputLayer<ArrayList<Float>, ArrayList<Integer>> outputLayer = new HighestOutputLayer();

        nn.addLayer(inputLayer);
        nn.addLayer(hiddenLayer1);
        nn.addLayer(hiddenLayer2);
        nn.addLayer(hiddenLayer3);
        nn.setOutputLayer(outputLayer);

        NeuralNetwork<ArrayList<Integer>> nnClone = nn.clone();

        Assertions.assertNotEquals(nn, nnClone);
    }

    @Test
    void toStringTest(){
        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<>();

        InputLayer inputLayer = new InputLayer();
        HiddenLayer hiddenLayer1 = new HiddenLayer(5);
        HiddenLayer hiddenLayer2 = new HiddenLayer(7);
        HiddenLayer hiddenLayer3 = new HiddenLayer(5);
        OutputLayer<ArrayList<Float>, ArrayList<Integer>> outputLayer = new HighestOutputLayer();

        nn.addLayer(inputLayer);
        nn.addLayer(hiddenLayer1);
        nn.addLayer(hiddenLayer2);
        nn.addLayer(hiddenLayer3);
        nn.setOutputLayer(outputLayer);

        System.out.println(nn);
    }

}
