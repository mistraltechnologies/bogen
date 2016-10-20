package com.mistraltech.bogen.codegenerator.javabuilder;

import java.util.ArrayList;
import java.util.List;

import static com.mistraltech.bogen.codegenerator.javabuilder.BuilderUtils.buildList;
import static com.mistraltech.bogen.codegenerator.javabuilder.TypeBuilder.aType;
import static java.util.stream.Collectors.toList;

public class TryCatchHandlerBuilder extends AbstractBuilder<TryCatchHandlerBuilder>
        implements StatementContainer<TryCatchHandlerBuilder> {

    private List<StatementBuilder> statements = new ArrayList<>();

    private List<Class<? extends Exception>> exceptions = new ArrayList<>();

    private TryCatchHandlerBuilder() {
    }

    public static TryCatchHandlerBuilder aTryCatchHandler() {
        return new TryCatchHandlerBuilder();
    }

    public TryCatchHandlerBuilder withStatement(StatementBuilder statement) {
        statements.add(statement);
        return this;
    }

    public TryCatchHandlerBuilder withStatement(ExpressionTermBuilder expression) {
        return withStatement(ExpressionStatementBuilder.anExpressionStatement().withExpression(expression));
    }

    public TryCatchHandlerBuilder withException(Class<? extends Exception> exceptionClass) {
        exceptions.add(exceptionClass);
        return this;
    }

    @Override
    public String build(JavaBuilderContext context) {
        List<TypeBuilder> exceptionTypes = exceptions.stream()
                .map(c -> aType().withName(c.getName()))
                .collect(toList());

        return "catch"
                + buildList(context, "(", exceptionTypes, " e)", "|")
                + "{\n"
                + buildList(context, "", statements, "", "")
                + "}\n";
    }
}
