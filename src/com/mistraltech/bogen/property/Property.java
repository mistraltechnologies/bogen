package com.mistraltech.bogen.property;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeVisitor;
import com.mistraltech.bogen.utils.NameUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class Property {
    private String name;

    private final PsiType type;

    private final Optional<PsiMethod> accessorMethod;

    private final Optional<PsiMethod> mutatorMethod;

    /**
     * Construct a property from its accessor method.
     *
     * @param name the property name
     * @param type the property type
     * @param accessorMethod the accessor method (e.g. getFoo or isBar)
     * @param mutatorMethod the mutator method (e.g. setFoo)
     */
    Property(@NotNull String name, @NotNull PsiType type, PsiMethod accessorMethod, PsiMethod mutatorMethod) {
        this.name = name;
        this.type = type;
        this.accessorMethod = Optional.ofNullable(accessorMethod);
        this.mutatorMethod = Optional.ofNullable(mutatorMethod);
    }

    /**
     * Gets the name of the property.
     *
     * @return the property name
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Gets a name that can be used for a field that represents the property.
     * This is the same as the property name, unless the property name begins
     * with a capital letter (the result of the property name starting with initials),
     * in which case the first letter is converted to lower case.
     *
     * @return the property name
     */
    @NotNull
    public String getFieldName() {
        return NameUtils.deCapitalise(name);
    }

    /**
     * Gets the name of the property, with initial letter capitalised (provided it was a capital letter
     * in the accessor method name).
     *
     * @return the property name
     */
    @NotNull
    public String getCapitalisedName() {
        return StringUtil.capitalize(name);
    }

    /**
     * Gets the name of the accessor method.
     *
     * @return the accessor method name
     */
    @NotNull
    public Optional<String> getAccessorName() {
        return accessorMethod.map(PsiMethod::getName);
    }

    /**
     * Gets the name of the mutator method.
     *
     * @return the mutator method name
     */
    @NotNull
    public Optional<String> getMutatorName() {
        return mutatorMethod.map(PsiMethod::getName);
    }

    /**
     * Gets the name of the property type (the return type of the accessor method).
     *
     * @return the name of the property type
     */
    @NotNull
    public String getType() {
        return type.getCanonicalText();
    }

    /**
     * Gets the internal Psi type of the property.
     *
     * @return the PsiType of the property
     */
    @NotNull
    public PsiType getPsiType() {
        return type;
    }

    /**
     * Accept a visitor on the property type.
     *
     * @return the result returned by the visitor
     */
    @Nullable
    public <T> T accept(PsiTypeVisitor<T> visitor) {
        return type.accept(visitor);
    }
}
