import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import java.util.function.Supplier;

import static com.mistraltech.bog.core.picker.NaturalDefaultValuePicker.naturalDefault;
import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder<P1> extends TwoPhaseBuilder<Widget<P1>> {
    @SuppressWarnings("unchecked")
    static <P1> WidgetBuilder<P1> aWidget() {
        return builderOf(WidgetBuilder.class);
    }

    @SuppressWarnings("unchecked")
    static <P1> WidgetBuilder<P1> aWidgetFrom(final Widget<P1> template) {
        return (WidgetBuilder<P1>) builderOf(WidgetBuilder.class).from(template);
    }

    WidgetBuilder<P1> from(Widget<P1> template);

    @ConstructorParameter(0)
    WidgetBuilder<P1> withContents(P1 contents);

    WidgetBuilder<P1> withContents(Builder<? extends P1> contentsBuilder);

    BuilderProperty<P1> getContents();

    default Supplier<P1> getDefaultContents() {
        return naturalDefault(P1.class);
    }
}
