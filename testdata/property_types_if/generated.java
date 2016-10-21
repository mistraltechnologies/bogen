import Widget.Box;
import Widget.Final;
import Widget.X;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.BuilderProperty;
import com.mistraltech.bog.core.TwoPhaseBuilder;
import com.mistraltech.bog.core.annotation.Builds;

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

    BuilderProperty<Final> getFinalType();

    BuilderProperty<Box<X>> getGenerics();

    BuilderProperty<int[][]> getMultiDimensionalArray();

    BuilderProperty<Box<Box<? super X>>> getNestedWildcardGenerics();

    BuilderProperty<Boolean> getPrimitive();

    BuilderProperty<Widget> getRecursiveType();

    BuilderProperty<Box<?>> getUnboundedGenerics();

    BuilderProperty<Box<? extends X>> getWildcardGenerics();

    default Supplier<Final> getDefaultFinalType() {
        return singleValuePicker(null);
    }

    default Supplier<Box<X>> getDefaultGenerics() {
        return singleValuePicker(null);
    }

    default Supplier<int[][]> getDefaultMultiDimensionalArray() {
        return singleValuePicker(null);
    }

    default Supplier<Box<Box<? super X>>> getDefaultNestedWildcardGenerics() {
        return singleValuePicker(null);
    }

    default Supplier<Boolean> getDefaultPrimitive() {
        return singleValuePicker(null);
    }

    default Supplier<Widget> getDefaultRecursiveType() {
        return singleValuePicker(null);
    }

    default Supplier<Box<?>> getDefaultUnboundedGenerics() {
        return singleValuePicker(null);
    }

    default Supplier<Box<? extends X>> getDefaultWildcardGenerics() {
        return singleValuePicker(null);
    }
}
