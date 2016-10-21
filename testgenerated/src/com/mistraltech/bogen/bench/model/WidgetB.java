package com.mistraltech.bogen.bench.model;

public class WidgetB<T> {
    private T prop1;
    private T prop2;

    public WidgetB(T prop1) {
        this.prop1 = prop1;
    }

    public T getProp1() {
        return prop1;
    }

    public T getProp2() {
        return prop2;
    }

    public void setProp2(T prop2) {
        this.prop2 = prop2;
    }
}

