package com.mistraltech.bogen.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.ClassUtil;
import com.mistraltech.bogen.codegenerator.buildergenerator.BuilderGenerator;
import com.mistraltech.bogen.codegenerator.buildergenerator.BuilderGeneratorProperties;
import com.mistraltech.bogen.utils.ActionEventUtils;
import com.mistraltech.bogen.utils.PsiUtils;
import com.mistraltech.bogen.utils.SourceRootUtils;

import java.util.List;

public class GenerateBuilderAction extends AnAction {

    @Override
    public void update(AnActionEvent event) {
        boolean enabled = ActionEventUtils.hasProject(event)
                && ActionEventUtils.hasFile(event)
                && ActionEventUtils.hasSelectedClass(event);

        Presentation presentation = event.getPresentation();
        presentation.setEnabled(enabled);
        presentation.setVisible(true);
    }

    public void actionPerformed(AnActionEvent event) {
        final Project project = ActionEventUtils.getTargetProject(event);
        final PsiFile selectedFile = ActionEventUtils.getTargetFile(event);
        final PsiClass selectedClass = PsiUtils.getSelectedClass(event)
                .orElseThrow(() -> new IllegalStateException("Cannot perform action without a selected class"));

        // Note that module may be null if the selected class is in a library rather than the source tree
        final Module module = event.getData(LangDataKeys.MODULE);

        final ConfigurationProperties configurationProperties = new ConfigurationProperties(project);

        // Guess what we are going to call the target builder class and what its package will be
        String preferredClassName = configurationProperties.getBuilderClassNamePrefix() +
                selectedClass.getName() +
                configurationProperties.getBuilderClassNameSuffix();

        String preferredPackageName = ClassUtil.extractPackageName(selectedClass.getQualifiedName());

        // Get the list of available source roots
        List<VirtualFile> candidateSourceRoots = SourceRootUtils.getSourceAndTestSourceRoots(project);

        if (candidateSourceRoots.isEmpty()) {
            Messages.showErrorDialog(project, "No source roots have been configured. A source root is required as the target location for the generated class.", "No Source Root");
            return;
        }

        // Guess what the target source root might be
        VirtualFile preferredSourceRoot = SourceRootUtils.getPreferredSourceRootForTestClass(project, module, selectedFile.getVirtualFile())
                .orElseThrow(IllegalStateException::new);

        final BuilderGeneratorProperties generatorProperties = new BuilderGeneratorProperties()
                .setProject(project)
                .setClassName(preferredClassName)
                .setPackageName(preferredPackageName)
                .setSourceRoot(preferredSourceRoot)
                .setSourceClass(selectedClass)
                .setFactoryMethodSuffix(configurationProperties.getFactoryMethodSuffix())
                .setTemplateFactoryMethodSuffix(configurationProperties.getTemplateFactoryMethodSuffix())
                .setSetterPrefix(configurationProperties.getSetterPrefix())
                .setSetterSuffix(configurationProperties.getSetterSuffix())
                .setExtensible(configurationProperties.isMakeExtensible())
                .setBaseClassName(configurationProperties.getBaseClass())
                .setGenerateTemplateFactoryMethod(configurationProperties.isGenerateTemplateFactoryMethod())
                .setMakeMethodParametersFinal(configurationProperties.isMakeMethodParametersFinal());

        BuilderGeneratorOptionsDialog builderGeneratorOptionsDialog = new BuilderGeneratorOptionsDialog(
                project, selectedClass, candidateSourceRoots, generatorProperties);

        builderGeneratorOptionsDialog.show();

        if (builderGeneratorOptionsDialog.isOK()) {
            new BuilderGenerator(generatorProperties).generate();
        }
    }
}
