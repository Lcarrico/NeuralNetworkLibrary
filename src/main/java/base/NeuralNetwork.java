package base;

import layers.Layer;
import layers.Node;
import layers.OutputLayer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NeuralNetwork<O> {

    String name;
    public NeuralNetwork(String name){
        this.name = name;
    }

    public NeuralNetwork(){
        this.name = "Neural Network";
    }

    private ArrayList<Layer<ArrayList<Float>>> layers = new ArrayList<>();

    private OutputLayer<ArrayList<Float>, O> outputLayer = null;

    public void addLayer(Layer<ArrayList<Float>> layer){
        layers.add(layer);
    }

    public void addLayers(Layer<ArrayList<Float>>... layers){
        for (Layer<ArrayList<Float>> layer : layers){
            addLayer(layer);
        }
    }

    public void setOutputLayer(OutputLayer<ArrayList<Float>, O> outputLayer){
        this.outputLayer = outputLayer;
    }

    public void setLayers(ArrayList<Layer<ArrayList<Float>>> layers){
        this.layers = layers;
    }

    public ArrayList<Layer<ArrayList<Float>>> getLayers(){
        return layers;
    }

    public ArrayList<ArrayList<Node>> getNodes(){
        ArrayList<ArrayList<Node>> nodes2DList = new ArrayList<>();

        for (Layer<ArrayList<Float>> layer: layers) {
            nodes2DList.add(layer.getNodes());
        }
        return nodes2DList;
    }

    public void setNodes(ArrayList<ArrayList<Node>> nodes){
        ArrayList<Layer<ArrayList<Float>>> layers = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++){
            Layer<ArrayList<Float>> tempLayer = this.layers.get(i);
            tempLayer.setNodes(nodes.get(i));
            layers.add(tempLayer);
        }

        this.setLayers(layers);
    }

    public O calc(ArrayList<Float> input) {
        ArrayList<Float> processingLayer = input;

        for (Layer<ArrayList<Float>> layer : this.layers) {
            processingLayer = (ArrayList<Float>)layer.calc(processingLayer);
        }

        return outputLayer.calc(processingLayer);
    }

    public ArrayList<O> calcAll(ArrayList<ArrayList<Float>> inputs){
        ArrayList<O> allCalculated = new ArrayList<>();

        for (ArrayList<Float> input : inputs){
            O calculated = calc(input);
            allCalculated.add(calculated);
        }
        return allCalculated;
    }

    public NeuralNetwork<ArrayList<Integer>> clone() {
        NeuralNetwork<ArrayList<Integer>> nn = new NeuralNetwork<>();
        for (Layer<ArrayList<Float>> layer: layers){
            nn.addLayer(layer.clone());
        }
        if (this.outputLayer != null)
            nn.setOutputLayer((OutputLayer<ArrayList<Float>, ArrayList<Integer>>) this.outputLayer.clone());
        return nn;
    }

    @Override
    public String toString() {
        return name + "\n" +
                "Layers =\n\t" + layers +
                ", \n\tOutput Layer = " + outputLayer;
    }

}
