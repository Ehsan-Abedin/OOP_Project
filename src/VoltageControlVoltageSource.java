import java.util.ArrayList;

public class VoltageControlVoltageSource extends Element {
    private int controlNode1, controlNode2;
    private float gain;
    private static ArrayList<VoltageControlVoltageSource> allVoltageControlVoltageSources = new ArrayList<>();

    public VoltageControlVoltageSource(float current, float voltage, float power, int node1, int node2, String name, int controlNode1, int controlNode2, float gain) {
        super(current, voltage, power, node1, node2, name);
        this.controlNode1 = controlNode1;
        this.controlNode2 = controlNode2;
        this.gain = gain;
        allVoltageControlVoltageSources.add(this);
    }

    public VoltageControlVoltageSource(float current, float voltage, float power, int node1, int node2, float node1Voltage, float node2Voltage, String name, int controlNode1, int controlNode2, float gain) {
        super(current, voltage, power, node1, node2, node1Voltage, node2Voltage, name);
        this.controlNode1 = controlNode1;
        this.controlNode2 = controlNode2;
        this.gain = gain;
    }

    public int getControlNode1() {
        return controlNode1;
    }

    public int getControlNode2() {
        return controlNode2;
    }

    public float getGain() {
        return gain;
    }

    public static ArrayList<VoltageControlVoltageSource> getAllVoltageControlVoltageSources() {
        return allVoltageControlVoltageSources;
    }

    public void setControlNode1(int controlNode1) {
        this.controlNode1 = controlNode1;
    }

    public void setControlNode2(int controlNode2) {
        this.controlNode2 = controlNode2;
    }

    public void setGain(float gain) {
        this.gain = gain;
    }
}
