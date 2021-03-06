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

    @ConstructorParameter(2)
    WidgetBuilder with_prop(String _prop);

    WidgetBuilder with_prop(Builder<? extends String> _propBuilder);

    @ConstructorParameter(0)
    WidgetBuilder withI(String i);

    WidgetBuilder withI(Builder<? extends String> iBuilder);

    @ConstructorParameter(1)
    WidgetBuilder withURL(String uRL);

    WidgetBuilder withURL(Builder<? extends String> uRLBuilder);

    BuilderProperty<String> get_prop();

    BuilderProperty<String> getI();

    BuilderProperty<String> getURL();

    default Supplier<String> getDefault_prop() {
        return singleValuePicker(null);
    }

    default Supplier<String> getDefaultI() {
        return singleValuePicker(null);
    }

    default Supplier<String> getDefaultURL() {
        return singleValuePicker(null);
    }
}
