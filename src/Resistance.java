//import com.sun.org.apache.xml.internal.security.c14n.implementations.Canonicalizer11_OmitComments;

import java.util.ArrayList;

public class Resistance extends Element {
    private float resistance;
    private static ArrayList<Resistance> allResistances = new ArrayList<>();

    public Resistance(ComplexNumber current, ComplexNumber voltage, ComplexNumber power, int node1, int node2, ComplexNumber node1Voltage, ComplexNumber node2Voltage, String name, float resistance) {
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

    public ComplexNumber voltage (ComplexNumber node1Voltage, ComplexNumber node2Voltage) {
        voltage = node1Voltage.subtract(node2Voltage);
        return voltage;
    }

    public ComplexNumber current (ComplexNumber voltage) {
        current = voltage.division(resistance);
        return current;
    }
}
