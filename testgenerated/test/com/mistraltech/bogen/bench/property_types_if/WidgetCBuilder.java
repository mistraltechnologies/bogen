package com.mistraltech.bogen.bench.property_types_if;

import com.mistraltech.bogen.bench.model.WidgetC;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.NaturalDefaultValuePicker.naturalDefault;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(WidgetC.class)
public interface WidgetCBuilder extends TwoPhaseBuilder<WidgetC> {
    @SuppressWarnings("unchecked")
    static WidgetCBuilder aWidgetC() {
        return builderOf(WidgetCBuilder.class);
    }

    @SuppressWarnings("unchecked")
    static WidgetCBuilder aWidgetCFrom(final WidgetC template) {
        return (WidgetCBuilder) builderOf(WidgetCBuilder.class).from(template);
    }

    WidgetCBuilder from(WidgetC template);

    @ConstructorParameter(6)
    WidgetCBuilder withA(String a);

    WidgetCBuilder withA(Builder<? extends String> aBuilder);

    @ConstructorParameter(1)
    WidgetCBuilder withB(byte b);

    WidgetCBuilder withB(Builder<? extends Byte> bBuilder);

    @ConstructorParameter(2)
    WidgetCBuilder withC(char c);

    WidgetCBuilder withC(Builder<? extends Character> cBuilder);

    @ConstructorParameter(5)
    WidgetCBuilder withD(double d);

    WidgetCBuilder withD(Builder<? extends Double> dBuilder);

    @ConstructorParameter(4)
    WidgetCBuilder withF(float f);

    WidgetCBuilder withF(Builder<? extends Float> fBuilder);

    @ConstructorParameter(0)
    WidgetCBuilder withI(int i);

    WidgetCBuilder withI(Builder<? extends Integer> iBuilder);

    @ConstructorParameter(3)
    WidgetCBuilder withS(short s);

    WidgetCBuilder withS(Builder<? extends Short> sBuilder);

    BuilderProperty<String> getA();

    BuilderProperty<Byte> getB();

    BuilderProperty<Character> getC();

    BuilderProperty<Double> getD();

    BuilderProperty<Float> getF();

    BuilderProperty<Integer> getI();

    BuilderProperty<Short> getS();

    default Supplier<String> getDefaultA() {
        return naturalDefault(String.class);
    }

    default Supplier<Byte> getDefaultB() {
        return naturalDefault(byte.class);
    }

    default Supplier<Character> getDefaultC() {
        return naturalDefault(char.class);
    }

    default Supplier<Double> getDefaultD() {
        return naturalDefault(double.class);
    }

    default Supplier<Float> getDefaultF() {
        return naturalDefault(float.class);
    }

    default Supplier<Integer> getDefaultI() {
        return naturalDefault(int.class);
    }

    default Supplier<Short> getDefaultS() {
        return naturalDefault(short.class);
    }
}
