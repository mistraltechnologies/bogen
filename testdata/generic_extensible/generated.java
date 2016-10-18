import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public class WidgetBuilder<P1, P2, R extends WidgetBuilder<P1, P2, R, T>, T extends Widget<P1, P2>> extends AbstractBuilder<T> {
    private final ValueContainer<P1> pBuilder = new ValueContainer<>();
    private final ValueContainer<P2> qBuilder = new ValueContainer<>();

    protected WidgetBuilder(final T template) {
        super();
        if (template != null) {
            withP(template.getP());
            withQ(template.getQ());
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

    public R withP(final P1 p) {
        this.pBuilder.set(p);
        return self();
    }

    public R withP(final Builder<? extends P1> pBuilder) {
        this.pBuilder.set(pBuilder);
        return self();
    }

    public R withQ(final P2 q) {
        this.qBuilder.set(q);
        return self();
    }

    public R withQ(final Builder<? extends P2> qBuilder) {
        this.qBuilder.set(qBuilder);
        return self();
    }

    @Override
    protected void assign(final Widget<P1, P2> instance) {
        super.assign(instance);
        instance.setQ(qBuilder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        pBuilder.reset();
        qBuilder.reset();
    }

    public static class WidgetBuilderType<P1, P2> extends WidgetBuilder<P1, P2, WidgetBuilderType<P1, P2>, Widget<P1, P2>> {
        protected WidgetBuilderType(final Widget<P1, P2> template) {
            super(template);
        }
    }
}
