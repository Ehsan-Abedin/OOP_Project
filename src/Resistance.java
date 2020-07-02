import java.util.ArrayList;

public class Resistance extends Element {
    private float resistance;
    private static ArrayList<Resistance> allResistances = new ArrayList<>();

    public Resistance(float current, float voltage, float power, String node1, String node2,String name, float resistance) {
        super(current, voltage, power, node1, node2, name);
        this.resistance = resistance;
        allResistances.add(this);
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
}
