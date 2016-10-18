package com.mistraltech.bogen.codegenerator;

public class GenericSubclassTest extends AbstractGeneratorTest {

    public static final String TEST_NAME = "generic_subclass";

    public void testGenericSubclass() {
        doTest(TEST_NAME, defaultGeneratorProperties().setBuilderSuperClassName("BaseWidgetBuilder"));
    }

    @Override
    protected void preLoadClasses() {
        super.preLoadClasses();
        loadTestClassFromFile(TEST_NAME, "superclass.java");
    }
}
