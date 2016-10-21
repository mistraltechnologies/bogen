package com.mistraltech.bogen.bench.model;

public class WidgetE {
    private int prop1;
    private int prop2;

    public WidgetE(String prop1) throws Exception {
        this.prop1 = Integer.parseInt(prop1);
    }

    public String getProp1() {
        return Integer.toString(prop1);
    }

    public String getProp2() {
        return Integer.toString(prop2);
    }

    public void setProp2(String prop2) throws Exception {
        this.prop2 = Integer.parseInt(prop2);
    }
}

