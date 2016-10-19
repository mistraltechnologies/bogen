import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public final class WidgetBuilder extends AbstractBuilder<Widget> {
    private final ValueContainer<String> prop1Builder = new ValueContainer<>();
    private final ValueContainer<String> prop2Builder = new ValueContainer<>();

    private WidgetBuilder(final Widget template) {
        super();
        if (template != null) {
            withProp1(template.getProp1());
            withProp2(template.getProp2());
        }
    }

    public static WidgetBuilder aWidget() {
        return new WidgetBuilder(null);
    }

    public static WidgetBuilder aWidgetFrom(final Widget template) {
        return new WidgetBuilder(template);
    }

    public WidgetBuilder withProp1(final String prop1) {
        this.prop1Builder.set(prop1);
        return this;
    }

    public WidgetBuilder withProp1(final Builder<? extends String> prop1Builder) {
        this.prop1Builder.set(prop1Builder);
        return this;
    }

    public WidgetBuilder withProp2(final String prop2) {
        this.prop2Builder.set(prop2);
        return this;
    }

    public WidgetBuilder withProp2(final Builder<? extends String> prop2Builder) {
        this.prop2Builder.set(prop2Builder);
        return this;
    }

    public BuilderProperty<String> getProp1() {
        return prop1Builder;
    }

    public BuilderProperty<String> getProp2() {
        return prop2Builder;
    }

    @Override
    protected Widget construct() {
        return new Widget(prop1Builder.value(), prop2Builder.value());
    }

    @Override
    protected void assign(final Widget instance) {
        super.assign(instance);
        instance.setProp1(prop1Builder.value());
        instance.setProp2(prop2Builder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        prop1Builder.reset();
        prop2Builder.reset();
    }
}
