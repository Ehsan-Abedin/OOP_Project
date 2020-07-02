public class Resistance extends Element {
    float resistance;

    public Resistance(float current, float voltage, float power, String node1, String node2, float resistance) {
        super(current, voltage, power, node1, node2);
        this.resistance = resistance;
    }

    public float getResistance() {
        return resistance;
    }

    public void setResistance(float resistance) {
        this.resistance = resistance;
    }
}
