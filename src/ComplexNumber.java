public class ComplexNumber {
    private float x, y;
    public ComplexNumber(float real, float imaginary) {
        this.x = real;
        this.y = imaginary;
    }

    public double real() {
        return x;
    }

    public double imaginary() {
        return y;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public String toString() {
        return "{" + x + "," + y + "}";
    }

    public static ComplexNumber add(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.x + b.x, a.y + b.y);
    }

    public static ComplexNumber add(ComplexNumber a, float b) {
        return new ComplexNumber(a.x + b, a.y);
    }

    public ComplexNumber add(ComplexNumber a) {
        return new ComplexNumber(x + a.x, y + a.y);
    }

    public ComplexNumber add(float a) { return new ComplexNumber(x + a, y);}

    public static ComplexNumber subtract(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.x - b.x, a.y - b.y);
    }

    public static ComplexNumber subtract(ComplexNumber a, float b) {
        return new ComplexNumber(a.x - b, a.y);
    }

    public ComplexNumber subtract(ComplexNumber a) {
        return new ComplexNumber(x - a.x, y - a.y);
    }

    public ComplexNumber subtract(float a) { return new ComplexNumber(x - a, y);}

    public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.x * b.x - a.y * b.y, a.x * b.y + a.y * b.x);
    }

    public ComplexNumber multiply(ComplexNumber a, float b) {
        return new ComplexNumber(b * a.x, b * a.y);
    }

    public ComplexNumber multiply(ComplexNumber a) {
        return new ComplexNumber(x * a.x - y * a.y, x * a.y + y * a.x);
    }

    public ComplexNumber multiply(float a) {
        return new ComplexNumber(a * x,a * y);
    }

    public static ComplexNumber division(ComplexNumber a, ComplexNumber b) {
         ComplexNumber c = new ComplexNumber(b.x,-b.y);
         c.multiply(a);
         return new ComplexNumber(c.x / (b.x * b.x + b.y * b.y), c.y / (b.x * b.x + b.y * b.y));
    }

    public static ComplexNumber division(ComplexNumber a, float b) {
        return new ComplexNumber(a.x / b, a.y / b);
    }

    public ComplexNumber division(ComplexNumber a) {
        ComplexNumber c = new ComplexNumber(a.x,-a.y);
        this.multiply(a.x);
        return new ComplexNumber(c.x / (a.x * a.x + a.y * a.y), c.y / (a.x * a.x + a.y * a.y));
    }

    public ComplexNumber division(float a) {
        return new ComplexNumber(x / a, y / a);
    }
}
