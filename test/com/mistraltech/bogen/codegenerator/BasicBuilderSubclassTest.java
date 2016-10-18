package com.mistraltech.bogen.codegenerator;

public class BasicBuilderSubclassTest extends AbstractGeneratorTest {
    public static final String TEST_NAME = "basic_subclass";

    public void testBasicBuilderSubclass() {
        doTest(TEST_NAME, defaultGeneratorProperties().setBuilderSuperClassName("BaseWidgetBuilder"));
    }

    @Override
    protected void preLoadClasses() {
        super.preLoadClasses();
        loadTestClassFromFile(TEST_NAME, "superclass.java");
    }
}
