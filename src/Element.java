import java.util.ArrayList;

public class Element {
    protected ComplexNumber current, voltage, power;
    private int node1, node2;
    private ComplexNumber node1Voltage, node2Voltage;
    private String name;
    private static ArrayList<Element> allElements = new ArrayList<>();

    public Element(ComplexNumber current, ComplexNumber voltage, ComplexNumber power, int node1, int node2, ComplexNumber node1Voltage, ComplexNumber node2Voltage, String name) {
        this.current = current;
        this.voltage = voltage;
        this.power = power;
        this.node1 = node1;
        this.node2 = node2;
        this.node1Voltage = node1Voltage;
        this.node2Voltage = node2Voltage;
        this.name = name;
    }

    public Element(ComplexNumber current, ComplexNumber voltage, ComplexNumber power, int node1, int node2, String name) {
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

    public static Element getElementByNode(Node node1, Node node2) {
        for (Element allElement : allElements) {
            if (((allElement.node1 == node1.getNode()) && (allElement.node2 == node2.getNode())) || ((allElement.node1 == node2.getNode()) && (allElement.node2 == node1.getNode())))
                return allElement;
        }
        return null;
    }

    public ComplexNumber getCurrent() {
        return current;
    }

    public ComplexNumber getVoltage() {
        return voltage;
    }

    public ComplexNumber getPower() {
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

    public ComplexNumber getNode1Voltage() {
        return node1Voltage;
    }

    public ComplexNumber getNode2Voltage() {
        return node2Voltage;
    }

    public void setCurrent(ComplexNumber current) {
        this.current = current;
    }

    public void setVoltage(ComplexNumber voltage) {
        this.voltage = voltage;
    }

    public void setPower(ComplexNumber power) {
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

    public void setNode1Voltage(ComplexNumber node1Voltage) {
        this.node1Voltage = node1Voltage;
    }

    public void setNode2Voltage(ComplexNumber node2Voltage) {
        this.node2Voltage = node2Voltage;
    }

    public ComplexNumber power (ComplexNumber current, ComplexNumber voltage){
        power = voltage.multiply(current);
        return power;
    }

    @Override
    public String toString() {
        return "Element{" +
                "current=" + current +
                ", voltage=" + voltage +
                ", power=" + power +
                ", node1=" + node1 +
                ", node2=" + node2 +
                ", node1Voltage=" + node1Voltage +
                ", node2Voltage=" + node2Voltage +
                ", name='" + name + '\'' +
                '}';
    }
}