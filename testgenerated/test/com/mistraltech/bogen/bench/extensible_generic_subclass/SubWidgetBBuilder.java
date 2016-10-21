package com.mistraltech.bogen.bench.extensible_generic_subclass;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bogen.bench.model.SubWidgetB;

@Builds(SubWidgetB.class)
public abstract class SubWidgetBBuilder<P1, R extends SubWidgetBBuilder<P1, R, T>, T extends SubWidgetB<P1>> extends WidgetBBuilder<P1, R, T> {
    private final ValueContainer<P1> prop1Builder = new ValueContainer<>();

    private final ValueContainer<P1> prop3Builder = new ValueContainer<>();

    private final ValueContainer<P1> prop4Builder = new ValueContainer<>();

    protected SubWidgetBBuilder(final T template) {
        super(template);
        if (template != null) {
            prop1Builder.set(template.getProp1());
            prop3Builder.set(template.getProp3());
            prop4Builder.set(template.getProp4());
        }
    }

    public static <P1> SubWidgetBBuilderType<P1> aSubWidgetB() {
        return new SubWidgetBBuilderType<P1>(null);
    }

    public static <P1> SubWidgetBBuilderType<P1> aSubWidgetBFrom(final SubWidgetB<P1> template) {
        return new SubWidgetBBuilderType<P1>(template);
    }

    @SuppressWarnings("unchecked")
    private R self() {
        return (R) this;
    }

    public R withProp1(final P1 prop1) {
        this.prop1Builder.set(prop1);
        return self();
    }

    public R withProp1(final Builder<? extends P1> prop1Builder) {
        this.prop1Builder.set(prop1Builder);
        return self();
    }

    public R withProp3(final P1 prop3) {
        this.prop3Builder.set(prop3);
        return self();
    }

    public R withProp3(final Builder<? extends P1> prop3Builder) {
        this.prop3Builder.set(prop3Builder);
        return self();
    }

    public R withProp4(final P1 prop4) {
        this.prop4Builder.set(prop4);
        return self();
    }

    public R withProp4(final Builder<? extends P1> prop4Builder) {
        this.prop4Builder.set(prop4Builder);
        return self();
    }

    public BuilderProperty<P1> getProp1() {
        return prop1Builder;
    }

    public BuilderProperty<P1> getProp3() {
        return prop3Builder;
    }

    public BuilderProperty<P1> getProp4() {
        return prop4Builder;
    }

    @Override
    protected void assign(final T instance) {
        super.assign(instance);
        instance.setProp4(prop4Builder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        prop1Builder.reset();
        prop3Builder.reset();
        prop4Builder.reset();
    }

    public static class SubWidgetBBuilderType<P1> extends SubWidgetBBuilder<P1, SubWidgetBBuilderType<P1>, SubWidgetB<P1>> {
        private SubWidgetBBuilderType(final SubWidgetB<P1> template) {
            super(template);
        }

        @Override
        protected SubWidgetB<P1> construct() {
            return new SubWidgetB<>(getProp1().value(), getProp3().value());
        }
    }
}
