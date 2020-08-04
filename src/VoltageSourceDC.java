import java.util.ArrayList;

public class VoltageSourceDC extends Element {
    private static ArrayList<VoltageSourceDC> allVoltageSourceDCs = new ArrayList<>();

    public VoltageSourceDC(ComplexNumber current, ComplexNumber voltage, ComplexNumber power, int node1, int node2, ComplexNumber node1Voltage, ComplexNumber node2Voltage, String name) {
        super(current, voltage, power, node1, node2, node1Voltage, node2Voltage, name);
        allVoltageSourceDCs.add(this);
    }

    public static ArrayList<VoltageSourceDC> getAllVoltageSourceDCs() {
        return allVoltageSourceDCs;
    }
}
