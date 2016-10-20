import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public abstract class WidgetBuilder<R extends WidgetBuilder<R, T>, T extends Widget> extends AbstractBuilder<T> {
    private final ValueContainer<String> propBuilder = new ValueContainer<>();

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

    public BuilderProperty<String> getProp() {
        return propBuilder;
    }

    @Override
    protected void assign(final T instance) {
        super.assign(instance);
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        propBuilder.reset();
    }

    public static class WidgetBuilderType extends WidgetBuilder<WidgetBuilderType, Widget> {
        private WidgetBuilderType(final Widget template) {
            super(template);
        }

        @Override
        protected Widget construct() {
            try {
                return new Widget(getProp().value());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
