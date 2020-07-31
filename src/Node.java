import java.util.ArrayList;

public class Node {
    private int node, flag = 0;
    private ComplexNumber nodeVoltage;
    private static ArrayList<Node> allNodes = new ArrayList<>();

    public Node(int node, ComplexNumber nodeVoltage) {
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

    public ComplexNumber getNodeVoltage() {
        return nodeVoltage;
    }

    public static ArrayList<Node> getAllNodes() {
        return allNodes;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public void setNodeVoltage(ComplexNumber nodeVoltage) {
        this.nodeVoltage = nodeVoltage;
    }
}