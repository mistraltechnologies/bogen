import Widget.Box;
import Widget.Final;
import Widget.X;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;

import static com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator.builderOf;

@Builds(Widget.class)
public interface WidgetBuilder extends TwoPhaseBuilder<Widget> {
    static WidgetBuilder aWidget() {
        return builderOf(WidgetBuilder.class);
    }

    static WidgetBuilder aWidgetFrom(final Widget template) {
        return builderOf(WidgetBuilder.class).from(template);
    }

    WidgetBuilder from(Widget template);

    WidgetBuilder withFinalType(Final finalType);

    WidgetBuilder withFinalType(Builder<? extends Final> finalTypeBuilder);

    WidgetBuilder withGenerics(Box<X> generics);

    WidgetBuilder withGenerics(Builder<? extends Box<X>> genericsBuilder);

    WidgetBuilder withMultiDimensionalArray(int[][] multiDimensionalArray);

    WidgetBuilder withMultiDimensionalArray(Builder<? extends int[][]> multiDimensionalArrayBuilder);

    WidgetBuilder withNestedWildcardGenerics(Box<Box<? super X>> nestedWildcardGenerics);

    WidgetBuilder withNestedWildcardGenerics(Builder<? extends Box<Box<? super X>>> nestedWildcardGenericsBuilder);

    WidgetBuilder withPrimitive(boolean primitive);

    WidgetBuilder withPrimitive(Builder<? extends Boolean> primitiveBuilder);

    WidgetBuilder withRecursiveType(Widget recursiveType);

    WidgetBuilder withRecursiveType(Builder<? extends Widget> recursiveTypeBuilder);

    WidgetBuilder withUnboundedGenerics(Box<?> unboundedGenerics);

    WidgetBuilder withUnboundedGenerics(Builder<? extends Box<?>> unboundedGenericsBuilder);

    WidgetBuilder withWildcardGenerics(Box<? extends X> wildcardGenerics);

    WidgetBuilder withWildcardGenerics(Builder<? extends Box<? extends X>> wildcardGenericsBuilder);
}
