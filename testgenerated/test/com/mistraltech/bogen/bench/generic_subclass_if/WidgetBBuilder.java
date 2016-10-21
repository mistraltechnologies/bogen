package com.mistraltech.bogen.bench.generic_subclass_if;

import com.mistraltech.bogen.bench.model.WidgetB;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(WidgetB.class)
public interface WidgetBBuilder<P1, R extends WidgetBBuilder<P1, R, T>, T extends WidgetB<P1>> extends TwoPhaseBuilder<T> {
    @SuppressWarnings("unchecked")
    static <P1> WidgetBBuilderType<P1> aWidgetB() {
        return builderOf(WidgetBBuilderType.class);
    }

    @SuppressWarnings("unchecked")
    static <P1> WidgetBBuilderType<P1> aWidgetBFrom(final WidgetB<P1> template) {
        return (WidgetBBuilderType<P1>) builderOf(WidgetBBuilderType.class).from(template);
    }

    R from(WidgetB<P1> template);

    @ConstructorParameter(0)
    R withProp1(P1 prop1);

    R withProp1(Builder<? extends P1> prop1Builder);

    R withProp2(P1 prop2);

    R withProp2(Builder<? extends P1> prop2Builder);

    BuilderProperty<P1> getProp1();

    BuilderProperty<P1> getProp2();

    @Builds(WidgetB.class)
    interface WidgetBBuilderType<P1> extends WidgetBBuilder<P1, WidgetBBuilderType<P1>, WidgetB<P1>> {
    }
}
