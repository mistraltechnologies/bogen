package com.mistraltech.bogen.codegenerator;

public class BasicExtensibleBuilderTest extends AbstractGeneratorTest {
    public void testBasicExtensibleBuilder() {
        doTest("basic_extensible", defaultGeneratorProperties().setExtensible(true));
    }
}
