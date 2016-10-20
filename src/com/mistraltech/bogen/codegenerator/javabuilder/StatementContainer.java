package com.mistraltech.bogen.codegenerator.javabuilder;

public interface StatementContainer<T extends StatementContainer> {
    T withStatement(StatementBuilder statement);

    T withStatement(ExpressionTermBuilder expression);
}
