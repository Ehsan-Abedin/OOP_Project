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
}
