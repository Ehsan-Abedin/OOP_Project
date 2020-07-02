public class VoltageSourceAC extends Element {
    float voltageDC, amplitude, frequency, phase;

    public VoltageSourceAC(float current, float voltage, float power, String node1, String node2, String name, float voltageDC, float amplitude, float frequency, float phase) {
        super(current, voltage, power, node1, node2, name);
        this.voltageDC = voltageDC;
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.phase = phase;
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
