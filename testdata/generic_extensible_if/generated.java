import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.NaturalDefaultValuePicker.naturalDefault;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder<P1, P2, R extends WidgetBuilder<P1, P2, R, T>, T extends Widget<P1, P2>> extends TwoPhaseBuilder<T> {
    @SuppressWarnings("unchecked")
    static <P1, P2> WidgetBuilderType<P1, P2> aWidget() {
        return builderOf(WidgetBuilderType.class);
    }

    @SuppressWarnings("unchecked")
    static <P1, P2> WidgetBuilderType<P1, P2> aWidgetFrom(final Widget<P1, P2> template) {
        return (WidgetBuilderType<P1, P2>) builderOf(WidgetBuilderType.class).from(template);
    }

    R from(Widget<P1, P2> template);

    @ConstructorParameter(0)
    R withP(P1 p);

    R withP(Builder<? extends P1> pBuilder);

    R withQ(P2 q);

    R withQ(Builder<? extends P2> qBuilder);

    BuilderProperty<P1> getP();

    BuilderProperty<P2> getQ();

    default Supplier<P1> getDefaultP() {
        return naturalDefault(P1.class);
    }

    default Supplier<P2> getDefaultQ() {
        return naturalDefault(P2.class);
    }

    @Builds(Widget.class)
    interface WidgetBuilderType<P1, P2> extends WidgetBuilder<P1, P2, WidgetBuilderType<P1, P2>, Widget<P1, P2>> {
    }
}
