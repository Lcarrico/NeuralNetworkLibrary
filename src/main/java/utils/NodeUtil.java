package utils;

import layers.Node;

import java.util.ArrayList;

public class NodeUtil {

    public static ArrayList<Node> cloneNodes(ArrayList<Node> nodes){
        ArrayList<Node> nodeClones = new ArrayList<>();
        for (Node node : nodes){
            nodeClones.add(node.clone());
        }
        return nodeClones;
    }

    public static boolean compareNodeList(ArrayList<Node> n, ArrayList<Node> m){
        if (n.size() != m.size())
            return false;

        boolean same = true;

        for (int i = 0; i < n.size(); i++){
            if (n.get(i).getWeight() != m.get(i).getWeight()) {
                same = false;
                break;
            }
        }

        return same;
    }
}
