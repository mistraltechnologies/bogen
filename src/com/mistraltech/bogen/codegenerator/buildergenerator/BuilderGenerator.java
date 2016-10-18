package com.mistraltech.bogen.codegenerator.buildergenerator;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.search.GlobalSearchScope;
import com.mistraltech.bogen.codegenerator.Generator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BuilderGenerator extends Generator {

    public static final String BUILDER_SUPER_INTERFACE = "com.mistraltech.bog.core.TwoPhaseBuilder";

    private final BuilderGeneratorProperties generatorProperties;

    public BuilderGenerator(@NotNull BuilderGeneratorProperties generatorProperties) {
        super(generatorProperties);
        this.generatorProperties = generatorProperties;
    }

    @Override
    protected List<String> checkProperties() {
        List<String> warnings = new ArrayList<>();

        warnings.addAll(super.checkProperties());

        checkSuperType(warnings);

        return warnings;
    }

    private void checkSuperType(List<String> warnings) {
        final Project project = generatorProperties.getProject();
        final String defaultSuperClassName = generatorProperties.isGenerateInterface() ?
                BUILDER_SUPER_INTERFACE : generatorProperties.getBaseClassName();

        String superClassName = generatorProperties.getBuilderSuperClassName() != null ?
                generatorProperties.getBuilderSuperClassName() : defaultSuperClassName;

        if (superClassName != null) {
            final PsiClass superClass = findPsiClass(project, superClassName);

            if (superClass == null) {
                warnings.add("Supertype '" + superClassName + "' was not found in the project.");
            } else {
                checkSuperClassModifiers(superClass, warnings);
                checkSuperClassInheritance(superClass, defaultSuperClassName, warnings);
            }
        }
    }

    private PsiClass findPsiClass(Project project, String className) {
        return JavaPsiFacade.getInstance(project).findClass(className, GlobalSearchScope.allScope(project));
    }

    private void checkSuperClassModifiers(PsiClass superClass, List<String> warnings) {
        final PsiModifierList modifierList = superClass.getModifierList();
        if (modifierList != null && modifierList.hasExplicitModifier(PsiModifier.FINAL)) {
            warnings.add("Supertype '" + superClass.getQualifiedName() + "' is final.");
        }
    }

    private void checkSuperClassInheritance(PsiClass superClass, String baseClassName, List<String> warnings) {
        final Project project = generatorProperties.getProject();

        PsiClass baseClass = findPsiClass(project, baseClassName);
        if (baseClass == null) {
            warnings.add("Could not find base type '" + baseClassName + "' in the project.");
        } else if (!baseClass.equals(superClass)) {
            final PsiClassType[] superTypes = superClass.getSuperTypes();

            boolean foundBaseClass = false;
            for (PsiClassType superType : superTypes) {
                final PsiClass resolve = superType.resolve();
                if (baseClass.equals(resolve)) {
                    foundBaseClass = true;
                    break;
                }
            }

            if (!foundBaseClass) {
                warnings.add("Supertype '" + superClass.getQualifiedName() + "' does not extend '" + baseClass.getQualifiedName() + "'.");
            }
        }
    }
}
