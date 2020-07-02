public class Inductance extends Element {
    float inductance;

    public Inductance(float current, float voltage, float power, String node1, String node2, float inductance) {
        super(current, voltage, power, node1, node2);
        this.inductance = inductance;
    }

    public float getInductance() {
        return inductance;
    }

    public void setInductance(float inductance) {
        this.inductance = inductance;
    }
}
