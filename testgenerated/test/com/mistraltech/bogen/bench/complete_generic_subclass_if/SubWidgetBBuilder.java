package com.mistraltech.bogen.bench.complete_generic_subclass_if;

import com.mistraltech.bogen.bench.model.SubWidgetB;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(SubWidgetB.class)
public interface SubWidgetBBuilder<P1> extends TwoPhaseBuilder<SubWidgetB<P1>> {
    @SuppressWarnings("unchecked")
    static <P1> SubWidgetBBuilder<P1> aSubWidgetB() {
        return builderOf(SubWidgetBBuilder.class);
    }

    @SuppressWarnings("unchecked")
    static <P1> SubWidgetBBuilder<P1> aSubWidgetBFrom(final SubWidgetB<P1> template) {
        return (SubWidgetBBuilder<P1>) builderOf(SubWidgetBBuilder.class).from(template);
    }

    SubWidgetBBuilder<P1> from(SubWidgetB<P1> template);

    @ConstructorParameter(0)
    SubWidgetBBuilder<P1> withProp1(P1 prop1);

    SubWidgetBBuilder<P1> withProp1(Builder<? extends P1> prop1Builder);

    SubWidgetBBuilder<P1> withProp2(P1 prop2);

    SubWidgetBBuilder<P1> withProp2(Builder<? extends P1> prop2Builder);

    @ConstructorParameter(1)
    SubWidgetBBuilder<P1> withProp3(P1 prop3);

    SubWidgetBBuilder<P1> withProp3(Builder<? extends P1> prop3Builder);

    SubWidgetBBuilder<P1> withProp4(P1 prop4);

    SubWidgetBBuilder<P1> withProp4(Builder<? extends P1> prop4Builder);

    BuilderProperty<P1> getProp1();

    BuilderProperty<P1> getProp2();

    BuilderProperty<P1> getProp3();

    BuilderProperty<P1> getProp4();
}
