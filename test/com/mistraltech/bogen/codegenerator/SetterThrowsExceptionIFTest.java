package com.mistraltech.bogen.codegenerator;

public class SetterThrowsExceptionIFTest extends AbstractInterfaceGeneratorTest {
    public void testSetterThrowsException() {
        doTest("setter_throws_if", defaultGeneratorProperties());
    }
}
