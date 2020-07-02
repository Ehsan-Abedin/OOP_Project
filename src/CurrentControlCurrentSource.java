import java.util.ArrayList;

public class CurrentControlCurrentSource extends Element {
    private int controlElement;
    private float gain;
    private static ArrayList<CurrentControlCurrentSource> allCurrentControlCurrentSources = new ArrayList<>();

    public CurrentControlCurrentSource(float current, float voltage, float power, int node1, int node2, String name, int controlElement, float gain) {
        super(current, voltage, power, node1, node2, name);
        this.controlElement = controlElement;
        this.gain = gain;
        allCurrentControlCurrentSources.add(this);
    }

    public int getControlElement() {
        return controlElement;
    }

    public float getGain() {
        return gain;
    }

    public static ArrayList<CurrentControlCurrentSource> getAllCurrentControlCurrentSources() {
        return allCurrentControlCurrentSources;
    }

    public void setControlElement(int ControlElement) {
        this.controlElement = ControlElement;
    }

    public void setGain(float gain) {
        this.gain = gain;
    }
}
