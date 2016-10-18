package com.mistraltech.bogen.plugin;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;

public class ConfigurationProperties {
    private static final String BUILDER_CLASS_NAME_PREFIX = "bogen.builderPrefix";

    private static final String BUILDER_CLASS_NAME_PREFIX_DEFAULT = "";

    private static final String BUILDER_CLASS_NAME_SUFFIX = "bogen.builderSuffix";

    private static final String BUILDER_CLASS_NAME_SUFFIX_DEFAULT = "Builder";

    private static final String FACTORY_METHOD_SUFFIX = "bogen.factoryMethodSuffix";

    private static final String FACTORY_METHOD_SUFFIX_DEFAULT = "";

    private static final String TEMPLATE_FACTORY_METHOD_SUFFIX = "bogen.templateFactoryMethodSuffix";

    private static final String TEMPLATE_FACTORY_METHOD_SUFFIX_DEFAULT = "From";

    private static final String SETTER_PREFIX = "bogen.setterPrefix";

    private static final String SETTER_PREFIX_DEFAULT = "with";

    private static final String SETTER_SUFFIX = "bogen.setterSuffix";

    private static final String SETTER_SUFFIX_DEFAULT = "";

    private static final String GENERATE_TEMPLATE_FACTORY_METHOD = "bogen.generateTemplateFactoryMethod";

    private static final String GENERATE_TEMPLATE_FACTORY_METHOD_DEFAULT = Boolean.TRUE.toString();

    private static final String MAKE_METHOD_PARAMETERS_FINAL = "bogen.makeMethodParametersFinal";

    private static final String MAKE_METHOD_PARAMETERS_FINAL_DEFAULT = Boolean.TRUE.toString();

    private static final String BASE_CLASS = "bogen.baseClass";

    private static final String BASE_CLASS_DEFAULT = "com.mistraltech.bog.core.AbstractBuilder";

    private static final String MAKE_EXTENSIBLE = "bogen.makeExtensible";

    private static final String MAKE_EXTENSIBLE_DEFAULT = Boolean.FALSE.toString();

    private PropertiesComponent properties;

    public ConfigurationProperties(Project project) {
        this.properties = PropertiesComponent.getInstance(project);
    }

    public String getBuilderClassNamePrefix() {
        return properties.getValue(BUILDER_CLASS_NAME_PREFIX, BUILDER_CLASS_NAME_PREFIX_DEFAULT);
    }

    public void setBuilderClassNamePrefix(String prefix) {
        properties.setValue(BUILDER_CLASS_NAME_PREFIX, prefix);
    }

    public String getBuilderClassNameSuffix() {
        return properties.getValue(BUILDER_CLASS_NAME_SUFFIX, BUILDER_CLASS_NAME_SUFFIX_DEFAULT);
    }

    public void setBuilderClassNameSuffix(String suffix) {
        properties.setValue(BUILDER_CLASS_NAME_SUFFIX, suffix);
    }

    public String getFactoryMethodSuffix() {
        return properties.getValue(FACTORY_METHOD_SUFFIX, FACTORY_METHOD_SUFFIX_DEFAULT);
    }

    public void setFactoryMethodSuffix(String suffix) {
        properties.setValue(FACTORY_METHOD_SUFFIX, suffix);
    }

    public String getTemplateFactoryMethodSuffix() {
        return properties.getValue(TEMPLATE_FACTORY_METHOD_SUFFIX, TEMPLATE_FACTORY_METHOD_SUFFIX_DEFAULT);
    }

    public void setTemplateFactoryMethodSuffix(String suffix) {
        properties.setValue(TEMPLATE_FACTORY_METHOD_SUFFIX, suffix);
    }

    public String getSetterPrefix() {
        return properties.getValue(SETTER_PREFIX, SETTER_PREFIX_DEFAULT);
    }

    public void setSetterPrefix(String prefix) {
        properties.setValue(SETTER_PREFIX, prefix);
    }

    public String getSetterSuffix() {
        return properties.getValue(SETTER_SUFFIX, SETTER_SUFFIX_DEFAULT);
    }

    public void setSetterSuffix(String suffix) {
        properties.setValue(SETTER_SUFFIX, suffix);
    }

    public boolean isGenerateTemplateFactoryMethod() {
        String value = properties.getValue(GENERATE_TEMPLATE_FACTORY_METHOD, GENERATE_TEMPLATE_FACTORY_METHOD_DEFAULT);
        return Boolean.valueOf(value);
    }

    public void setGenerateTemplateFactoryMethod(boolean generateTemplateFactoryMethod) {
        properties.setValue(GENERATE_TEMPLATE_FACTORY_METHOD, Boolean.toString(generateTemplateFactoryMethod));
    }

    public boolean isMakeMethodParametersFinal() {
        String value = properties.getValue(MAKE_METHOD_PARAMETERS_FINAL, MAKE_METHOD_PARAMETERS_FINAL_DEFAULT);
        return Boolean.valueOf(value);
    }

    public void setMakeMethodParametersFinal(boolean makeMethodParametersFinal) {
        properties.setValue(MAKE_METHOD_PARAMETERS_FINAL, Boolean.toString(makeMethodParametersFinal));
    }

    public String getBaseClass() {
        return properties.getValue(BASE_CLASS, BASE_CLASS_DEFAULT);
    }

    public void setBaseClass(String baseClass) {
        properties.setValue(BASE_CLASS, baseClass);
    }

    public boolean isMakeExtensible() {
        String value = properties.getValue(MAKE_EXTENSIBLE, MAKE_EXTENSIBLE_DEFAULT);
        return Boolean.valueOf(value);
    }

    public void setMakeExtensible(boolean makeExtensible) {
        properties.setValue(MAKE_EXTENSIBLE, Boolean.toString(makeExtensible));
    }

    public void reset() {
        setBuilderClassNamePrefix(BUILDER_CLASS_NAME_PREFIX_DEFAULT);
        setBuilderClassNameSuffix(BUILDER_CLASS_NAME_SUFFIX_DEFAULT);
        setFactoryMethodSuffix(FACTORY_METHOD_SUFFIX_DEFAULT);
        setTemplateFactoryMethodSuffix(TEMPLATE_FACTORY_METHOD_SUFFIX_DEFAULT);
        setSetterPrefix(SETTER_PREFIX_DEFAULT);
        setSetterSuffix(SETTER_SUFFIX_DEFAULT);
        setGenerateTemplateFactoryMethod(Boolean.valueOf(GENERATE_TEMPLATE_FACTORY_METHOD_DEFAULT));
        setMakeMethodParametersFinal(Boolean.valueOf(MAKE_METHOD_PARAMETERS_FINAL_DEFAULT));
        setBaseClass(BASE_CLASS_DEFAULT);
        setMakeExtensible(Boolean.valueOf(MAKE_EXTENSIBLE_DEFAULT));
    }
}
