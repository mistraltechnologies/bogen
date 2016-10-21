package com.mistraltech.bogen.bench.setter_throws;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bogen.bench.model.WidgetE;

@Builds(WidgetE.class)
public final class WidgetEBuilder extends AbstractBuilder<WidgetE> {
    private final ValueContainer<String> prop1Builder = new ValueContainer<>();

    private final ValueContainer<String> prop2Builder = new ValueContainer<>();

    private WidgetEBuilder(final WidgetE template) {
        super();
        if (template != null) {
            prop1Builder.set(template.getProp1());
            prop2Builder.set(template.getProp2());
        }
    }

    public static WidgetEBuilder aWidgetE() {
        return new WidgetEBuilder(null);
    }

    public static WidgetEBuilder aWidgetEFrom(final WidgetE template) {
        return new WidgetEBuilder(template);
    }

    public WidgetEBuilder withProp1(final String prop1) {
        this.prop1Builder.set(prop1);
        return this;
    }

    public WidgetEBuilder withProp1(final Builder<? extends String> prop1Builder) {
        this.prop1Builder.set(prop1Builder);
        return this;
    }

    public WidgetEBuilder withProp2(final String prop2) {
        this.prop2Builder.set(prop2);
        return this;
    }

    public WidgetEBuilder withProp2(final Builder<? extends String> prop2Builder) {
        this.prop2Builder.set(prop2Builder);
        return this;
    }

    public BuilderProperty<String> getProp1() {
        return prop1Builder;
    }

    public BuilderProperty<String> getProp2() {
        return prop2Builder;
    }

    @Override
    protected WidgetE construct() {
        try {
            return new WidgetE(prop1Builder.value());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void assign(final WidgetE instance) {
        super.assign(instance);
        try {
            instance.setProp2(prop2Builder.value());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        prop1Builder.reset();
        prop2Builder.reset();
    }
}
