import java.util.ArrayList;

public class Node {
    private int node, flag = 0, x , y;
    private ComplexNumber nodeVoltage;
    private static ArrayList<Node> allNodes = new ArrayList<>();

    public Node(int node, int x, int y, ComplexNumber nodeVoltage) {
        for (Node allNode : allNodes) {
            if (allNode.node == node) {
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            this.node = node;
            this.nodeVoltage = nodeVoltage;
            this.x = x;
            this.y = y;
            allNodes.add(this);
        }
    }

    public int getNode() {
        return node;
    }

    public ComplexNumber getNodeVoltage() {
        return nodeVoltage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}