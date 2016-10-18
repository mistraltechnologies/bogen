import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public class WidgetBuilder<R extends WidgetBuilder<R, T>, T extends Widget> extends BaseWidgetBuilder<R, T> {
    private final ValueContainer<String> prop1Builder = new ValueContainer<>();
    private final ValueContainer<String> prop2Builder = new ValueContainer<>();
    private final ValueContainer<Integer> superProp1Builder = new ValueContainer<>(int.class);

    protected WidgetBuilder(final T template) {
        super(template);
        if (template != null) {
            withProp1(template.getProp1());
            withProp2(template.getProp2());
            withSuperProp1(template.getSuperProp1());
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

    @Override
    protected void assign(final Widget instance) {
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
        protected WidgetBuilderType(final Widget template) {
            super(template);
        }
    }
}
