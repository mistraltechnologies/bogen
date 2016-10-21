package com.mistraltech.bogen.codegenerator.javabuilder;

import java.util.ArrayList;
import java.util.List;

import static com.mistraltech.bogen.codegenerator.javabuilder.BuilderUtils.buildList;

public class MethodBuilder extends MethodSignatureBuilder<MethodBuilder> implements StatementContainer<MethodBuilder> {
    protected String accessModifier;

    private boolean staticFlag;

    private boolean finalFlag;

    private boolean abstractFlag;

    private boolean defaultFlag;

    private List<StatementBuilder> statements = new ArrayList<>();

    private MethodBuilder() {
    }

    public static MethodBuilder aMethod() {
        return new MethodBuilder();
    }

    public MethodBuilder withStaticFlag(boolean staticFlag) {
        this.staticFlag = staticFlag;
        return self();
    }

    public MethodBuilder withAbstractFlag(boolean abstractFlag) {
        this.abstractFlag = abstractFlag;
        return self();
    }

    public MethodBuilder withAccessModifier(String modifier) {
        this.accessModifier = modifier;
        return self();
    }

    public MethodBuilder withFinalFlag(boolean finalFlag) {
        this.finalFlag = finalFlag;
        return self();
    }

    public MethodBuilder withDefaultFlag(boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
        return self();
    }

    public MethodBuilder withStatement(StatementBuilder statement) {
        statements.add(statement);
        return self();
    }

    @Override
    public MethodBuilder withStatement(ExpressionTermBuilder expression) {
        return withStatement(ExpressionStatementBuilder.anExpressionStatement().withExpression(expression));
    }

    @Override
    protected boolean isStatic() {
        return staticFlag;
    }

    @Override
    protected boolean isFinal() {
        return finalFlag;
    }

    @Override
    protected boolean isAbstract() {
        return abstractFlag;
    }

    @Override
    protected boolean isDefault() {
        return defaultFlag;
    }

    @Override
    protected String getAccessModifier() {
        return accessModifier;
    }

    @Override
    public String build(JavaBuilderContext context) {
        String sb = super.build(context) +
                "{" +
                buildList(context, "", statements, "", "") +
                "}\n";

        return sb;
    }
}
