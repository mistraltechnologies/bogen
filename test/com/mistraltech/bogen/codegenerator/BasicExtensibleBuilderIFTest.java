package com.mistraltech.bogen.codegenerator;

public class BasicExtensibleBuilderIFTest extends AbstractInterfaceGeneratorTest {
    public void testBasicExtensibleBuilder() {
        doTest("basic_extensible_if", defaultGeneratorProperties().setExtensible(true));
    }
}
