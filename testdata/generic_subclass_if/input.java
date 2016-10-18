public class Widget<R, Q> extends BaseWidget<R> {
    private Q prop1;
    private Q prop2;

    public Widget(R superProp1, Q prop1) {
        super(superProp1);
        this.prop1 = prop1;
    }

    public Q getProp1() {
        return prop1;
    }

    public Q getProp2() {
        return prop2;
    }

    public void setProp2(Q prop2) {
        this.prop2 = prop2;
    }
}

class BaseWidget<P> {
    private P superProp1;
    private P superProp2;

    public BaseWidget(P superProp1) {
        this.superProp1 = superProp1;
    }

    public P getSuperProp1() {
        return superProp1;
    }

    public P getSuperProp2() {
        return superProp2;
    }

    public void setSuperProp2(P superProp2) {
        this.superProp2 = superProp2;
    }
}
