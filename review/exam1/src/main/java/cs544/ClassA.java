package cs544;

public class ClassA {
    private String text;
    private ClassB classB;

    public ClassA(String text) {
        this.text = text;
        System.out.println("Class A constructor - text: " + text);
    }

    public void setClassB(ClassB classB) {
        this.classB = classB;
    }

    public void showText() {
        System.out.println("Class A text: " + text);
        classB.showText();
    }

    public void init() {
        System.out.println("Class A init - text: " + text);
    }

    public void destroy() {
        System.out.println("Class A destroy");
    }
}
