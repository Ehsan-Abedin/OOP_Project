public class Element {
    private float current, voltage, power;
    private String node1, node2, name;

    public Element(float current, float voltage, float power, String node1, String node2, String name) {
        this.current = current;
        this.voltage = voltage;
        this.power = power;
        this.node1 = node1;
        this.node2 = node2;
        this.name = name;
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

    public String getNode1() {
        return node1;
    }

    public String getNode2() {
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

    public void setNode1(String node1) {
        this.node1 = node1;
    }

    public void setNode2(String node2) {
        this.node2 = node2;
    }

    public void setName(String nmae) {
        this.name = name;
    }

    public float power (float current, float voltage){
        power = voltage*current;
        return power;
    }
}

