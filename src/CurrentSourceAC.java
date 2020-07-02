import java.util.ArrayList;

public class CurrentSourceAC extends Element{
    private float currentDC, amplitude, frequency, phase;
    private static ArrayList<CurrentSourceAC> allCurrentSourceACs = new ArrayList<>();
    public CurrentSourceAC(float current, float voltage, float power, String node1, String node2, String name, float currentDC, float amplitude, float frequency, float phase) {
        super(current, voltage, power, node1, node2, name);
        this.currentDC = currentDC;
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.phase = phase;
        allCurrentSourceACs.add(this);
    }

    public float getCurrentDC() {
        return currentDC;
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

    public static ArrayList<CurrentSourceAC> getAllCurrentSourceACs() {
        return allCurrentSourceACs;
    }

    public void setCurrentDC(float currentDC) {
        this.currentDC = currentDC;
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
