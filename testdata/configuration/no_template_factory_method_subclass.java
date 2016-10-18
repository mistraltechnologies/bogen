import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public final class WidgetBuilder extends BaseWidgetBuilder<WidgetBuilder, Widget> {
    private final ValueContainer<String> propBuilder = new ValueContainer<>();

    private WidgetBuilder() {
        super();
    }

    public static WidgetBuilder aWidget() {
        return new WidgetBuilder();
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
