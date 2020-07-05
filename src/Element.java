import java.util.ArrayList;

public class Element {
    private float current, voltage, power;
    private int node1, node2;
    private String name;
    private static ArrayList<Element> allElements = new ArrayList<>();

    public Element(float current, float voltage, float power, int node1, int node2, String name) {
        this.current = current;
        this.voltage = voltage;
        this.power = power;
        this.node1 = node1;
        this.node2 = node2;
        this.name = name;
    }

    public static ArrayList<Element> getAllElements() {
        allElements.addAll(Resistance.getAllResistances());
        allElements.addAll(Capacitor.getAllCapacitors());
        allElements.addAll(Inductance.getAllInductances());
        allElements.addAll(VoltageSourceDC.getAllVoltageSourceDCs());
        allElements.addAll(CurrentSourceDC.getAllCurrentSourceDCs());
        allElements.addAll(VoltageSourceAC.getAllVoltageSourceACs());
        allElements.addAll(CurrentSourceAC.getAllCurrentSourceACs());
        allElements.addAll(VoltageControlCurrentSource.getAllVoltageControlCurrentSources());
        allElements.addAll(VoltageControlVoltageSource.getAllVoltageControlVoltageSources());
        allElements.addAll(CurrentControlCurrentSource.getAllCurrentControlCurrentSources());
        allElements.addAll(CurrentControlVoltageSource.getAllCurrentControlVoltageSources());
        return allElements;
    }



    public float getCurrent() {
        return current;
    }

    public float getVoltage() {
        return voltage;
    }

    public float getPower() {
        return power;
    }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

    public String getName() {
        return name;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public void setNode1(int node1) {
        this.node1 = node1;
    }

    public void setNode2(int node2) {
        this.node2 = node2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float power (float current, float voltage){
        power = voltage*current;
        return power;
    }
}