import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public final class WidgetBuilder extends AbstractBuilder<Widget> {
    private final ValueContainer<String> _propBuilder = new ValueContainer<>();
    private final ValueContainer<String> iBuilder = new ValueContainer<>();
    private final ValueContainer<String> uRLBuilder = new ValueContainer<>();

    private WidgetBuilder(final Widget template) {
        super();
        if (template != null) {
            with_prop(template.get_prop());
            withI(template.getI());
            withURL(template.getURL());
        }
    }

    public static WidgetBuilder aWidget() {
        return new WidgetBuilder(null);
    }

    public static WidgetBuilder aWidgetFrom(final Widget template) {
        return new WidgetBuilder(template);
    }

    public WidgetBuilder with_prop(final String _prop) {
        this._propBuilder.set(_prop);
        return this;
    }

    public WidgetBuilder with_prop(final Builder<? extends String> _propBuilder) {
        this._propBuilder.set(_propBuilder);
        return this;
    }

    public WidgetBuilder withI(final String i) {
        this.iBuilder.set(i);
        return this;
    }

    public WidgetBuilder withI(final Builder<? extends String> iBuilder) {
        this.iBuilder.set(iBuilder);
        return this;
    }

    public WidgetBuilder withURL(final String uRL) {
        this.uRLBuilder.set(uRL);
        return this;
    }

    public WidgetBuilder withURL(final Builder<? extends String> uRLBuilder) {
        this.uRLBuilder.set(uRLBuilder);
        return this;
    }

    public BuilderProperty<String> get_prop() {
        return _propBuilder;
    }

    public BuilderProperty<String> getI() {
        return iBuilder;
    }

    public BuilderProperty<String> getURL() {
        return uRLBuilder;
    }

    @Override
    protected Widget construct() {
        return new Widget(iBuilder.value(), uRLBuilder.value(), _propBuilder.value());
    }

    @Override
    protected void assign(final Widget instance) {
        super.assign(instance);
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        _propBuilder.reset();
        iBuilder.reset();
        uRLBuilder.reset();
    }
}
