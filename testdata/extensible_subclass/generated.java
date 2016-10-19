import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public abstract class WidgetBuilder<R extends WidgetBuilder<R, T>, T extends Widget> extends BaseWidgetBuilder<R, T> {
    private final ValueContainer<String> prop1Builder = new ValueContainer<>();
    private final ValueContainer<String> prop2Builder = new ValueContainer<>();
    private final ValueContainer<Integer> superProp1Builder = new ValueContainer<>(int.class);

    protected WidgetBuilder(final T template) {
        super(template);
        if (template != null) {
            prop1Builder.set(template.getProp1());
            prop2Builder.set(template.getProp2());
            superProp1Builder.set(template.getSuperProp1());
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

    public R withProp1(final String prop1) {
        this.prop1Builder.set(prop1);
        return self();
    }

    public R withProp1(final Builder<? extends String> prop1Builder) {
        this.prop1Builder.set(prop1Builder);
        return self();
    }

    public R withProp2(final String prop2) {
        this.prop2Builder.set(prop2);
        return self();
    }

    public R withProp2(final Builder<? extends String> prop2Builder) {
        this.prop2Builder.set(prop2Builder);
        return self();
    }

    public R withSuperProp1(final int superProp1) {
        this.superProp1Builder.set(superProp1);
        return self();
    }

    public R withSuperProp1(final Builder<? extends Integer> superProp1Builder) {
        this.superProp1Builder.set(superProp1Builder);
        return self();
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

    @Override
    protected void assign(final T instance) {
        super.assign(instance);
        instance.setProp2(prop2Builder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        prop1Builder.reset();
        prop2Builder.reset();
        superProp1Builder.reset();
    }

    public static class WidgetBuilderType extends WidgetBuilder<WidgetBuilderType, Widget> {
        private WidgetBuilderType(final Widget template) {
            super(template);
        }

        @Override
        protected Widget construct() {
            return new Widget(getSuperProp1().value(), getProp1().value());
        }
    }
}
