public class VoltageControlVoltageSource {
    String controlNode1, ControlNode2;

    public VoltageControlVoltageSource(String controlNode1, String controlNode2) {
        this.controlNode1 = controlNode1;
        ControlNode2 = controlNode2;
    }

    public String getControlNode1() {
        return controlNode1;
    }

    public String getControlNode2() {
        return ControlNode2;
    }

    public void setControlNode1(String controlNode1) {
        this.controlNode1 = controlNode1;
    }

    public void setControlNode2(String controlNode2) {
        ControlNode2 = controlNode2;
    }
}
