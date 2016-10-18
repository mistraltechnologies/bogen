package com.mistraltech.bogen.plugin;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.mistraltech.bogen.codegenerator.buildergenerator.BuilderGeneratorProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class BuilderGeneratorOptionsDialog extends DialogWrapper implements BuilderGeneratorOptionsPanelDataSource {
    private static final String RECENTS_KEY = "BuilderGeneratorOptionsDialog.RecentsKey";

    private final Project project;

    private final PsiClass builtClass;

    private final BuilderGeneratorOptionsPanel builderGeneratorOptionsPanel;

    private final List<VirtualFile> candidateSourceRoots;

    private final BuilderGeneratorProperties generatorProperties;

    private final List<PsiMethod> candidateConstructors;

    public BuilderGeneratorOptionsDialog(@NotNull Project project,
            @NotNull PsiClass builtClass,
            @NotNull List<VirtualFile> candidateSourceRoots,
            @NotNull BuilderGeneratorProperties generatorProperties) {
        super(project);

        this.generatorProperties = generatorProperties;
        setModal(true);
        setTitle("Generate Builder");

        this.project = project;
        this.builtClass = builtClass;
        this.candidateSourceRoots = candidateSourceRoots;
        this.candidateConstructors = Arrays.asList(builtClass.getConstructors());

        this.builderGeneratorOptionsPanel = new BuilderGeneratorOptionsPanel(this);

        init();
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();

        final VirtualFile sourceRoot = builderGeneratorOptionsPanel.getSelectedSourceRoot();

        generatorProperties.setClassName(builderGeneratorOptionsPanel.getSelectedClassName())
                .setFactoryMethodPrefix(builderGeneratorOptionsPanel.isAn() ? "an" : "a")
                .setExtensible(builderGeneratorOptionsPanel.isMakeExtensible())
                .setBuilderSuperClassName(builderGeneratorOptionsPanel.getSuperClassName())
                .setPackageName(builderGeneratorOptionsPanel.getSelectedPackageName())
                .setGenerateInterface(builderGeneratorOptionsPanel.isGenerateInterface())
                .setGenerateFactoryMethods(builderGeneratorOptionsPanel.isGenerateFactoryMethods())
                .setConstructor(builderGeneratorOptionsPanel.getSelectedConstructor())
                .setSourceRoot(sourceRoot);
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        ValidationInfo validationInfo = builderGeneratorOptionsPanel.doValidate();

        if (validationInfo != null) {
            return validationInfo;
        }

        return super.doValidate();
    }

    @Nullable
    protected JComponent createCenterPanel() {
        return builderGeneratorOptionsPanel.getRoot();
    }

    @NotNull
    @Override
    public Project getProject() {
        return project;
    }

    @NotNull
    @Override
    public String getRecentsKey() {
        return RECENTS_KEY;
    }

    @NotNull
    @Override
    public String getPackageName() {
        return generatorProperties.getPackageName();
    }

    @NotNull
    public PsiClass getBuiltClass() {
        return builtClass;
    }

    @NotNull
    @Override
    public String getDefaultClassName() {
        return generatorProperties.getClassName();
    }

    @NotNull
    @Override
    public VirtualFile getDefaultRoot() {
        return generatorProperties.getSourceRoot();
    }

    @NotNull
    @Override
    public List<VirtualFile> getCandidateRoots() {
        return candidateSourceRoots;
    }

    @NotNull
    @Override
    public List<PsiMethod> getCandidateConstructors() {
        return candidateConstructors;
    }

    @Override
    public boolean getDefaultIsExtensible() {
        return generatorProperties.isExtensible();
    }
}
