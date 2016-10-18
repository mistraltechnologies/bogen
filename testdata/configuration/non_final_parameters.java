import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public final class WidgetBuilder extends AbstractBuilder<Widget> {
    private final ValueContainer<String> propBuilder = new ValueContainer<>();

    private WidgetBuilder(Widget template) {
        super();
        if (template != null) {
            withProp(template.getProp());
        }
    }

    public static WidgetBuilder aWidget() {
        return new WidgetBuilder(null);
    }

    public static WidgetBuilder aWidgetFrom(Widget template) {
        return new WidgetBuilder(template);
    }

    public WidgetBuilder withProp(String prop) {
        this.propBuilder.set(prop);
        return this;
    }

    public WidgetBuilder withProp(Builder<? extends String> propBuilder) {
        this.propBuilder.set(propBuilder);
        return this;
    }

    @Override
    protected Widget construct() {
        return new Widget(propBuilder.value());
    }

    @Override
    protected void assign(Widget instance) {
        super.assign(instance);
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        propBuilder.reset();
    }
}
