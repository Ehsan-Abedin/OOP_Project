import java.util.ArrayList;

public class Capacitor extends Element {
    private float capacitor;
    private static ArrayList<Capacitor> allCapacitors = new ArrayList<>();

    public Capacitor(float current, float voltage, float power, int node1, int node2, String name, float capacitor) {
        super(current, voltage, power, node1, node2, name);
        this.capacitor = capacitor;
        allCapacitors.add(this);
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
}
