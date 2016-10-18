import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public final class WidgetBuilder<P1, P2> extends BaseWidgetBuilder<P1, WidgetBuilder<P1, P2>, Widget<P1, P2>> {
    private final ValueContainer<P2> prop1Builder = new ValueContainer<>();
    private final ValueContainer<P2> prop2Builder = new ValueContainer<>();
    private final ValueContainer<P1> superProp1Builder = new ValueContainer<>();

    private WidgetBuilder(final Widget<P1, P2> template) {
        super(template);
        if (template != null) {
            withProp1(template.getProp1());
            withProp2(template.getProp2());
            withSuperProp1(template.getSuperProp1());
        }
    }

    public static <P1, P2> WidgetBuilder<P1, P2> aWidget() {
        return new WidgetBuilder<P1, P2>(null);
    }

    public static <P1, P2> WidgetBuilder<P1, P2> aWidgetFrom(final Widget<P1, P2> template) {
        return new WidgetBuilder<P1, P2>(template);
    }

    public WidgetBuilder<P1, P2> withProp1(final P2 prop1) {
        this.prop1Builder.set(prop1);
        return this;
    }

    public WidgetBuilder<P1, P2> withProp1(final Builder<? extends P2> prop1Builder) {
        this.prop1Builder.set(prop1Builder);
        return this;
    }

    public WidgetBuilder<P1, P2> withProp2(final P2 prop2) {
        this.prop2Builder.set(prop2);
        return this;
    }

    public WidgetBuilder<P1, P2> withProp2(final Builder<? extends P2> prop2Builder) {
        this.prop2Builder.set(prop2Builder);
        return this;
    }

    public WidgetBuilder<P1, P2> withSuperProp1(final P1 superProp1) {
        this.superProp1Builder.set(superProp1);
        return this;
    }

    public WidgetBuilder<P1, P2> withSuperProp1(final Builder<? extends P1> superProp1Builder) {
        this.superProp1Builder.set(superProp1Builder);
        return this;
    }

    @Override
    protected Widget<P1, P2> construct() {
        return new Widget<>(superProp1Builder.value(), prop1Builder.value());
    }

    @Override
    protected void assign(final Widget<P1, P2> instance) {
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
}
