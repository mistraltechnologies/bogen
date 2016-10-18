package com.mistraltech.bogen.codegenerator;

public class ExtensibleSubclassTest extends AbstractGeneratorTest {
    public static final String TEST_NAME = "extensible_subclass";

    public void testExtensibleSubclass() {
        doTest(TEST_NAME, defaultGeneratorProperties().setBuilderSuperClassName("BaseWidgetBuilder").setExtensible(true));
    }

    @Override
    protected void preLoadClasses() {
        super.preLoadClasses();
        loadTestClassFromFile(TEST_NAME, "superclass.java");
    }
}
