import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder<R extends WidgetBuilder<R, T>, T extends Widget> extends BaseWidgetBuilder<R, T> {
    static WidgetBuilder<?, Widget> aWidget() {
        return builderOf(WidgetBuilder.class);
    }

    static WidgetBuilder<?, Widget> aWidgetFrom(final Widget template) {
        return builderOf(WidgetBuilder.class).from(template);
    }

    R from(Widget template);

    @ConstructorParameter(1)
    R withProp1(String prop1);

    R withProp1(Builder<? extends String> prop1Builder);

    R withProp2(String prop2);

    R withProp2(Builder<? extends String> prop2Builder);

    @ConstructorParameter(0)
    R withSuperProp1(int superProp1);

    R withSuperProp1(Builder<? extends Integer> superProp1Builder);
}
