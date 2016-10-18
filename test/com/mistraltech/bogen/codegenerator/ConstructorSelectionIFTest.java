package com.mistraltech.bogen.codegenerator;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;

public class ConstructorSelectionIFTest extends AbstractInterfaceGeneratorTest {
    private static final String TEST_NAME = "constructor_selection_if";

    @Override
    protected PsiMethod getConstructor(PsiClass sourceClass) {
        return findConstructor(sourceClass, "String", "String");
    }

    public void testSelectConstructor() {
        doTest(TEST_NAME, "input.java", "generated.java", defaultGeneratorProperties());
    }
}
