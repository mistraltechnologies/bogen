package com.mistraltech.bogen.bench.property_types_if;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;
import com.mistraltech.bogen.bench.model.WidgetC;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(WidgetC.class)
public interface WidgetCBuilder extends TwoPhaseBuilder<WidgetC> {
    @SuppressWarnings("unchecked")
    static WidgetCBuilder aWidgetC() {
        return builderOf(WidgetCBuilder.class);
    }

    @SuppressWarnings("unchecked")
    static WidgetCBuilder aWidgetCFrom(final WidgetC template) {
        return builderOf(WidgetCBuilder.class).from(template);
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
        return singleValuePicker(null);
    }

    default Supplier<Byte> getDefaultB() {
        return singleValuePicker((byte) 0);
    }

    default Supplier<Character> getDefaultC() {
        return singleValuePicker('\0');
    }

    default Supplier<Double> getDefaultD() {
        return singleValuePicker(0.0D);
    }

    default Supplier<Float> getDefaultF() {
        return singleValuePicker(0.0F);
    }

    default Supplier<Integer> getDefaultI() {
        return singleValuePicker(0);
    }

    default Supplier<Short> getDefaultS() {
        return singleValuePicker((short) 0);
    }
}
