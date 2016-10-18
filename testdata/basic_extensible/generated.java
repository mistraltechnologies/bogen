import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public class WidgetBuilder<R extends WidgetBuilder<R, T>, T extends Widget> extends AbstractBuilder<T> {
    private final ValueContainer<String> propBuilder = new ValueContainer<>();
    private final ValueContainer<String> prop1Builder = new ValueContainer<>();

    protected WidgetBuilder(final T template) {
        super();
        if (template != null) {
        }
    }

    public static WidgetBuilderType aWidget() {
        return new WidgetBuilderType(null);
    }

    public static WidgetBuilderType aWidgetFrom(final Widget template) {
        return new WidgetBuilderType(template);
    }

    @SuppressWarnings("unchecked")
    private R self() {
        return (R) this;
    }

    public R withProp(final String prop) {
        this.propBuilder.set(prop);
        return self();
    }

    public R withProp(final Builder<? extends String> propBuilder) {
        this.propBuilder.set(propBuilder);
        return self();
    }

    public R withProp1(final String prop1) {
        this.prop1Builder.set(prop1);
        return self();
    }

    public R withProp1(final Builder<? extends String> prop1Builder) {
        this.prop1Builder.set(prop1Builder);
        return self();
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
        prop1Builder.reset();
    }

    public static class WidgetBuilderType extends WidgetBuilder<WidgetBuilderType, Widget> {
        protected WidgetBuilderType(final Widget template) {
            super(template);
        }
    }
}
