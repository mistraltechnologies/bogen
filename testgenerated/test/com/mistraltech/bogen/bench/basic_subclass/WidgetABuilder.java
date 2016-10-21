package com.mistraltech.bogen.bench.basic_subclass;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bogen.bench.model.WidgetA;

@Builds(WidgetA.class)
public abstract class WidgetABuilder<R extends WidgetABuilder<R, T>, T extends WidgetA> extends AbstractBuilder<T> {
    private final ValueContainer<String> prop1Builder = new ValueContainer<>();

    private final ValueContainer<String> prop2Builder = new ValueContainer<>();

    protected WidgetABuilder(final T template) {
        super();
        if (template != null) {
            prop1Builder.set(template.getProp1());
            prop2Builder.set(template.getProp2());
        }
    }

    public static WidgetABuilderType aWidgetA() {
        return new WidgetABuilderType(null);
    }

    public static WidgetABuilderType aWidgetAFrom(final WidgetA template) {
        return new WidgetABuilderType(template);
    }

    @SuppressWarnings("unchecked")
    private R self() {
        return (R) this;
    }

    public R withProp1(final String prop1) {
        this.prop1Builder.set(prop1);
        return self();
    }

    public R withProp1(final Builder<? extends String> prop1Builder) {
        this.prop1Builder.set(prop1Builder);
        return self();
    }

    public R withProp2(final String prop2) {
        this.prop2Builder.set(prop2);
        return self();
    }

    public R withProp2(final Builder<? extends String> prop2Builder) {
        this.prop2Builder.set(prop2Builder);
        return self();
    }

    public BuilderProperty<String> getProp1() {
        return prop1Builder;
    }

    public BuilderProperty<String> getProp2() {
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

    public static class WidgetABuilderType extends WidgetABuilder<WidgetABuilderType, WidgetA> {
        private WidgetABuilderType(final WidgetA template) {
            super(template);
        }

        @Override
        protected WidgetA construct() {
            return new WidgetA(getProp1().value());
        }
    }
}
