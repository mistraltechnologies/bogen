import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder<R extends WidgetBuilder<R, T>, T extends Widget> extends TwoPhaseBuilder<T> {
    static WidgetBuilder<?, Widget> aWidget() {
        return builderOf(WidgetBuilder.class);
    }

    @ConstructorParameter(0)
    R withProp(String prop);

    R withProp(Builder<? extends String> propBuilder);
}
