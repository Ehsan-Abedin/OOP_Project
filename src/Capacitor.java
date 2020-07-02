public class Capacitor extends Element {
    float capacitor;

    public Capacitor(float current, float voltage, float power, String node1, String node2, float capacitor) {
        super(current, voltage, power, node1, node2);
        this.capacitor = capacitor;
    }

    public float getCapacitor() {
        return capacitor;
    }

    public void setCapacitor(float capacitor) {
        this.capacitor = capacitor;
    }
}
