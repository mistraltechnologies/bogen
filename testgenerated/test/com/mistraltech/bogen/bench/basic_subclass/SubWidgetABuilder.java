package com.mistraltech.bogen.bench.basic_subclass;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bogen.bench.model.SubWidgetA;

@Builds(SubWidgetA.class)
public final class SubWidgetABuilder extends WidgetABuilder<SubWidgetABuilder, SubWidgetA> {
    private final ValueContainer<String> prop1Builder = new ValueContainer<>();

    private final ValueContainer<String> prop3Builder = new ValueContainer<>();

    private SubWidgetABuilder(final SubWidgetA template) {
        super(template);
        if (template != null) {
            prop1Builder.set(template.getProp1());
            prop3Builder.set(template.getProp3());
        }
    }

    public static SubWidgetABuilder aSubWidgetA() {
        return new SubWidgetABuilder(null);
    }

    public static SubWidgetABuilder aSubWidgetAFrom(final SubWidgetA template) {
        return new SubWidgetABuilder(template);
    }

    public SubWidgetABuilder withProp1(final String prop1) {
        this.prop1Builder.set(prop1);
        return this;
    }

    public SubWidgetABuilder withProp1(final Builder<? extends String> prop1Builder) {
        this.prop1Builder.set(prop1Builder);
        return this;
    }

    public SubWidgetABuilder withProp3(final String prop3) {
        this.prop3Builder.set(prop3);
        return this;
    }

    public SubWidgetABuilder withProp3(final Builder<? extends String> prop3Builder) {
        this.prop3Builder.set(prop3Builder);
        return this;
    }

    public BuilderProperty<String> getProp1() {
        return prop1Builder;
    }

    public BuilderProperty<String> getProp3() {
        return prop3Builder;
    }

    @Override
    protected SubWidgetA construct() {
        return new SubWidgetA(prop1Builder.value());
    }

    @Override
    protected void assign(final SubWidgetA instance) {
        super.assign(instance);
        instance.setProp3(prop3Builder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        prop1Builder.reset();
        prop3Builder.reset();
    }
}
