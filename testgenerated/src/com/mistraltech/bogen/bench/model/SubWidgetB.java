package com.mistraltech.bogen.bench.model;

public class SubWidgetB<T> extends WidgetB<T> {
    private T prop3;
    private T prop4;

    public SubWidgetB(T prop1, T prop3) {
        super(prop1);
        this.prop3 = prop3;
    }

    public T getProp3() {
        return prop3;
    }

    public T getProp4() {
        return prop4;
    }

    public void setProp4(T prop4) {
        this.prop4 = prop4;
    }
}
