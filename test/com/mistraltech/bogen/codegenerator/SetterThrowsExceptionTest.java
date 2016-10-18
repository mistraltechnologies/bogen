package com.mistraltech.bogen.codegenerator;

public class SetterThrowsExceptionTest extends AbstractGeneratorTest {
    public void testSetterThrowsException() {
        doTest("setter_throws", defaultGeneratorProperties());
    }
}
