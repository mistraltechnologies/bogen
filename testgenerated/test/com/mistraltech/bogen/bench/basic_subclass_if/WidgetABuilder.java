package com.mistraltech.bogen.bench.basic_subclass_if;

import com.mistraltech.bogen.bench.model.WidgetA;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(WidgetA.class)
public interface WidgetABuilder<R extends WidgetABuilder<R, T>, T extends WidgetA> extends TwoPhaseBuilder<T> {
    @SuppressWarnings("unchecked")
    static WidgetABuilder<?, WidgetA> aWidgetA() {
        return builderOf(WidgetABuilder.class);
    }

    @SuppressWarnings("unchecked")
    static WidgetABuilder<?, WidgetA> aWidgetAFrom(final WidgetA template) {
        return builderOf(WidgetABuilder.class).from(template);
    }

    R from(WidgetA template);

    @ConstructorParameter(0)
    R withProp1(String prop1);

    R withProp1(Builder<? extends String> prop1Builder);

    R withProp2(String prop2);

    R withProp2(Builder<? extends String> prop2Builder);
}
