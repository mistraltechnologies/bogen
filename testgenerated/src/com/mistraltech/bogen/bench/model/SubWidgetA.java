package com.mistraltech.bogen.bench.model;

public class SubWidgetA extends WidgetA {
    private String prop3;

    public SubWidgetA(String prop1) {
        super(prop1);
    }

    public String getProp3() {
        return prop3;
    }

    public void setProp3(String prop3) {
        this.prop3 = prop3;
    }
}
