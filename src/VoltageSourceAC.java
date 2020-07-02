import java.util.ArrayList;

public class VoltageSourceAC extends Element {
    private float voltageDC, amplitude, frequency, phase;
    private static ArrayList<VoltageSourceAC> allVoltageSourceACs = new ArrayList<>();
    public VoltageSourceAC(float current, float voltage, float power, String node1, String node2, String name, float voltageDC, float amplitude, float frequency, float phase) {
        super(current, voltage, power, node1, node2, name);
        this.voltageDC = voltageDC;
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.phase = phase;
        allVoltageSourceACs.add(this);
    }

    public float getVoltageDC() {
        return voltageDC;
    }

    public float getAmplitude() {
        return amplitude;
    }

    public float getFrequency() {
        return frequency;
    }

    public float getPhase() {
        return phase;
    }

    public static ArrayList<VoltageSourceAC> getAllVoltageSourceACs() {
        return allVoltageSourceACs;
    }

    public void setVoltageDC(float voltageDC) {
        this.voltageDC = voltageDC;
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public void setPhase(float phase) {
        this.phase = phase;
    }
}
