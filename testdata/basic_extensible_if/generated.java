import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;
import com.mistraltech.bog.core.annotation.ConstructorParameter;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder<R extends WidgetBuilder<R, T>, T extends Widget> extends TwoPhaseBuilder<T> {
    @SuppressWarnings("unchecked")
    static WidgetBuilderType aWidget() {
        return builderOf(WidgetBuilderType.class);
    }

    @SuppressWarnings("unchecked")
    static WidgetBuilderType aWidgetFrom(final Widget template) {
        return (WidgetBuilderType) builderOf(WidgetBuilderType.class).from(template);
    }

    R from(Widget template);

    R withProp(String prop);

    R withProp(Builder<? extends String> propBuilder);

    @ConstructorParameter(0)
    R withProp1(String prop1);

    R withProp1(Builder<? extends String> prop1Builder);

    BuilderProperty<String> getProp();

    BuilderProperty<String> getProp1();

    @Builds(Widget.class)
    interface WidgetBuilderType extends WidgetBuilder<WidgetBuilderType, Widget> {
    }
}
