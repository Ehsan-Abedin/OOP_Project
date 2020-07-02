public class VoltageControlVoltageSource extends Element {
    String controlNode1, ControlNode2;

    public VoltageControlVoltageSource(float current, float voltage, float power, String node1, String node2, String name, String controlNode1, String controlNode2) {
        super(current, voltage, power, node1, node2, name);
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
