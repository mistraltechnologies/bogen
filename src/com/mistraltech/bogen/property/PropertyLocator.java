package com.mistraltech.bogen.property;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.util.PropertyUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public final class PropertyLocator {
    /**
     * Identifies the list of properties on the supplied class and optionally its superclasses.
     * Properties are located through their setters, ensuring that all properties are writable.
     * Located properties may or may not also have getters.
     *
     * @param owner the class to be scanned for properties
     * @param includeSuperclasses whether to include properties declared in superclasses
     * @return the list of qualifying properties
     */
    @NotNull
    public static List<Property> locatePropertiesFromSetters(@NotNull PsiClass owner, boolean includeSuperclasses) {

        Map<String, PsiMethod> propertyMap = PropertyUtil.getAllProperties(owner,
                true,    // Do accept setters
                false,   // Don't accept getters
                includeSuperclasses);

        @SuppressWarnings("ConstantConditions")
        List<Property> matchedProperties = propertyMap
                .entrySet()
                .stream()
                .map(e -> new Property(e.getKey(),
                        PropertyUtil.getPropertyType(e.getValue()),     // known to be non-null
                        PropertyUtil.findPropertyGetter(owner, e.getKey(), false, includeSuperclasses),
                        e.getValue()))
                .collect(toList());

        return matchedProperties;
    }

    /**
     * Identifies the list of properties on the supplied class and optionally its superclasses.
     * Properties are located through their getters, ensuring that all properties are readable.
     * Located properties may or may not also have setters.
     *
     * @param owner the class to be scanned for properties
     * @param includeSuperclasses whether to include properties declared in superclasses
     * @return the list of qualifying properties
     */
    @NotNull
    public static List<Property> locatePropertiesFromGetters(@NotNull PsiClass owner, boolean includeSuperclasses) {

        Map<String, PsiMethod> propertyMap = PropertyUtil.getAllProperties(owner,
                false,  // Don't accept setters
                true,   // Do accept getters
                includeSuperclasses);

        @SuppressWarnings("ConstantConditions")
        List<Property> matchedProperties = propertyMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new Property(e.getKey(),
                        PropertyUtil.getPropertyType(e.getValue()),     // known to be non-null
                        e.getValue(),
                        PropertyUtil.findPropertySetter(owner, e.getKey(), false, includeSuperclasses)))
                .collect(toList());

        return matchedProperties;
    }

    /**
     * Identifies the list of properties on the supplied class constructor.
     *
     * @param owner the class to be scanned for properties
     * @param constructor the constructor
     * @return the list of qualifying properties
     */
    @NotNull
    public static List<Property> locatePropertiesFromConstructor(@NotNull PsiClass owner, @Nullable PsiMethod constructor) {

        List<PsiParameter> parameters = Optional.ofNullable(constructor)
                .map(c -> c.getParameterList().getParameters())
                .map(Arrays::asList)
                .orElse(Collections.emptyList());

        List<Property> matchedProperties = parameters.stream()
                .map(p -> new Property(
                        requireNonNull(p.getName()),
                        p.getType(),
                        PropertyUtil.findPropertyGetter(owner, p.getName(), false, true),
                        PropertyUtil.findPropertySetter(owner, p.getName(), false, true)))
                .collect(toList());

        return matchedProperties;
    }

}

