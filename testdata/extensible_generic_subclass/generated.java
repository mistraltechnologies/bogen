import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public class WidgetBuilder<P1, P2, R extends WidgetBuilder<P1, P2, R, T>, T extends Widget<P1, P2>> extends BaseWidgetBuilder<P1, R, T> {
    private final ValueContainer<P2> prop1Builder = new ValueContainer<>();
    private final ValueContainer<P2> prop2Builder = new ValueContainer<>();
    private final ValueContainer<P1> superProp1Builder = new ValueContainer<>();

    protected WidgetBuilder(final T template) {
        super(template);
        if (template != null) {
            withProp1(template.getProp1());
            withProp2(template.getProp2());
            withSuperProp1(template.getSuperProp1());
        }
    }

    public static <P1, P2> WidgetBuilderType<P1, P2> aWidget() {
        return new WidgetBuilderType<P1, P2>(null);
    }

    public static <P1, P2> WidgetBuilderType<P1, P2> aWidgetFrom(final Widget<P1, P2> template) {
        return new WidgetBuilderType<P1, P2>(template);
    }

    @SuppressWarnings("unchecked")
    private R self() {
        return (R) this;
    }

    public R withProp1(final P2 prop1) {
        this.prop1Builder.set(prop1);
        return self();
    }

    public R withProp1(final Builder<? extends P2> prop1Builder) {
        this.prop1Builder.set(prop1Builder);
        return self();
    }

    public R withProp2(final P2 prop2) {
        this.prop2Builder.set(prop2);
        return self();
    }

    public R withProp2(final Builder<? extends P2> prop2Builder) {
        this.prop2Builder.set(prop2Builder);
        return self();
    }

    public R withSuperProp1(final P1 superProp1) {
        this.superProp1Builder.set(superProp1);
        return self();
    }

    public R withSuperProp1(final Builder<? extends P1> superProp1Builder) {
        this.superProp1Builder.set(superProp1Builder);
        return self();
    }

    @Override
    protected void assign(final Widget<P1, P2> instance) {
        super.assign(instance);
        instance.setProp2(prop2Builder.value());
        instance.setSuperProp1(superProp1Builder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        prop1Builder.reset();
        prop2Builder.reset();
        superProp1Builder.reset();
    }

    public static class WidgetBuilderType<P1, P2> extends WidgetBuilder<P1, P2, WidgetBuilderType<P1, P2>, Widget<P1, P2>> {
        protected WidgetBuilderType(final Widget<P1, P2> template) {
            super(template);
        }
    }
}
