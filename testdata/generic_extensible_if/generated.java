import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder<P1, P2, R extends WidgetBuilder<P1, P2, R, T>, T extends Widget<P1, P2>> extends TwoPhaseBuilder<T> {
    static <P1, P2> WidgetBuilder<?, Widget<P1, P2>> aWidget() {
        return builderOf(WidgetBuilder.class);
    }

    static <P1, P2> WidgetBuilder<?, Widget<P1, P2>> aWidgetFrom(final Widget<P1, P2> template) {
        return builderOf(WidgetBuilder.class).from(template);
    }

    R from(Widget<P1, P2> template);

    @ConstructorParameter(0)
    R withP(P1 p);

    R withP(Builder<? extends P1> pBuilder);

    R withQ(P2 q);

    R withQ(Builder<? extends P2> qBuilder);
}
