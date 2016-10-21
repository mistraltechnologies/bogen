package com.mistraltech.bogen.bench.property_types;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bogen.bench.model.WidgetC;

@Builds(WidgetC.class)
public final class WidgetCBuilder extends AbstractBuilder<WidgetC> {
    private final ValueContainer<String> aBuilder = new ValueContainer<>();

    private final ValueContainer<Byte> bBuilder = new ValueContainer<>(byte.class);

    private final ValueContainer<Character> cBuilder = new ValueContainer<>(char.class);

    private final ValueContainer<Double> dBuilder = new ValueContainer<>(double.class);

    private final ValueContainer<Float> fBuilder = new ValueContainer<>(float.class);

    private final ValueContainer<Integer> iBuilder = new ValueContainer<>(int.class);

    private final ValueContainer<Short> sBuilder = new ValueContainer<>(short.class);

    private WidgetCBuilder(final WidgetC template) {
        super();
        if (template != null) {
            aBuilder.set(template.getA());
            bBuilder.set(template.getB());
            cBuilder.set(template.getC());
            dBuilder.set(template.getD());
            fBuilder.set(template.getF());
            iBuilder.set(template.getI());
            sBuilder.set(template.getS());
        }
    }

    public static WidgetCBuilder aWidgetC() {
        return new WidgetCBuilder(null);
    }

    public static WidgetCBuilder aWidgetCFrom(final WidgetC template) {
        return new WidgetCBuilder(template);
    }

    public WidgetCBuilder withA(final String a) {
        this.aBuilder.set(a);
        return this;
    }

    public WidgetCBuilder withA(final Builder<? extends String> aBuilder) {
        this.aBuilder.set(aBuilder);
        return this;
    }

    public WidgetCBuilder withB(final byte b) {
        this.bBuilder.set(b);
        return this;
    }

    public WidgetCBuilder withB(final Builder<? extends Byte> bBuilder) {
        this.bBuilder.set(bBuilder);
        return this;
    }

    public WidgetCBuilder withC(final char c) {
        this.cBuilder.set(c);
        return this;
    }

    public WidgetCBuilder withC(final Builder<? extends Character> cBuilder) {
        this.cBuilder.set(cBuilder);
        return this;
    }

    public WidgetCBuilder withD(final double d) {
        this.dBuilder.set(d);
        return this;
    }

    public WidgetCBuilder withD(final Builder<? extends Double> dBuilder) {
        this.dBuilder.set(dBuilder);
        return this;
    }

    public WidgetCBuilder withF(final float f) {
        this.fBuilder.set(f);
        return this;
    }

    public WidgetCBuilder withF(final Builder<? extends Float> fBuilder) {
        this.fBuilder.set(fBuilder);
        return this;
    }

    public WidgetCBuilder withI(final int i) {
        this.iBuilder.set(i);
        return this;
    }

    public WidgetCBuilder withI(final Builder<? extends Integer> iBuilder) {
        this.iBuilder.set(iBuilder);
        return this;
    }

    public WidgetCBuilder withS(final short s) {
        this.sBuilder.set(s);
        return this;
    }

    public WidgetCBuilder withS(final Builder<? extends Short> sBuilder) {
        this.sBuilder.set(sBuilder);
        return this;
    }

    public BuilderProperty<String> getA() {
        return aBuilder;
    }

    public BuilderProperty<Byte> getB() {
        return bBuilder;
    }

    public BuilderProperty<Character> getC() {
        return cBuilder;
    }

    public BuilderProperty<Double> getD() {
        return dBuilder;
    }

    public BuilderProperty<Float> getF() {
        return fBuilder;
    }

    public BuilderProperty<Integer> getI() {
        return iBuilder;
    }

    public BuilderProperty<Short> getS() {
        return sBuilder;
    }

    @Override
    protected WidgetC construct() {
        return new WidgetC(iBuilder.value(), bBuilder.value(), cBuilder.value(), sBuilder.value(), fBuilder.value(), dBuilder.value(), aBuilder.value());
    }

    @Override
    protected void assign(final WidgetC instance) {
        super.assign(instance);
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        aBuilder.reset();
        bBuilder.reset();
        cBuilder.reset();
        dBuilder.reset();
        fBuilder.reset();
        iBuilder.reset();
        sBuilder.reset();
    }
}
