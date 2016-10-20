package com.mistraltech.bogen.codegenerator;

public class ConstructorThrowsExceptionTest extends AbstractGeneratorTest {
    public void testConstructorThrowsException() {
        doTest("constructor_throws", defaultGeneratorProperties());
    }
}
