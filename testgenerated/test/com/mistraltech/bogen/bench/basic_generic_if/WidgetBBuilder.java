package com.mistraltech.bogen.bench.basic_generic_if;

import com.mistraltech.bogen.bench.model.WidgetB;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(WidgetB.class)
public interface WidgetBBuilder<P1> extends TwoPhaseBuilder<WidgetB<P1>> {
    @SuppressWarnings("unchecked")
    static <P1> WidgetBBuilder<P1> aWidgetB() {
        return builderOf(WidgetBBuilder.class);
    }

    @SuppressWarnings("unchecked")
    static <P1> WidgetBBuilder<P1> aWidgetBFrom(final WidgetB<P1> template) {
        return builderOf(WidgetBBuilder.class).from(template);
    }

    WidgetBBuilder<P1> from(WidgetB<P1> template);

    @ConstructorParameter(0)
    WidgetBBuilder<P1> withProp1(P1 prop1);

    WidgetBBuilder<P1> withProp1(Builder<? extends P1> prop1Builder);

    WidgetBBuilder<P1> withProp2(P1 prop2);

    WidgetBBuilder<P1> withProp2(Builder<? extends P1> prop2Builder);
}
