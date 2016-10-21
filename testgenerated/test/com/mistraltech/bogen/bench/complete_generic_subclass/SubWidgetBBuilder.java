package com.mistraltech.bogen.bench.complete_generic_subclass;

import com.mistraltech.bogen.bench.model.SubWidgetB;
import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(SubWidgetB.class)
public final class SubWidgetBBuilder<P1> extends AbstractBuilder<SubWidgetB<P1>> {
    private final ValueContainer<P1> prop1Builder = new ValueContainer<>();
    private final ValueContainer<P1> prop2Builder = new ValueContainer<>();
    private final ValueContainer<P1> prop3Builder = new ValueContainer<>();
    private final ValueContainer<P1> prop4Builder = new ValueContainer<>();

    private SubWidgetBBuilder(final SubWidgetB<P1> template) {
        super();
        if (template != null) {
            prop1Builder.set(template.getProp1());
            prop2Builder.set(template.getProp2());
            prop3Builder.set(template.getProp3());
            prop4Builder.set(template.getProp4());
        }
    }

    public static <P1> SubWidgetBBuilder<P1> aSubWidgetB() {
        return new SubWidgetBBuilder<P1>(null);
    }

    public static <P1> SubWidgetBBuilder<P1> aSubWidgetBFrom(final SubWidgetB<P1> template) {
        return new SubWidgetBBuilder<P1>(template);
    }

    public SubWidgetBBuilder<P1> withProp1(final P1 prop1) {
        this.prop1Builder.set(prop1);
        return this;
    }

    public SubWidgetBBuilder<P1> withProp1(final Builder<? extends P1> prop1Builder) {
        this.prop1Builder.set(prop1Builder);
        return this;
    }

    public SubWidgetBBuilder<P1> withProp2(final P1 prop2) {
        this.prop2Builder.set(prop2);
        return this;
    }

    public SubWidgetBBuilder<P1> withProp2(final Builder<? extends P1> prop2Builder) {
        this.prop2Builder.set(prop2Builder);
        return this;
    }

    public SubWidgetBBuilder<P1> withProp3(final P1 prop3) {
        this.prop3Builder.set(prop3);
        return this;
    }

    public SubWidgetBBuilder<P1> withProp3(final Builder<? extends P1> prop3Builder) {
        this.prop3Builder.set(prop3Builder);
        return this;
    }

    public SubWidgetBBuilder<P1> withProp4(final P1 prop4) {
        this.prop4Builder.set(prop4);
        return this;
    }

    public SubWidgetBBuilder<P1> withProp4(final Builder<? extends P1> prop4Builder) {
        this.prop4Builder.set(prop4Builder);
        return this;
    }

    public BuilderProperty<P1> getProp1() {
        return prop1Builder;
    }

    public BuilderProperty<P1> getProp2() {
        return prop2Builder;
    }

    public BuilderProperty<P1> getProp3() {
        return prop3Builder;
    }

    public BuilderProperty<P1> getProp4() {
        return prop4Builder;
    }

    @Override
    protected SubWidgetB<P1> construct() {
        return new SubWidgetB<>(prop1Builder.value(), prop3Builder.value());
    }

    @Override
    protected void assign(final SubWidgetB<P1> instance) {
        super.assign(instance);
        instance.setProp2(prop2Builder.value());
        instance.setProp4(prop4Builder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        prop1Builder.reset();
        prop2Builder.reset();
        prop3Builder.reset();
        prop4Builder.reset();
    }
}
