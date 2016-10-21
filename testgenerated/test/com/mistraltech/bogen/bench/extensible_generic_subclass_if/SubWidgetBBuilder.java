package com.mistraltech.bogen.bench.extensible_generic_subclass_if;

import com.mistraltech.bogen.bench.model.SubWidgetB;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(SubWidgetB.class)
public interface SubWidgetBBuilder<P1, R extends SubWidgetBBuilder<P1, R, T>, T extends SubWidgetB<P1>> extends WidgetBBuilder<P1, R, T> {
    @SuppressWarnings("unchecked")
    static <P1> SubWidgetBBuilderType<P1> aSubWidgetB() {
        return builderOf(SubWidgetBBuilderType.class);
    }

    @SuppressWarnings("unchecked")
    static <P1> SubWidgetBBuilderType<P1> aSubWidgetBFrom(final SubWidgetB<P1> template) {
        return (SubWidgetBBuilderType<P1>) builderOf(SubWidgetBBuilderType.class).from(template);
    }

    R from(SubWidgetB<P1> template);

    @ConstructorParameter(0)
    R withProp1(P1 prop1);

    R withProp1(Builder<? extends P1> prop1Builder);

    @ConstructorParameter(1)
    R withProp3(P1 prop3);

    R withProp3(Builder<? extends P1> prop3Builder);

    R withProp4(P1 prop4);

    R withProp4(Builder<? extends P1> prop4Builder);

    BuilderProperty<P1> getProp1();

    BuilderProperty<P1> getProp3();

    BuilderProperty<P1> getProp4();

    @Builds(SubWidgetB.class)
    interface SubWidgetBBuilderType<P1> extends SubWidgetBBuilder<P1, SubWidgetBBuilderType<P1>, SubWidgetB<P1>> {
    }
}
