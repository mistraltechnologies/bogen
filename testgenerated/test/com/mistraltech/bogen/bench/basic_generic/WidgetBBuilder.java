package com.mistraltech.bogen.bench.basic_generic;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bogen.bench.model.WidgetB;

@Builds(WidgetB.class)
public final class WidgetBBuilder<P1> extends AbstractBuilder<WidgetB<P1>> {
    private final ValueContainer<P1> prop1Builder = new ValueContainer<>();

    private final ValueContainer<P1> prop2Builder = new ValueContainer<>();

    private WidgetBBuilder(final WidgetB<P1> template) {
        super();
        if (template != null) {
            prop1Builder.set(template.getProp1());
            prop2Builder.set(template.getProp2());
        }
    }

    public static <P1> WidgetBBuilder<P1> aWidgetB() {
        return new WidgetBBuilder<P1>(null);
    }

    public static <P1> WidgetBBuilder<P1> aWidgetBFrom(final WidgetB<P1> template) {
        return new WidgetBBuilder<P1>(template);
    }

    public WidgetBBuilder<P1> withProp1(final P1 prop1) {
        this.prop1Builder.set(prop1);
        return this;
    }

    public WidgetBBuilder<P1> withProp1(final Builder<? extends P1> prop1Builder) {
        this.prop1Builder.set(prop1Builder);
        return this;
    }

    public WidgetBBuilder<P1> withProp2(final P1 prop2) {
        this.prop2Builder.set(prop2);
        return this;
    }

    public WidgetBBuilder<P1> withProp2(final Builder<? extends P1> prop2Builder) {
        this.prop2Builder.set(prop2Builder);
        return this;
    }

    public BuilderProperty<P1> getProp1() {
        return prop1Builder;
    }

    public BuilderProperty<P1> getProp2() {
        return prop2Builder;
    }

    @Override
    protected WidgetB<P1> construct() {
        return new WidgetB<>(prop1Builder.value());
    }

    @Override
    protected void assign(final WidgetB<P1> instance) {
        super.assign(instance);
        instance.setProp2(prop2Builder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        prop1Builder.reset();
        prop2Builder.reset();
    }
}
