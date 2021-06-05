package base;

import layers.Layer;
import layers.Node;
import layers.OutputLayer;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class is the base NeuralNetwork. It holds the computational layers, and the output layer.
 *
 * @param <O> This generic is the type for the final output of the Neural Network.
 * @see Layer
 * @see OutputLayer
 */
public class NeuralNetwork<O> {

    /**
     * This variable is the name of the Neural Network.
     */
    String name;

    /**
     * This constructor will instantiate the Neural Network with a name.
     *
     * @param name This parameter will be the name given to the Neural Network.
     */
    public NeuralNetwork(String name){
        this.name = name;
    }

    /**
     * This constructor will instantiate the Neural Network with a name of <i>Neural Network</i>.
     */
    public NeuralNetwork(){
        this.name = "Neural Network";
    }

    private ArrayList<Layer<ArrayList<Float>>> layers = new ArrayList<>();

    private OutputLayer<ArrayList<Float>, O> outputLayer = null;

    /**
     * This method will add a new layer to the existing list of layers.
     *
     * @param layer This parameter is the layer that will be added.
     * @see Layer
     */
    public void addLayer(Layer<ArrayList<Float>> layer){
        layers.add(layer);
    }

    /**
     * This method will add all layers provided to the existing list of layers.
     * @param layers This parameter is the list of layers provided to be added to the Neural Network's list of layers.
     * @see Layer
     */
    public void addLayers(Layer<ArrayList<Float>>... layers){
        for (Layer<ArrayList<Float>> layer : layers){
            addLayer(layer);
        }
    }


    /**
     * This method will set the Neural Network's current Output Layer to the OutputLayer provided.
     * @param outputLayer This parameter is the OutputLayer that will be set for the Neural Network.
     * @see OutputLayer
     */
    public void setOutputLayer(OutputLayer<ArrayList<Float>, O> outputLayer){
        this.outputLayer = outputLayer;
    }

    /**
     * This method will replace the Neural Network's current computational layers with the layers provided.
     * @param layers This parameter will be the replacement to the old layers.
     * @see Layer
     */
    public void setLayers(ArrayList<Layer<ArrayList<Float>>> layers){
        this.layers = layers;
    }

    /**
     * This method will return the current list of layers.
     * @return List of layers.
     * @see Layer
     */
    public ArrayList<Layer<ArrayList<Float>>> getLayers(){
        return layers;
    }

    /**
     * This method will return a two dimensional list of nodes belonging to the Neural Network.
     * Each nested list belongs to a separate layer within the Neural Network.
     *
     * @return 2D List of Nodes
     * @see Node
     */
    public ArrayList<ArrayList<Node>> getNodes(){
        ArrayList<ArrayList<Node>> nodes2DList = new ArrayList<>();

        for (Layer<ArrayList<Float>> layer: layers) {
            nodes2DList.add(layer.getNodes());
        }
        return nodes2DList;
    }

    /**
     * This method will replace the current two dimensional list of nodes with the one provided.
     * @param nodes This parameter will be the new 2D list of nodes for the Neural Network.
     * @see Node
     */
    public void setNodes(ArrayList<ArrayList<Node>> nodes){
        ArrayList<Layer<ArrayList<Float>>> layers = new ArrayList<>();

        for (int i = 0; i < nodes.size(); i++){
            Layer<ArrayList<Float>> tempLayer = this.layers.get(i);
            tempLayer.setNodes(nodes.get(i));
            layers.add(tempLayer);
        }

        this.setLayers(layers);
    }

    /**
     * This method will calculate the output of the Neural Network with it's current weights, given the input.
     *
     * @param input This parameter will be the input value for the Neural network's calculations.
     * @return Expected Output from the Neural Network
     */
    public O calc(ArrayList<Float> input) {
        ArrayList<Float> processingLayer = input;

        for (Layer<ArrayList<Float>> layer : this.layers) {
            processingLayer = (ArrayList<Float>)layer.calc(processingLayer);
        }

        return outputLayer.calc(processingLayer);
    }

    /**
     * This method will calculate the outputs of all the inputs passed through the Neural Network.
     * @param inputs This parameter will be the list of input values for the Neural Network's calculations.
     * @return List of Expected Outputs from the Neural Network
     */
    public ArrayList<O> calcAll(ArrayList<ArrayList<Float>> inputs){
        ArrayList<O> allCalculated = new ArrayList<>();

        for (ArrayList<Float> input : inputs){
            O calculated = calc(input);
            allCalculated.add(calculated);
        }
        return allCalculated;
    }

    /**
     * This method will create a <b>deep</b> copy of the current Neural Network.
     * @return NeuralNetwork
     */
    public NeuralNetwork<O> clone() {
        NeuralNetwork<O> nn = new NeuralNetwork<>();
        for (Layer<ArrayList<Float>> layer: layers){
            nn.addLayer(layer.clone());
        }
        if (this.outputLayer != null)
            nn.setOutputLayer((OutputLayer<ArrayList<Float>, O>) this.outputLayer.clone());
        return nn;
    }

    @Override
    public String toString() {
        return name + "\n" +
                "Layers =\n\t" + layers +
                ", \n\tOutput Layer = " + outputLayer;
    }

}
