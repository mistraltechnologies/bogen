import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;

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
}
