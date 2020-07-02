public class CurrentControlVoltageSource extends Element {
    String controlElement;
    float gain;

    public CurrentControlVoltageSource(float current, float voltage, float power, String node1, String node2, String name, String controlElement, float gain) {
        super(current, voltage, power, node1, node2, name);
        this.controlElement = controlElement;
        this.gain = gain;
    }

    public String getControlElement() {
        return controlElement;
    }

    public void setControlElement(String controlElement) {
        this.controlElement = controlElement;
    }
}
