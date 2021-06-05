package utils;

import layers.Node;

import java.util.ArrayList;

/**
 * This class will contain utility methods for the Node class
 * @see Node
 */
public class NodeUtil {

    /**
     * This method will <b>deep</b> clone a list of nodes.
     * @param nodes This parameter is the list of nodes to clone.
     * @return List of Node's
     */
    public static ArrayList<Node> cloneNodes(ArrayList<Node> nodes){
        ArrayList<Node> nodeClones = new ArrayList<>();
        for (Node node : nodes){
            nodeClones.add(node.clone());
        }
        return nodeClones;
    }

    /**
     * This method will compare two list of nodes and return whether the nodes from the first list
     * have the same weights as the nodes from the second list.
     * @param n This parameter is the first node to compare.
     * @param m This parameter is the second node to compare.
     * @return boolean
     */
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
