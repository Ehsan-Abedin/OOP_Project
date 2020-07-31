import java.util.ArrayList;

public class CurrentSourceDC extends Element {
    private static ArrayList<CurrentSourceDC> allCurrentSourceDCs = new ArrayList<>();

    public CurrentSourceDC(ComplexNumber current, ComplexNumber voltage, ComplexNumber power, int node1, int node2, String name) {
        super(current, voltage, power, node1, node2, name);
        allCurrentSourceDCs.add(this);
    }

    public static ArrayList<CurrentSourceDC> getAllCurrentSourceDCs() {
        return allCurrentSourceDCs;
    }
}
