package com.mistraltech.bogen.codegenerator;

public class ConstructorThrowsExceptionIFTest extends AbstractInterfaceGeneratorTest {
    public void testConstructorThrowsException() {
        doTest("constructor_throws_if", defaultGeneratorProperties());
    }
}
