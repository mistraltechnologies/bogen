package com.mistraltech.bogen.codegenerator.javabuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTypeBuilder<T extends AbstractTypeBuilder<T>> extends AbstractBuilder<T> {
    private String className;

    private List<TypeParameterDeclBuilder> typeParameters = new ArrayList<>();

    private List<TypeBuilder> interfaces = new ArrayList<>();

    private List<AnnotationBuilder> annotations = new ArrayList<>();

    private String accessModifier;

    public T withAccessModifier(String modifier) {
        this.accessModifier = modifier;
        return self();
    }

    public T withName(String className) {
        this.className = className;
        return self();
    }

    public T withTypeParameters(List<TypeParameterDeclBuilder> params) {
        this.typeParameters.addAll(params);
        return self();
    }

    public T withTypeParameter(TypeParameterDeclBuilder param) {
        this.typeParameters.add(param);
        return self();
    }

    public T withImplementedInterface(TypeBuilder type) {
        this.interfaces.add(type);
        return self();
    }

    public T withAnnotation(AnnotationBuilder annotation) {
        annotations.add(annotation);
        return self();
    }

    protected String getAccessModifier() {
        return accessModifier;
    }

    protected String getClassName() {
        return className;
    }

    protected List<TypeParameterDeclBuilder> getTypeParameters() {
        return typeParameters;
    }

    protected List<TypeBuilder> getInterfaces() {
        return interfaces;
    }

    protected List<AnnotationBuilder> getAnnotations() {
        return annotations;
    }

    protected abstract void writeModifiers(StringBuilder sb);
}
