package com.mistraltech.bogen.codegenerator;

public class ExtensibleGenericSubclassIFTest extends AbstractInterfaceGeneratorTest {
    public static final String TEST_NAME = "extensible_generic_subclass_if";

    public void testExtensibleGenericSubclass() {
        doTest(TEST_NAME, defaultGeneratorProperties().setBuilderSuperClassName("BaseWidgetBuilder").setExtensible(true));
    }

    @Override
    protected void preLoadClasses() {
        super.preLoadClasses();
        loadTestClassFromFile(TEST_NAME, "superclass.java");
    }
}
