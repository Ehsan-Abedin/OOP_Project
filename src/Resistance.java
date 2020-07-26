import java.util.ArrayList;

public class Resistance extends Element {
    private float resistance;
    private static ArrayList<Resistance> allResistances = new ArrayList<>();

    public Resistance(float current, float voltage, float power, int node1, int node2, float node1Voltage, float node2Voltage, String name, float resistance) {
        super(current, voltage, power, node1, node2, node1Voltage, node2Voltage, name);
        this.resistance = resistance;
    }

    public float getResistance() {
        return resistance;
    }

    public static ArrayList<Resistance> getAllResistances() {
        return allResistances;
    }

    public void setResistance(float resistance) {
        this.resistance = resistance;
    }

    public float voltage (float node1Voltage, float node2Voltage) {
        voltage = node1Voltage - node2Voltage;
        return voltage;
    }
}
