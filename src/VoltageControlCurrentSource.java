import java.util.ArrayList;

public class VoltageControlCurrentSource extends Element {
    private String controlNode1, controlNode2;
    private float gain;
    private static ArrayList<VoltageControlCurrentSource> allVoltageControlCurrentSources = new ArrayList<>();

    public VoltageControlCurrentSource(float current, float voltage, float power, String node1, String node2, String name, String controlNode1, String controlNode2, float gain) {
        super(current, voltage, power, node1, node2, name);
        this.controlNode1 = controlNode1;
        this.controlNode2 = controlNode2;
        this.gain = gain;
        allVoltageControlCurrentSources.add(this);
    }

    public String getControlNode1() {
        return controlNode1;
    }

    public String getControlNode2() {
        return controlNode2;
    }

    public float getGain() {
        return gain;
    }

    public static ArrayList<VoltageControlCurrentSource> getAllVoltageControlCurrentSources() {
        return allVoltageControlCurrentSources;
    }

    public void setControlNode1(String controlNode1) {
        this.controlNode1 = controlNode1;
    }

    public void setControlNode2(String controlNode2) {
        this.controlNode2 = controlNode2;
    }

    public void setGain(float gain) {
        this.gain = gain;
    }
}
