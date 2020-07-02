import java.util.ArrayList;

public class VoltageSourceDC extends Element {
    private static ArrayList<VoltageSourceDC> allVoltageSourceDCs = new ArrayList<>();
    public VoltageSourceDC(float current, float voltage, float power, int node1, int node2, String name) {
        super(current, voltage, power, node1, node2, name);
        allVoltageSourceDCs.add(this);
    }

    public static ArrayList<VoltageSourceDC> getAllVoltageSourceDCs() {
        return allVoltageSourceDCs;
    }
}
