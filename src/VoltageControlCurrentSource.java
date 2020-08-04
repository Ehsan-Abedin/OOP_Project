import java.util.ArrayList;

public class VoltageControlCurrentSource extends Element {
    private int controlNode1, controlNode2;
    private float gain;
    private static ArrayList<VoltageControlCurrentSource> allVoltageControlCurrentSources = new ArrayList<>();

    public VoltageControlCurrentSource(ComplexNumber current, ComplexNumber voltage, ComplexNumber power, int node1, int node2, ComplexNumber node1Voltage, ComplexNumber node2Voltage, String name, int controlNode1, int controlNode2, float gain) {
        super(current, voltage, power, node1, node2, node1Voltage, node2Voltage, name);
        this.controlNode1 = controlNode1;
        this.controlNode2 = controlNode2;
        this.gain = gain;
        allVoltageControlCurrentSources.add(this);
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

    public static ArrayList<VoltageControlCurrentSource> getAllVoltageControlCurrentSources() {
        return allVoltageControlCurrentSources;
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

    public ComplexNumber voltage (ComplexNumber node1Voltage, ComplexNumber node2Voltage) {
        voltage = node1Voltage.subtract(node2Voltage);
        return voltage;
    }
}
