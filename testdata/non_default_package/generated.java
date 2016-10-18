package com.acme;

import com.acme.widget.Widget;
import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public final class WidgetBuilder extends AbstractBuilder<Widget> {
    private WidgetBuilder(final Widget template) {
        super();
    }

    public static WidgetBuilder aWidget() {
        return new WidgetBuilder(null);
    }

    public static WidgetBuilder aWidgetFrom(final Widget template) {
        return new WidgetBuilder(template);
    }

    @Override
    protected Widget construct() {
        return new Widget();
    }

    @Override
    protected void assign(final Widget instance) {
        super.assign(instance);
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
    }
}
