import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.NaturalDefaultValuePicker.naturalDefault;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder extends TwoPhaseBuilder<Widget> {
    @SuppressWarnings("unchecked")
    static WidgetBuilder aWidget() {
        return builderOf(WidgetBuilder.class);
    }

    @SuppressWarnings("unchecked")
    static WidgetBuilder aWidgetFrom(final Widget template) {
        return (WidgetBuilder) builderOf(WidgetBuilder.class).from(template);
    }

    WidgetBuilder from(Widget template);

    WidgetBuilder withProp(String prop);

    WidgetBuilder withProp(Builder<? extends String> propBuilder);

    BuilderProperty<String> getProp();

    default Supplier<String> getDefaultProp() {
        return naturalDefault(String.class);
    }
}
