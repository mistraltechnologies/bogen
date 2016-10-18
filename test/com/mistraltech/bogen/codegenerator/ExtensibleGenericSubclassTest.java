package com.mistraltech.bogen.codegenerator;

public class ExtensibleGenericSubclassTest extends AbstractGeneratorTest {
    public static final String TEST_NAME = "extensible_generic_subclass";

    public void testExtensibleGenericSubclass() {
        doTest(TEST_NAME, defaultGeneratorProperties().setBuilderSuperClassName("BaseWidgetBuilder").setExtensible(true));
    }

    @Override
    protected void preLoadClasses() {
        super.preLoadClasses();
        loadTestClassFromFile(TEST_NAME, "superclass.java");
    }
}
