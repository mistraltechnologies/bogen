package com.mistraltech.bogen.plugin;

import com.intellij.openapi.project.Project;

import javax.swing.*;

public class BogenConfiguration {

    private final Project project;

    private JPanel mainPanel;

    private JTextField builderClassNameSuffixTextField;

    private JTextField builderClassNamePrefixTextField;

    private JCheckBox generateTemplateFactoryMethodCheckBox;

    private JCheckBox makeMethodParametersFinalCheckBox;

    private JTextField baseClassTextField;

    private JCheckBox makeExtensibleCheckBox;

    private String currentBuilderClassNamePrefix;

    private String currentBuilderClassNameSuffix;

    private String currentFactoryMethodSuffix;

    private String currentTemplateFactoryMethodSuffix;

    private String currentSetterPrefix;

    private String currentSetterSuffix;

    private boolean currentGenerateTemplateFactoryMethod;

    private boolean currentMakeMethodParametersFinal;

    private String currentBaseClass;

    private boolean currentMakeExtensible;

    public BogenConfiguration(Project project) {
        this.project = project;
        init();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public boolean isModified() {
        return !(
                builderClassNamePrefixTextField.getText().equals(currentBuilderClassNamePrefix) &&
                        builderClassNameSuffixTextField.getText().equals(currentBuilderClassNameSuffix) &&
                        generateTemplateFactoryMethodCheckBox.isSelected() == currentGenerateTemplateFactoryMethod &&
                        makeMethodParametersFinalCheckBox.isSelected() == currentMakeMethodParametersFinal &&
                        baseClassTextField.getText().equals(currentBaseClass) &&
                        makeExtensibleCheckBox.isSelected() == currentMakeExtensible
        );
    }

    private void init() {
        ConfigurationProperties properties = new ConfigurationProperties(project);

        currentBuilderClassNamePrefix = properties.getBuilderClassNamePrefix();
        currentBuilderClassNameSuffix = properties.getBuilderClassNameSuffix();
        currentFactoryMethodSuffix = properties.getFactoryMethodSuffix();
        currentTemplateFactoryMethodSuffix = properties.getTemplateFactoryMethodSuffix();
        currentSetterPrefix = properties.getSetterPrefix();
        currentSetterSuffix = properties.getSetterSuffix();
        currentGenerateTemplateFactoryMethod = properties.isGenerateTemplateFactoryMethod();
        currentMakeMethodParametersFinal = properties.isMakeMethodParametersFinal();
        currentBaseClass = properties.getBaseClass();
        currentMakeExtensible = properties.isMakeExtensible();

        reset();
    }

    public void save() {
        ConfigurationProperties properties = new ConfigurationProperties(project);

        currentBuilderClassNamePrefix = builderClassNamePrefixTextField.getText();
        currentBuilderClassNameSuffix = builderClassNameSuffixTextField.getText();
        currentGenerateTemplateFactoryMethod = generateTemplateFactoryMethodCheckBox.isSelected();
        currentMakeMethodParametersFinal = makeMethodParametersFinalCheckBox.isSelected();
        currentBaseClass = baseClassTextField.getText();
        currentMakeExtensible = makeExtensibleCheckBox.isSelected();

        properties.setBuilderClassNamePrefix(currentBuilderClassNamePrefix);
        properties.setBuilderClassNameSuffix(currentBuilderClassNameSuffix);
        properties.setFactoryMethodSuffix(currentFactoryMethodSuffix);
        properties.setTemplateFactoryMethodSuffix(currentTemplateFactoryMethodSuffix);
        properties.setSetterPrefix(currentSetterPrefix);
        properties.setSetterSuffix(currentSetterSuffix);
        properties.setGenerateTemplateFactoryMethod(currentGenerateTemplateFactoryMethod);
        properties.setMakeMethodParametersFinal(currentMakeMethodParametersFinal);
        properties.setBaseClass(currentBaseClass);
        properties.setMakeExtensible(currentMakeExtensible);
    }

    public void reset() {
        builderClassNamePrefixTextField.setText(currentBuilderClassNamePrefix);
        builderClassNameSuffixTextField.setText(currentBuilderClassNameSuffix);
        generateTemplateFactoryMethodCheckBox.setSelected(currentGenerateTemplateFactoryMethod);
        makeMethodParametersFinalCheckBox.setSelected(currentMakeMethodParametersFinal);
        baseClassTextField.setText(currentBaseClass);
        makeExtensibleCheckBox.setSelected(currentMakeExtensible);
    }
}
