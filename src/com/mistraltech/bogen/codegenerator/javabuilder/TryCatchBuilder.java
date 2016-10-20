package com.mistraltech.bogen.codegenerator.javabuilder;

import java.util.ArrayList;
import java.util.List;

import static com.mistraltech.bogen.codegenerator.javabuilder.BuilderUtils.buildList;

public class TryCatchBuilder extends StatementBuilder<TryCatchBuilder> implements StatementContainer<TryCatchBuilder> {

    private List<StatementBuilder> statements = new ArrayList<>();

    private List<TryCatchHandlerBuilder> handlers = new ArrayList<>();

    private TryCatchBuilder() {
    }

    public static TryCatchBuilder aTryCatch() {
        return new TryCatchBuilder();
    }

    public TryCatchBuilder withStatement(StatementBuilder statement) {
        statements.add(statement);
        return this;
    }

    public TryCatchBuilder withStatement(ExpressionTermBuilder expression) {
        return withStatement(ExpressionStatementBuilder.anExpressionStatement().withExpression(expression));
    }

    public TryCatchBuilder withHandler(TryCatchHandlerBuilder tryCatchHandlerBuilder) {
        handlers.add(tryCatchHandlerBuilder);
        return this;
    }

    @Override
    public String build(JavaBuilderContext context) {
        return "try {\n"
                + buildList(context, "", statements, "", "")
                + "}"
                + buildList(context, "", handlers, "", "");
    }
}
