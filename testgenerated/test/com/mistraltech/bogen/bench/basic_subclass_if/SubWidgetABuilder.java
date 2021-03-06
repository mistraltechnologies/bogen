package com.mistraltech.bogen.bench.basic_subclass_if;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;
import com.mistraltech.bogen.bench.model.SubWidgetA;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(SubWidgetA.class)
public interface SubWidgetABuilder extends WidgetABuilder<SubWidgetABuilder, SubWidgetA> {
    @SuppressWarnings("unchecked")
    static SubWidgetABuilder aSubWidgetA() {
        return builderOf(SubWidgetABuilder.class);
    }

    @SuppressWarnings("unchecked")
    static SubWidgetABuilder aSubWidgetAFrom(final SubWidgetA template) {
        return builderOf(SubWidgetABuilder.class).from(template);
    }

    SubWidgetABuilder from(SubWidgetA template);

    @ConstructorParameter(0)
    SubWidgetABuilder withProp1(String prop1);

    SubWidgetABuilder withProp1(Builder<? extends String> prop1Builder);

    SubWidgetABuilder withProp3(String prop3);

    SubWidgetABuilder withProp3(Builder<? extends String> prop3Builder);

    BuilderProperty<String> getProp1();

    BuilderProperty<String> getProp3();

    default Supplier<String> getDefaultProp1() {
        return singleValuePicker(null);
    }

    default Supplier<String> getDefaultProp3() {
        return singleValuePicker(null);
    }
}
