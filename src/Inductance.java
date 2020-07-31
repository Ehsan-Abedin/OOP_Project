import java.util.ArrayList;

public class Inductance extends Element {
    private float inductance;
    private static ArrayList<Inductance> allInductances = new ArrayList<>();

    public Inductance(ComplexNumber current, ComplexNumber voltage, ComplexNumber power, int node1, int node2, ComplexNumber node1Voltage, ComplexNumber node2Voltage, String name, float inductance) {
        super(current, voltage, power, node1, node2, node1Voltage, node2Voltage, name);
        this.inductance = inductance;
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
