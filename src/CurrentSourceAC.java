public class CurrentSourceAC extends Element{
    float currentDC, amplitude, frequency, phase;

    public CurrentSourceAC(float current, float voltage, float power, String node1, String node2, String name, float currentDC, float amplitude, float frequency, float phase) {
        super(current, voltage, power, node1, node2, name);
        this.currentDC = currentDC;
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.phase = phase;
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
