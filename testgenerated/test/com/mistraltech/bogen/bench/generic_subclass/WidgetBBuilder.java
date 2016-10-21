package com.mistraltech.bogen.bench.generic_subclass;

import com.mistraltech.bogen.bench.model.WidgetB;
import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(WidgetB.class)
public abstract class WidgetBBuilder<P1, R extends WidgetBBuilder<P1, R, T>, T extends WidgetB<P1>> extends AbstractBuilder<T> {
    private final ValueContainer<P1> prop1Builder = new ValueContainer<>();
    private final ValueContainer<P1> prop2Builder = new ValueContainer<>();

    protected WidgetBBuilder(final T template) {
        super();
        if (template != null) {
            prop1Builder.set(template.getProp1());
            prop2Builder.set(template.getProp2());
        }
    }

    public static <P1> WidgetBBuilderType<P1> aWidgetB() {
        return new WidgetBBuilderType<P1>(null);
    }

    public static <P1> WidgetBBuilderType<P1> aWidgetBFrom(final WidgetB<P1> template) {
        return new WidgetBBuilderType<P1>(template);
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

    public R withProp2(final P1 prop2) {
        this.prop2Builder.set(prop2);
        return self();
    }

    public R withProp2(final Builder<? extends P1> prop2Builder) {
        this.prop2Builder.set(prop2Builder);
        return self();
    }

    public BuilderProperty<P1> getProp1() {
        return prop1Builder;
    }

    public BuilderProperty<P1> getProp2() {
        return prop2Builder;
    }

    @Override
    protected void assign(final T instance) {
        super.assign(instance);
        instance.setProp2(prop2Builder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        prop1Builder.reset();
        prop2Builder.reset();
    }

    public static class WidgetBBuilderType<P1> extends WidgetBBuilder<P1, WidgetBBuilderType<P1>, WidgetB<P1>> {
        private WidgetBBuilderType(final WidgetB<P1> template) {
            super(template);
        }

        @Override
        protected WidgetB<P1> construct() {
            return new WidgetB<>(getProp1().value());
        }
    }
}
