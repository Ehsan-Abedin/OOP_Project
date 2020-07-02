public class VoltageControlVoltageSource extends Element {
    String controlNode1, controlNode2;
    float gain;

    public VoltageControlVoltageSource(float current, float voltage, float power, String node1, String node2, String name, String controlNode1, String controlNode2, float gain) {
        super(current, voltage, power, node1, node2, name);
        this.controlNode1 = controlNode1;
        this.controlNode2 = controlNode2;
        this.gain = gain;
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
