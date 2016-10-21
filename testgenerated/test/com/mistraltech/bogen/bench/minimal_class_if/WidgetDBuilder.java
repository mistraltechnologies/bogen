package com.mistraltech.bogen.bench.minimal_class_if;

import com.mistraltech.bogen.bench.model.WidgetD;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(WidgetD.class)
public interface WidgetDBuilder extends TwoPhaseBuilder<WidgetD> {
    @SuppressWarnings("unchecked")
    static WidgetDBuilder aWidgetD() {
        return builderOf(WidgetDBuilder.class);
    }

    @SuppressWarnings("unchecked")
    static WidgetDBuilder aWidgetDFrom(final WidgetD template) {
        return (WidgetDBuilder) builderOf(WidgetDBuilder.class).from(template);
    }

    WidgetDBuilder from(WidgetD template);
}
