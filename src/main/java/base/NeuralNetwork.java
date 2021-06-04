package base;

import layers.Layer;
import layers.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NeuralNetwork {

    private ArrayList<Layer> layers = new ArrayList<>();

    public void addLayer(Layer layer){
        layers.add(layer);
    }

    public ArrayList<Layer> getLayers(){
        return layers;
    }

    public ArrayList<ArrayList<Node>> getNodes(){
        ArrayList<ArrayList<Node>> nodes2DList = new ArrayList<>();

        for (Layer layer: layers) {
            nodes2DList.add(layer.getNodes());
        }
        return nodes2DList;
    }

    public ArrayList<Float> score(Object input){
        ArrayList<Float> output = (ArrayList<Float>) input;

        for (Layer layer : layers){
            input = layer.calc(input);
        }

        return output;
    }

}
