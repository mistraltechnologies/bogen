package com.mistraltech.bogen.bench.minimal_class;

import com.mistraltech.bogen.bench.model.WidgetD;
import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(WidgetD.class)
public final class WidgetDBuilder extends AbstractBuilder<WidgetD> {
    private WidgetDBuilder(final WidgetD template) {
        super();
    }

    public static WidgetDBuilder aWidgetD() {
        return new WidgetDBuilder(null);
    }

    public static WidgetDBuilder aWidgetDFrom(final WidgetD template) {
        return new WidgetDBuilder(template);
    }

    @Override
    protected WidgetD construct() {
        return new WidgetD();
    }

    @Override
    protected void assign(final WidgetD instance) {
        super.assign(instance);
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
    }
}
