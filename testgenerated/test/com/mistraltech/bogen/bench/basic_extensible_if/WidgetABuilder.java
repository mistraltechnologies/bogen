package com.mistraltech.bogen.bench.basic_extensible_if;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;
import com.mistraltech.bogen.bench.model.WidgetA;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(WidgetA.class)
public interface WidgetABuilder<R extends WidgetABuilder<R, T>, T extends WidgetA> extends TwoPhaseBuilder<T> {
    @SuppressWarnings("unchecked")
    static WidgetABuilderType aWidgetA() {
        return builderOf(WidgetABuilderType.class);
    }

    @SuppressWarnings("unchecked")
    static WidgetABuilderType aWidgetAFrom(final WidgetA template) {
        return builderOf(WidgetABuilderType.class).from(template);
    }

    R from(WidgetA template);

    @ConstructorParameter(0)
    R withProp1(String prop1);

    R withProp1(Builder<? extends String> prop1Builder);

    R withProp2(String prop2);

    R withProp2(Builder<? extends String> prop2Builder);

    BuilderProperty<String> getProp1();

    BuilderProperty<String> getProp2();

    default Supplier<String> getDefaultProp1() {
        return singleValuePicker(null);
    }

    default Supplier<String> getDefaultProp2() {
        return singleValuePicker(null);
    }

    @Builds(WidgetA.class)
    interface WidgetABuilderType extends WidgetABuilder<WidgetABuilderType, WidgetA> {
    }
}
