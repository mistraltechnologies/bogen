package com.mistraltech.bogen.codegenerator;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameter;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.mistraltech.bogen.codegenerator.buildergenerator.BuilderGenerator;
import com.mistraltech.bogen.codegenerator.buildergenerator.BuilderGeneratorProperties;
import com.mistraltech.bogen.utils.ActionUtils;
import com.mistraltech.bogen.utils.PsiUtils;
import com.mistraltech.bogen.utils.SourceRootUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public abstract class AbstractGeneratorTest extends LightCodeInsightFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return new File("").getAbsolutePath().replace(File.separatorChar, '/') + "/testdata";
    }

    protected void doTest(String testName, String inputFileName, String expectedGeneratedFileName, BuilderGeneratorProperties generatorProperties) {
        preLoadClasses();

        final PsiClass sourceClass = loadTestClassFromFile(testName, inputFileName);

        generatorProperties.setSourceRoot(getSourceRoot())
                .setSourceClass(sourceClass);

        generatorProperties.setConstructor(getConstructor(sourceClass));

        new BuilderGenerator(generatorProperties).generate();

        String expectedGeneratedFilePath = testName + "/" + expectedGeneratedFileName;
        myFixture.checkResultByFile(getGeneratedFilePath(generatorProperties), expectedGeneratedFilePath, false);
    }

    protected PsiMethod getConstructor(PsiClass sourceClass) {
        return getOnlyAvailableConstructor(sourceClass);
    }

    private PsiMethod getOnlyAvailableConstructor(PsiClass psiClass) {
        PsiMethod[] constructors = psiClass.getConstructors();

        if (constructors.length > 1) {
            throw new IllegalStateException(
                    "No constructor specified and multiple constructors found for class " + psiClass.getName());
        }

        if (constructors.length == 0) {
            return null;
        }

        return constructors[0];
    }

    protected PsiMethod findConstructor(PsiClass psiClass, String... paramTypes) {
        PsiMethod[] constructors = psiClass.getConstructors();

        Optional<PsiMethod> matchingConstructor = Arrays.stream(constructors)
                .filter(c -> isMatchingParameters(c.getParameterList().getParameters(), paramTypes))
                .findAny();

        return matchingConstructor.orElseThrow(() -> new IllegalArgumentException("No constructor found with given parameters"));
    }

    private boolean isMatchingParameters(PsiParameter[] parameters, String[] paramTypes) {
        if (parameters.length != paramTypes.length) {
            return false;
        }

        for (int i = 0; i < parameters.length; i++) {
            if (!parameters[i].getType().equalsToText(paramTypes[i])) {
                return false;
            }
        }

        return true;
    }

    protected void preLoadClasses() {
        loadDefaultBaseClass();
    }

    protected void doTest(String testName, BuilderGeneratorProperties generatorProperties) {
        doTest(testName, "input.java", "generated.java", generatorProperties);
    }

    protected String getGeneratedFilePath(BuilderGeneratorProperties generatorProperties) {
        return generatorProperties.getPackageName().replace('.', '/') + "/" + generatorProperties.getClassName() + ".java";
    }

    protected VirtualFile getSourceRoot() {
        return SourceRootUtils.getSourceAndTestSourceRoots(getProject()).get(0);
    }

    protected BuilderGeneratorProperties defaultGeneratorProperties() {
        return new BuilderGeneratorProperties()
                .setProject(getProject())
                .setPackageName("")
                .setClassName("WidgetBuilder")
                .setExtensible(false)
                .setFactoryMethodPrefix("a")
                .setSetterPrefix("with")
                .setSetterSuffix("")
                .setBaseClassName("com.mistraltech.bog.core.AbstractBuilder")
                .setExtensible(false)
                .setFactoryMethodSuffix("")
                .setTemplateFactoryMethodSuffix("From")
                .setGenerateTemplateFactoryMethod(true)
                .setMakeMethodParametersFinal(true)
                .setGenerateFactoryMethods(true);
    }

    protected void createPackage(final String packageName) {
        assert packageName != null && packageName.length() > 0;

        final PsiDirectory baseDirectory = myFixture.getPsiManager().findDirectory(getSourceRoot());

        assert baseDirectory != null;

        ActionUtils.runAction(() -> PsiUtils.createMissingDirectoriesForPackage(baseDirectory, packageName));
    }

    protected PsiFile getGeneratedFile(BuilderGeneratorProperties generatorProperties) {
        final String path = getGeneratedFilePath(generatorProperties);

        final VirtualFile generatedVirtualFile = myFixture.findFileInTempDir(path);
        if (generatedVirtualFile == null) {
            throw new IllegalArgumentException("could not find generated file " + path);
        }

        final PsiFile generatedPsiFile = myFixture.getPsiManager().findFile(generatedVirtualFile);
        assert generatedPsiFile != null;

        return generatedPsiFile;
    }

    protected PsiClass loadTestClassFromFile(String testName, String fileName) {
        String inputFilePath = testName + "/" + fileName;

        final PsiFile sourceFile = myFixture.configureByFile(inputFilePath);

        return PsiUtils.getPublicClassFromFile(sourceFile)
                .orElseThrow(() -> new IllegalStateException("Couldn't load test class from file "
                        + inputFilePath + " for test " + testName));
    }

    // TODO is this required?
    protected Optional<PsiClass> loadDefaultBaseClass() {
        return loadTestClassFromText("com/mistraltech/bog/core/AbstractBuilder.java",
                "package com.mistraltech.bog.core;\n" +
                        "public class AbstractBuilder<T> {}\n");
    }

    protected Optional<PsiClass> loadTestClassFromText(String className, String code) {
        final PsiFile sourceFile = myFixture.addFileToProject(className, code);
        return PsiUtils.getPublicClassFromFile(sourceFile);
    }
}
