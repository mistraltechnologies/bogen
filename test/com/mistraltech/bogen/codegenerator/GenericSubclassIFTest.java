package com.mistraltech.bogen.codegenerator;

public class GenericSubclassIFTest extends AbstractInterfaceGeneratorTest {

    public static final String TEST_NAME = "generic_subclass_if";

    public void testGenericSubclass() {
        doTest(TEST_NAME, defaultGeneratorProperties().setBuilderSuperClassName("BaseWidgetBuilder"));
    }

    @Override
    protected void preLoadClasses() {
        super.preLoadClasses();
        loadTestClassFromFile(TEST_NAME, "superclass.java");
    }
}
