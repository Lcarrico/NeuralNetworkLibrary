package utils;

import layers.Node;

import java.util.Random;

/**
 * This class is intended used to generate a Node with a random weight.
 * @see Node
 */
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
