import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder extends TwoPhaseBuilder<Widget> {
    @SuppressWarnings("unchecked")
    static WidgetBuilder aWidget() {
        return builderOf(WidgetBuilder.class);
    }

    @SuppressWarnings("unchecked")
    static WidgetBuilder aWidgetFrom(final Widget template) {
        return builderOf(WidgetBuilder.class).from(template);
    }

    WidgetBuilder from(Widget template);

    @ConstructorParameter(0)
    WidgetBuilder withProp1(String prop1);

    WidgetBuilder withProp1(Builder<? extends String> prop1Builder);

    @ConstructorParameter(1)
    WidgetBuilder withProp2(String prop2);

    WidgetBuilder withProp2(Builder<? extends String> prop2Builder);

    BuilderProperty<String> getProp1();

    BuilderProperty<String> getProp2();

    default Supplier<String> getDefaultProp1() {
        return singleValuePicker(null);
    }

    default Supplier<String> getDefaultProp2() {
        return singleValuePicker(null);
    }
}
