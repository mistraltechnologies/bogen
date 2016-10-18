public class Widget {
    private boolean primitive;
    private Widget recursiveType;
    private Final finalType;
    private Box<X> generics;
    private Box<? extends X> wildcardGenerics;
    private Box<?> unboundedGenerics;
    private Box<Box<? super X>> nestedWildcardGenerics;
    private int[][] multiDimensionalArray;

    public Widget() {
    }

    public void setPrimitive(boolean primitive) {
        this.primitive = primitive;
    }

    public void setRecursiveType(Widget recursiveType) {
        this.recursiveType = recursiveType;
    }

    public void setFinalType(Final finalType) {
        this.finalType = finalType;
    }

    public void setGenerics(Box<X> generics) {
        this.generics = generics;
    }

    public void setWildcardGenerics(Box<? extends X> wildcardGenerics) {
        this.wildcardGenerics = wildcardGenerics;
    }

    public void setUnboundedGenerics(Box<?> unboundedGenerics) {
        this.unboundedGenerics = unboundedGenerics;
    }

    public void setNestedWildcardGenerics(Box<Box<? super X>> nestedWildcardGenerics) {
        this.nestedWildcardGenerics = nestedWildcardGenerics;
    }

    public void setMultiDimensionalArray(int[][] multiDimensionalArray) {
        this.multiDimensionalArray = multiDimensionalArray;
    }

    public class Box<T> {
    }

    public static final class Final {
    }

    public class X {
    }
}
