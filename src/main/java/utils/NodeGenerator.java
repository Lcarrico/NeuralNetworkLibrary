package utils;

import layers.Node;

import java.util.Random;

public class NodeGenerator {

    /**
     * This method will instantiate a node with a random weight.
     * @return Node
     */
    public static Node generate(){
        Random random = new Random();
        return new Node(random.nextFloat());
    }
}
