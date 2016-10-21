import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.NaturalDefaultValuePicker.naturalDefault;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder<P1, P2> extends BaseWidgetBuilder<P1, WidgetBuilder<P1, P2>, Widget<P1, P2>> {
    @SuppressWarnings("unchecked")
    static <P1, P2> WidgetBuilder<P1, P2> aWidget() {
        return builderOf(WidgetBuilder.class);
    }

    @SuppressWarnings("unchecked")
    static <P1, P2> WidgetBuilder<P1, P2> aWidgetFrom(final Widget<P1, P2> template) {
        return (WidgetBuilder<P1, P2>) builderOf(WidgetBuilder.class).from(template);
    }

    WidgetBuilder<P1, P2> from(Widget<P1, P2> template);

    @ConstructorParameter(1)
    WidgetBuilder<P1, P2> withProp1(P2 prop1);

    WidgetBuilder<P1, P2> withProp1(Builder<? extends P2> prop1Builder);

    WidgetBuilder<P1, P2> withProp2(P2 prop2);

    WidgetBuilder<P1, P2> withProp2(Builder<? extends P2> prop2Builder);

    @ConstructorParameter(0)
    WidgetBuilder<P1, P2> withSuperProp1(P1 superProp1);

    WidgetBuilder<P1, P2> withSuperProp1(Builder<? extends P1> superProp1Builder);

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
}
