import java.util.ArrayList;

public class VoltageControlCurrentSource extends Element {
    private int controlNode1, controlNode2;
    private float gain;
    private static ArrayList<VoltageControlCurrentSource> allVoltageControlCurrentSources = new ArrayList<>();

    public VoltageControlCurrentSource(float current, float voltage, float power, int node1, int node2, String name, int controlNode1, int controlNode2, float gain) {
        super(current, voltage, power, node1, node2, name);
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
}