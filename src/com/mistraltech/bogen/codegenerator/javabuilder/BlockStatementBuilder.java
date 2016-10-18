package com.mistraltech.bogen.codegenerator.javabuilder;

import java.util.ArrayList;
import java.util.List;

import static com.mistraltech.bogen.codegenerator.javabuilder.BuilderUtils.buildList;

public class BlockStatementBuilder extends StatementBuilder<BlockStatementBuilder> {

    private String headerText;

    private List<StatementBuilder> statements = new ArrayList<>();

    private BlockStatementBuilder() {
    }

    public static BlockStatementBuilder aBlockStatement() {
        return new BlockStatementBuilder();
    }

    public BlockStatementBuilder withHeader(String text) {
        this.headerText = text;
        return this;
    }

    public BlockStatementBuilder withStatement(StatementBuilder statement) {
        statements.add(statement);
        return this;
    }

    public BlockStatementBuilder withStatement(ExpressionTermBuilder expression) {
        return withStatement(ExpressionStatementBuilder.anExpressionStatement().withExpression(expression));
    }

    @Override
    public String build(JavaBuilderContext context) {
        return headerText + "{\n" + buildList(context, "", statements, "", "") + "}\n";
    }
}
