import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder<R extends WidgetBuilder<R, T>, T extends Widget> extends TwoPhaseBuilder<T> {
    @SuppressWarnings("unchecked")
    static WidgetBuilderType aWidget() {
        return builderOf(WidgetBuilderType.class);
    }

    @ConstructorParameter(0)
    R withProp(String prop);

    R withProp(Builder<? extends String> propBuilder);

    BuilderProperty<String> getProp();

    default Supplier<String> getDefaultProp() {
        return singleValuePicker(null);
    }

    @Builds(Widget.class)
    interface WidgetBuilderType extends WidgetBuilder<WidgetBuilderType, Widget> {
    }
}
