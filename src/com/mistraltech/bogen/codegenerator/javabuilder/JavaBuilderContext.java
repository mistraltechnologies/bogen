package com.mistraltech.bogen.codegenerator.javabuilder;

public interface JavaBuilderContext {
    String normaliseClassReference(String classFQN);

    String normaliseClassMemberReference(String memberFQN, String typeParams);
}
