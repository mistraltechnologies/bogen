import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder<P1, P2, R extends WidgetBuilder<P1, P2, R, T>, T extends Widget<P1, P2>> extends BaseWidgetBuilder<P1, R, T> {
    static <P1, P2> WidgetBuilder<?, Widget<P1, P2>> aWidget() {
        return builderOf(WidgetBuilder.class);
    }

    static <P1, P2> WidgetBuilder<?, Widget<P1, P2>> aWidgetFrom(final Widget<P1, P2> template) {
        return builderOf(WidgetBuilder.class).from(template);
    }

    R from(Widget<P1, P2> template);

    @ConstructorParameter(1)
    R withProp1(P2 prop1);

    R withProp1(Builder<? extends P2> prop1Builder);

    R withProp2(P2 prop2);

    R withProp2(Builder<? extends P2> prop2Builder);

    @ConstructorParameter(0)
    R withSuperProp1(P1 superProp1);

    R withSuperProp1(Builder<? extends P1> superProp1Builder);
}
