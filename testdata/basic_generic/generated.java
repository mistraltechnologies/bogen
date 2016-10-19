import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public final class WidgetBuilder<P1> extends AbstractBuilder<Widget<P1>> {
    private final ValueContainer<P1> contentsBuilder = new ValueContainer<>();

    private WidgetBuilder(final Widget<P1> template) {
        super();
        if (template != null) {
            contentsBuilder.set(template.getContents());
        }
    }

    public static <P1> WidgetBuilder<P1> aWidget() {
        return new WidgetBuilder<P1>(null);
    }

    public static <P1> WidgetBuilder<P1> aWidgetFrom(final Widget<P1> template) {
        return new WidgetBuilder<P1>(template);
    }

    public WidgetBuilder<P1> withContents(final P1 contents) {
        this.contentsBuilder.set(contents);
        return this;
    }

    public WidgetBuilder<P1> withContents(final Builder<? extends P1> contentsBuilder) {
        this.contentsBuilder.set(contentsBuilder);
        return this;
    }

    public BuilderProperty<P1> getContents() {
        return contentsBuilder;
    }

    @Override
    protected Widget<P1> construct() {
        return new Widget<>(contentsBuilder.value());
    }

    @Override
    protected void assign(final Widget<P1> instance) {
        super.assign(instance);
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        contentsBuilder.reset();
    }
}
