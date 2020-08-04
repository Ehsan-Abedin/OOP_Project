import java.util.ArrayList;

public class Inductance extends Element {
    private float inductance;
    private static ArrayList<Inductance> allInductances = new ArrayList<>();

    public Inductance(ComplexNumber current, ComplexNumber voltage, ComplexNumber power, int node1, int node2, ComplexNumber node1Voltage, ComplexNumber node2Voltage, String name, float inductance) {
        super(current, voltage, power, node1, node2, node1Voltage, node2Voltage, name);
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

    public ComplexNumber voltage (ComplexNumber node1Voltage, ComplexNumber node2Voltage) {
        voltage = node1Voltage.subtract(node2Voltage);
        return voltage;
    }

    public ComplexNumber current (ComplexNumber power, ComplexNumber voltage) {
        current = power.division(voltage);
        return current;
    }

    public ComplexNumber power (ComplexNumber voltage) {
        power = voltage.multiply(voltage).multiply(2).division(inductance);
        return power;
    }
}
