public class Widget extends BaseWidget {
    private String prop1;
    private String prop2;

    public Widget(int superProp1, String prop1) {
        super(superProp1);
        this.prop1 = prop1;
    }

    public String getProp1() {
        return prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }
}

class BaseWidget {
    private int superProp1;
    private int superProp2;

    public BaseWidget(int superProp1) {
        this.superProp1 = superProp1;
    }

    public int getSuperProp1() {
        return superProp1;
    }

    public int getSuperProp2() {
        return superProp2;
    }

    public void setSuperProp2(int superProp2) {
        this.superProp2 = superProp2;
    }
}