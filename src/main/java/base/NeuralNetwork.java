package base;

import layers.Layer;
import layers.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NeuralNetwork {

    ArrayList<Layer> layers = new ArrayList<>();

    public void addLayer(Layer layer){
        layers.add(layer);
    }

    public ArrayList<ArrayList<Node>> getNodes(){
        ArrayList<ArrayList<Node>> nodes2DList = new ArrayList<>();

        for (Layer layer: layers) {
            nodes2DList.add(layer.getNodes());
        }
        return nodes2DList;
    }

    public ArrayList<Integer> score(Object input){
        ArrayList<Integer> output;

        for (Layer layer : layers){
            input = layer.calc(input);
        }

        try {
            output = (ArrayList<Integer>)input;
        }
        catch (ClassCastException c){
            throw new RuntimeException("Neural Network needs to output an ArrayList<Integer>");
        }


        return output;
    }

}
