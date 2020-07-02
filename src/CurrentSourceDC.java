import java.util.ArrayList;

public class CurrentSourceDC extends Element {
    private static ArrayList<CurrentSourceDC> allCurrentSourceDCs = new ArrayList<>();
    public CurrentSourceDC(float current, float voltage, float power, String node1, String node2, String name) {
        super(current, voltage, power, node1, node2, name);
        allCurrentSourceDCs.add(this);
    }

    public static ArrayList<CurrentSourceDC> getAllCurrentSourceDCs() {
        return allCurrentSourceDCs;
    }
}
