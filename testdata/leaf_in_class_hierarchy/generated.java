import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public final class WidgetBuilder extends AbstractBuilder<Widget> {
    private final ValueContainer<String> prop1Builder = new ValueContainer<>();
    private final ValueContainer<String> prop2Builder = new ValueContainer<>();
    private final ValueContainer<Integer> superProp1Builder = new ValueContainer<>(int.class);
    private final ValueContainer<Integer> superProp2Builder = new ValueContainer<>(int.class);

    private WidgetBuilder(final Widget template) {
        super();
        if (template != null) {
            prop1Builder.set(template.getProp1());
            prop2Builder.set(template.getProp2());
            superProp1Builder.set(template.getSuperProp1());
            superProp2Builder.set(template.getSuperProp2());
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

    public WidgetBuilder withSuperProp1(final int superProp1) {
        this.superProp1Builder.set(superProp1);
        return this;
    }

    public WidgetBuilder withSuperProp1(final Builder<? extends Integer> superProp1Builder) {
        this.superProp1Builder.set(superProp1Builder);
        return this;
    }

    public WidgetBuilder withSuperProp2(final int superProp2) {
        this.superProp2Builder.set(superProp2);
        return this;
    }

    public WidgetBuilder withSuperProp2(final Builder<? extends Integer> superProp2Builder) {
        this.superProp2Builder.set(superProp2Builder);
        return this;
    }

    public BuilderProperty<String> getProp1() {
        return prop1Builder;
    }

    public BuilderProperty<String> getProp2() {
        return prop2Builder;
    }

    public BuilderProperty<Integer> getSuperProp1() {
        return superProp1Builder;
    }

    public BuilderProperty<Integer> getSuperProp2() {
        return superProp2Builder;
    }

    @Override
    protected Widget construct() {
        return new Widget(superProp1Builder.value(), prop1Builder.value());
    }

    @Override
    protected void assign(final Widget instance) {
        super.assign(instance);
        instance.setProp2(prop2Builder.value());
        instance.setSuperProp2(superProp2Builder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        prop1Builder.reset();
        prop2Builder.reset();
        superProp1Builder.reset();
        superProp2Builder.reset();
    }
}
