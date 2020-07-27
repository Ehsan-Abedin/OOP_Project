import java.util.ArrayList;

public class Node {
    private int node, flag = 0;
    private float nodeVoltage;
    private static ArrayList<Node> allNodes = new ArrayList<>();

    public Node(int node, float nodeVoltage) {
        for (Node allNode : allNodes) {
            if (allNode.node == node) {
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            this.node = node;
            this.nodeVoltage = nodeVoltage;
            allNodes.add(this);
        }
    }

    public int getNode() {
        return node;
    }

    public float getNodeVoltage() {
        return nodeVoltage;
    }

    public static ArrayList<Node> getAllNodes() {
        return allNodes;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public void setNodeVoltage(float nodeVoltage) {
        this.nodeVoltage = nodeVoltage;
    }
}