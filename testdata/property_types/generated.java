import Widget.Box;
import Widget.Final;
import Widget.X;
import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.core.annotation.Builds;

@Builds(Widget.class)
public final class WidgetBuilder extends AbstractBuilder<Widget> {
    private final ValueContainer<Final> finalTypeBuilder = new ValueContainer<>();
    private final ValueContainer<Box<X>> genericsBuilder = new ValueContainer<>();
    private final ValueContainer<int[][]> multiDimensionalArrayBuilder = new ValueContainer<>();
    private final ValueContainer<Box<Box<? super X>>> nestedWildcardGenericsBuilder = new ValueContainer<>();
    private final ValueContainer<Boolean> primitiveBuilder = new ValueContainer<>(boolean.class);
    private final ValueContainer<Widget> recursiveTypeBuilder = new ValueContainer<>();
    private final ValueContainer<Box<?>> unboundedGenericsBuilder = new ValueContainer<>();
    private final ValueContainer<Box<? extends X>> wildcardGenericsBuilder = new ValueContainer<>();

    private WidgetBuilder(final Widget template) {
        super();
        if (template != null) {
        }
    }

    public static WidgetBuilder aWidget() {
        return new WidgetBuilder(null);
    }

    public static WidgetBuilder aWidgetFrom(final Widget template) {
        return new WidgetBuilder(template);
    }

    public WidgetBuilder withFinalType(final Final finalType) {
        this.finalTypeBuilder.set(finalType);
        return this;
    }

    public WidgetBuilder withFinalType(final Builder<? extends Final> finalTypeBuilder) {
        this.finalTypeBuilder.set(finalTypeBuilder);
        return this;
    }

    public WidgetBuilder withGenerics(final Box<X> generics) {
        this.genericsBuilder.set(generics);
        return this;
    }

    public WidgetBuilder withGenerics(final Builder<? extends Box<X>> genericsBuilder) {
        this.genericsBuilder.set(genericsBuilder);
        return this;
    }

    public WidgetBuilder withMultiDimensionalArray(final int[][] multiDimensionalArray) {
        this.multiDimensionalArrayBuilder.set(multiDimensionalArray);
        return this;
    }

    public WidgetBuilder withMultiDimensionalArray(final Builder<? extends int[][]> multiDimensionalArrayBuilder) {
        this.multiDimensionalArrayBuilder.set(multiDimensionalArrayBuilder);
        return this;
    }

    public WidgetBuilder withNestedWildcardGenerics(final Box<Box<? super X>> nestedWildcardGenerics) {
        this.nestedWildcardGenericsBuilder.set(nestedWildcardGenerics);
        return this;
    }

    public WidgetBuilder withNestedWildcardGenerics(final Builder<? extends Box<Box<? super X>>> nestedWildcardGenericsBuilder) {
        this.nestedWildcardGenericsBuilder.set(nestedWildcardGenericsBuilder);
        return this;
    }

    public WidgetBuilder withPrimitive(final boolean primitive) {
        this.primitiveBuilder.set(primitive);
        return this;
    }

    public WidgetBuilder withPrimitive(final Builder<? extends Boolean> primitiveBuilder) {
        this.primitiveBuilder.set(primitiveBuilder);
        return this;
    }

    public WidgetBuilder withRecursiveType(final Widget recursiveType) {
        this.recursiveTypeBuilder.set(recursiveType);
        return this;
    }

    public WidgetBuilder withRecursiveType(final Builder<? extends Widget> recursiveTypeBuilder) {
        this.recursiveTypeBuilder.set(recursiveTypeBuilder);
        return this;
    }

    public WidgetBuilder withUnboundedGenerics(final Box<?> unboundedGenerics) {
        this.unboundedGenericsBuilder.set(unboundedGenerics);
        return this;
    }

    public WidgetBuilder withUnboundedGenerics(final Builder<? extends Box<?>> unboundedGenericsBuilder) {
        this.unboundedGenericsBuilder.set(unboundedGenericsBuilder);
        return this;
    }

    public WidgetBuilder withWildcardGenerics(final Box<? extends X> wildcardGenerics) {
        this.wildcardGenericsBuilder.set(wildcardGenerics);
        return this;
    }

    public WidgetBuilder withWildcardGenerics(final Builder<? extends Box<? extends X>> wildcardGenericsBuilder) {
        this.wildcardGenericsBuilder.set(wildcardGenericsBuilder);
        return this;
    }

    @Override
    protected Widget construct() {
        return new Widget();
    }

    @Override
    protected void assign(final Widget instance) {
        super.assign(instance);
        instance.setFinalType(finalTypeBuilder.value());
        instance.setGenerics(genericsBuilder.value());
        instance.setMultiDimensionalArray(multiDimensionalArrayBuilder.value());
        instance.setNestedWildcardGenerics(nestedWildcardGenericsBuilder.value());
        instance.setPrimitive(primitiveBuilder.value());
        instance.setRecursiveType(recursiveTypeBuilder.value());
        instance.setUnboundedGenerics(unboundedGenericsBuilder.value());
        instance.setWildcardGenerics(wildcardGenericsBuilder.value());
    }

    @Override
    protected void postUpdate() {
        super.postUpdate();
        finalTypeBuilder.reset();
        genericsBuilder.reset();
        multiDimensionalArrayBuilder.reset();
        nestedWildcardGenericsBuilder.reset();
        primitiveBuilder.reset();
        recursiveTypeBuilder.reset();
        unboundedGenericsBuilder.reset();
        wildcardGenericsBuilder.reset();
    }
}
