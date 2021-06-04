package base;

import layers.Layer;
import layers.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NeuralNetwork {

    private final ArrayList<Layer<ArrayList<Float>> layers = new ArrayList<>();

    private Layer<ArrayList<Float>> outputLayer = null;

    public void addLayer(Layer<ArrayList<Float> layer){
        layers.add(layer);
    }

    public void addOutputLayer(Layer<ArrayList<Float>> outputLayer){
        this.outputLayer = outputLayer;
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

    public ArrayList<Integer> calc(ArrayList<Float> input){
        Object output = input;

        for (Layer<ArrayList<Float>, ArrayList<?>> layer : layers){
            output = layer.calc(output);
        }

        return (ArrayList<Integer>) output;
    }

}
