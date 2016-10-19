import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public final class WidgetBuilder extends AbstractBuilder<Widget> {
    private final ValueContainer<String> propBuilder = new ValueContainer<>();

    private WidgetBuilder(final Widget template) {
        super();
        if (template != null) {
            propBuilder.set(template.getProp());
        }
    }

    public static WidgetBuilder aWidget() {
        return new WidgetBuilder(null);
    }

    public static WidgetBuilder aWidgetFrom(final Widget template) {
        return new WidgetBuilder(template);
    }

    public WidgetBuilder abcProp(final String prop) {
        this.propBuilder.set(prop);
        return this;
    }

    public WidgetBuilder abcProp(final Builder<? extends String> propBuilder) {
        this.propBuilder.set(propBuilder);
        return this;
    }

    public BuilderProperty<String> getProp() {
        return propBuilder;
    }

    @Override
    protected Widget construct() {
        return new Widget(propBuilder.value());
    }

    @Override
    protected void assign(final Widget instance) {
        super.assign(instance);
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        propBuilder.reset();
    }
}
