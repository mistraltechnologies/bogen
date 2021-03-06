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
    static WidgetBuilder abcWidget() {
        return builderOf(WidgetBuilder.class);
    }

    @SuppressWarnings("unchecked")
    static WidgetBuilder abcWidget(final Widget template) {
        return builderOf(WidgetBuilder.class).from(template);
    }

    WidgetBuilder from(Widget template);

    @ConstructorParameter(0)
    WidgetBuilder withProp(String prop);

    WidgetBuilder withProp(Builder<? extends String> propBuilder);

    BuilderProperty<String> getProp();

    default Supplier<String> getDefaultProp() {
        return singleValuePicker(null);
    }
}
