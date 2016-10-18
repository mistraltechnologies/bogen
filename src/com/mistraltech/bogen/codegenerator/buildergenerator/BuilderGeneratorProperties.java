package com.mistraltech.bogen.codegenerator.buildergenerator;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiMethod;
import com.mistraltech.bogen.codegenerator.CodeWriter;
import com.mistraltech.bogen.codegenerator.JavaGeneratorProperties;

import java.util.Optional;
import java.util.stream.Stream;

public class BuilderGeneratorProperties extends JavaGeneratorProperties<BuilderGeneratorProperties> {
    private String factoryMethodPrefix;

    private boolean extensible;

    private String builderSuperClassName;

    private CodeWriter builderGeneratorCodeWriter;

    private PsiClass sourceClass;

    private PsiMethod constructor;

    private String baseClassName;

    private String factoryMethodSuffix;

    private String templateFactoryMethodSuffix;

    private String setterPrefix;

    private String setterSuffix;

    private boolean generateTemplateFactoryMethod;

    private boolean makeMethodParametersFinal;

    private boolean generateInterface;

    private boolean generateFactoryMethods;

    public BuilderGeneratorProperties() {
    }

    public String getFactoryMethodPrefix() {
        return factoryMethodPrefix;
    }

    public BuilderGeneratorProperties setFactoryMethodPrefix(String factoryMethodPrefix) {
        this.factoryMethodPrefix = factoryMethodPrefix;
        return self();
    }

    public boolean isExtensible() {
        return extensible;
    }

    public BuilderGeneratorProperties setExtensible(boolean extensible) {
        this.extensible = extensible;
        return self();
    }

    public String getBuilderSuperClassName() {
        return builderSuperClassName;
    }

    public BuilderGeneratorProperties setBuilderSuperClassName(String builderSuperClassName) {
        this.builderSuperClassName = builderSuperClassName;
        return self();
    }

    public PsiClass getSourceClass() {
        return sourceClass;
    }

    public BuilderGeneratorProperties setSourceClass(PsiClass sourceClass) {
        this.sourceClass = sourceClass;
        return self();
    }

    public PsiMethod getConstructor() {
        return constructor;
    }

    public BuilderGeneratorProperties setConstructor(PsiMethod constructor) {
        this.constructor = constructor;
        return self();
    }

    public Optional<PsiClassType> getSourceSuperClassType() {
        final PsiClassType[] extendsListTypes = sourceClass.getExtendsListTypes();
        return Stream.of(extendsListTypes).findFirst();
    }

    @Override
    public CodeWriter getCodeWriter() {
        return isGenerateInterface() ? new BuilderInterfaceCodeWriter(this) : new BuilderClassCodeWriter(this);
    }

    public String getBaseClassName() {
        return baseClassName;
    }

    public BuilderGeneratorProperties setBaseClassName(String baseClassName) {
        this.baseClassName = baseClassName;
        return self();
    }

    public String getFactoryMethodSuffix() {
        return factoryMethodSuffix;
    }

    public BuilderGeneratorProperties setFactoryMethodSuffix(String factoryMethodSuffix) {
        this.factoryMethodSuffix = factoryMethodSuffix;
        return self();
    }

    public String getTemplateFactoryMethodSuffix() {
        return templateFactoryMethodSuffix;
    }

    public BuilderGeneratorProperties setTemplateFactoryMethodSuffix(String templateFactoryMethodSuffix) {
        this.templateFactoryMethodSuffix = templateFactoryMethodSuffix;
        return self();
    }

    public String getSetterPrefix() {
        return setterPrefix;
    }

    public BuilderGeneratorProperties setSetterPrefix(String setterPrefix) {
        this.setterPrefix = setterPrefix;
        return self();
    }

    public String getSetterSuffix() {
        return setterSuffix;
    }

    public BuilderGeneratorProperties setSetterSuffix(String setterSuffix) {
        this.setterSuffix = setterSuffix;
        return self();
    }

    public boolean isGenerateTemplateFactoryMethod() {
        return generateTemplateFactoryMethod;
    }

    public BuilderGeneratorProperties setGenerateTemplateFactoryMethod(boolean generateTemplateFactoryMethod) {
        this.generateTemplateFactoryMethod = generateTemplateFactoryMethod;
        return self();
    }

    public boolean isMakeMethodParametersFinal() {
        return makeMethodParametersFinal;
    }

    public BuilderGeneratorProperties setMakeMethodParametersFinal(boolean makeMethodParametersFinal) {
        this.makeMethodParametersFinal = makeMethodParametersFinal;
        return self();
    }

    public boolean isGenerateInterface() {
        return generateInterface;
    }

    public BuilderGeneratorProperties setGenerateInterface(boolean generateInterface) {
        this.generateInterface = generateInterface;
        return self();
    }

    public boolean isGenerateFactoryMethods() {
        return generateFactoryMethods;
    }

    public BuilderGeneratorProperties setGenerateFactoryMethods(boolean generateFactoryMethods) {
        this.generateFactoryMethods = generateFactoryMethods;
        return self();
    }
}
