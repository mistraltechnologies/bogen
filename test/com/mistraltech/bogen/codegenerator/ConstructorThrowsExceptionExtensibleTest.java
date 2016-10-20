package com.mistraltech.bogen.codegenerator;

public class ConstructorThrowsExceptionExtensibleTest extends AbstractGeneratorTest {
    public void testConstructorThrowsException() {
        doTest("constructor_throws_extensible", defaultGeneratorProperties().setExtensible(true));
    }
}
