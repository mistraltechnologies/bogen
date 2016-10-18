import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public final class WidgetBuilder extends AbstractBuilder<Widget> {
    private final ValueContainer<String> propBuilder = new ValueContainer<>();

    private WidgetBuilder(final Widget template) {
        super();
        if (template != null) {
        }
    }

    public static WidgetBuilder aWidget() {
        return new WidgetBuilder(null);
    }

    public static WidgetBuilder aWidgetFrom(final Widget template) {
        return new WidgetBuilder(template);
    }

    public WidgetBuilder withProp(final String prop) {
        this.propBuilder.set(prop);
        return this;
    }

    public WidgetBuilder withProp(final Builder<? extends String> propBuilder) {
        this.propBuilder.set(propBuilder);
        return this;
    }

    @Override
    protected Widget construct() {
        return new Widget();
    }

    @Override
    protected void assign(final Widget instance) {
        super.assign(instance);
        instance.setProp(propBuilder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        propBuilder.reset();
    }
}
