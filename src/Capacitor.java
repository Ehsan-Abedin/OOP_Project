import java.util.ArrayList;

public class Capacitor extends Element {
    private float capacitor;
    private static ArrayList<Capacitor> allCapacitors = new ArrayList<>();

    public Capacitor(ComplexNumber current, ComplexNumber voltage, ComplexNumber power, int node1, int node2, ComplexNumber node1Voltage, ComplexNumber node2Voltage, String name, float capacitor) {
        super(current, voltage, power, node1, node2, node1Voltage, node2Voltage, name);
        this.capacitor = capacitor;
    }

    public float getCapacitor() {
        return capacitor;
    }

    public static ArrayList<Capacitor> getAllCapacitors() {
        return allCapacitors;
    }

    public void setCapacitor(float capacitor) {
        this.capacitor = capacitor;
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
        power = voltage.multiply(voltage).multiply(capacitor).division((float) 2);
        return power;
    }
}
