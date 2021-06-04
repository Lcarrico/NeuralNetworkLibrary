package layers;

import utils.NodeGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HiddenLayer implements Layer<ArrayList<Float>>{

    ArrayList<Node> nodes = new ArrayList<>();

    public HiddenLayer(int numNodes){
        for (int i = 0; i < numNodes; i++){
            nodes.add(NodeGenerator.generate());
        }
    }

    @Override
    public ArrayList<Float> calc(ArrayList<Float> input) {
        ArrayList<Float> outputNums = new ArrayList<>();

        for (Node node : nodes){
            float total = 0;

            for (Float value : input){
                total += node.getWeight() * value;
            }

            outputNums.add(total);
        }

        return outputNums;
    }

    @Override
    public ArrayList<Node> getNodes() {
        return nodes;
    }
}
