import java.util.ArrayList;

public class CurrentControlVoltageSource extends Element {
    private String controlElement;
    private float gain;
    private static ArrayList<CurrentControlVoltageSource> allCurrentControlVoltageSources = new ArrayList<>();

    public CurrentControlVoltageSource(ComplexNumber current, ComplexNumber voltage, ComplexNumber power, int node1, int node2, ComplexNumber node1Voltage, ComplexNumber node2Voltage, String name, String controlElement, float gain) {
        super(current, voltage, power, node1, node2, node1Voltage, node2Voltage, name);
        this.controlElement = controlElement;
        this.gain = gain;
        allCurrentControlVoltageSources.add(this);
    }

    public String getControlElement() {
        return controlElement;
    }

    public float getGain() {
        return gain;
    }

    public static ArrayList<CurrentControlVoltageSource> getAllCurrentControlVoltageSources() {
        return allCurrentControlVoltageSources;
    }

    public void setControlElement(String controlElement) {
        this.controlElement = controlElement;
    }

    public void setGain(float gain) {
        this.gain = gain;
    }
}
