import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.NaturalDefaultValuePicker.naturalDefault;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder<P1, P2, R extends WidgetBuilder<P1, P2, R, T>, T extends Widget<P1, P2>> extends BaseWidgetBuilder<P1, R, T> {
    @SuppressWarnings("unchecked")
    static <P1, P2> WidgetBuilderType<P1, P2> aWidget() {
        return builderOf(WidgetBuilderType.class);
    }

    @SuppressWarnings("unchecked")
    static <P1, P2> WidgetBuilderType<P1, P2> aWidgetFrom(final Widget<P1, P2> template) {
        return (WidgetBuilderType<P1, P2>) builderOf(WidgetBuilderType.class).from(template);
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

    BuilderProperty<P2> getProp1();

    BuilderProperty<P2> getProp2();

    BuilderProperty<P1> getSuperProp1();

    default Supplier<P2> getDefaultProp1() {
        return naturalDefault(P2.class);
    }

    default Supplier<P2> getDefaultProp2() {
        return naturalDefault(P2.class);
    }

    default Supplier<P1> getDefaultSuperProp1() {
        return naturalDefault(P1.class);
    }

    @Builds(Widget.class)
    interface WidgetBuilderType<P1, P2> extends WidgetBuilder<P1, P2, WidgetBuilderType<P1, P2>, Widget<P1, P2>> {
    }
}
