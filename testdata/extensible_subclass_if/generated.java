import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder<R extends WidgetBuilder<R, T>, T extends Widget> extends BaseWidgetBuilder<R, T> {
    @SuppressWarnings("unchecked")
    static WidgetBuilderType aWidget() {
        return builderOf(WidgetBuilderType.class);
    }

    @SuppressWarnings("unchecked")
    static WidgetBuilderType aWidgetFrom(final Widget template) {
        return builderOf(WidgetBuilderType.class).from(template);
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

    BuilderProperty<String> getProp1();

    BuilderProperty<String> getProp2();

    BuilderProperty<Integer> getSuperProp1();

    default Supplier<String> getDefaultProp1() {
        return singleValuePicker(null);
    }

    default Supplier<String> getDefaultProp2() {
        return singleValuePicker(null);
    }

    default Supplier<Integer> getDefaultSuperProp1() {
        return singleValuePicker(0);
    }

    @Builds(Widget.class)
    interface WidgetBuilderType extends WidgetBuilder<WidgetBuilderType, Widget> {
    }
}
