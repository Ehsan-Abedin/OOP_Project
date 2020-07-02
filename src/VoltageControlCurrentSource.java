public class VoltageControlCurrentSource {
    String controlNode1, controlNode2;

    public VoltageControlCurrentSource(String controlNode1, String controlNode2) {
        this.controlNode1 = controlNode1;
        this.controlNode2 = controlNode2;
    }

    public String getControlNode1() {
        return controlNode1;
    }

    public String getControlNode2() {
        return controlNode2;
    }

    public void setControlNode1(String controlNode1) {
        this.controlNode1 = controlNode1;
    }

    public void setControlNode2(String controlNode2) {
        this.controlNode2 = controlNode2;
    }
}
