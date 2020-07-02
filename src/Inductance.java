import java.util.ArrayList;

public class Inductance extends Element {
    private float inductance;
    private static ArrayList<Inductance> allInductances = new ArrayList<>();

    public Inductance(float current, float voltage, float power, int node1, int node2, String name, float inductance) {
        super(current, voltage, power, node1, node2, name);
        this.inductance = inductance;
        allInductances.add(this);
    }

    public float getInductance() {
        return inductance;
    }

    public static ArrayList<Inductance> getAllInductances() {
        return allInductances;
    }

    public void setInductance(float inductance) {
        this.inductance = inductance;
    }
}
