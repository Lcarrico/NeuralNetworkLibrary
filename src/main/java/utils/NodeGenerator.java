package utils;

import layers.Node;

import java.util.Random;

public class NodeGenerator {

    public static Node generate(){

        Random random = new Random();

        return new Node(random.nextFloat());
    }
}
