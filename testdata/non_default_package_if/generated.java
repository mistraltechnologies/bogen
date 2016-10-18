package com.acme;

import com.acme.widget.Widget;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder extends TwoPhaseBuilder<Widget> {
    static WidgetBuilder aWidget() {
        return builderOf(com.acme.WidgetBuilder.class);
    }

    static WidgetBuilder aWidgetFrom(final Widget template) {
        return builderOf(com.acme.WidgetBuilder.class).from(template);
    }

    WidgetBuilder from(Widget template);
}
