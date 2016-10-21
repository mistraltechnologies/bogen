package com.mistraltech.bogen.bench.setter_throws_if;

import com.mistraltech.bogen.bench.model.WidgetE;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(WidgetE.class)
public interface WidgetEBuilder extends TwoPhaseBuilder<WidgetE> {
    @SuppressWarnings("unchecked")
    static WidgetEBuilder aWidgetE() {
        return builderOf(WidgetEBuilder.class);
    }

    @SuppressWarnings("unchecked")
    static WidgetEBuilder aWidgetEFrom(final WidgetE template) {
        return (WidgetEBuilder) builderOf(WidgetEBuilder.class).from(template);
    }

    WidgetEBuilder from(WidgetE template);

    @ConstructorParameter(0)
    WidgetEBuilder withProp1(String prop1);

    WidgetEBuilder withProp1(Builder<? extends String> prop1Builder);

    WidgetEBuilder withProp2(String prop2);

    WidgetEBuilder withProp2(Builder<? extends String> prop2Builder);

    BuilderProperty<String> getProp1();

    BuilderProperty<String> getProp2();
}
