package com.mistraltech.bogen.codegenerator.javabuilder;

import java.util.ArrayList;
import java.util.List;

import static com.mistraltech.bogen.codegenerator.javabuilder.BuilderUtils.buildMandatoryList;
import static com.mistraltech.bogen.codegenerator.javabuilder.ExpressionBuilder.anExpression;

public class NewInstanceBuilder extends ExpressionTermBuilder<NewInstanceBuilder> {
    private TypeBuilder type;

    private List<ExpressionTermBuilder> parameters = new ArrayList<>();

    private NewInstanceBuilder() {
    }

    public static NewInstanceBuilder aNewInstance() {
        return new NewInstanceBuilder();
    }

    public NewInstanceBuilder withType(TypeBuilder type) {
        this.type = type;
        return this;
    }

    public NewInstanceBuilder withParameter(String parameter) {
        return withParameter(anExpression().withText(parameter));
    }

    public NewInstanceBuilder withParameter(ExpressionTermBuilder expression) {
        parameters.add(expression);
        return this;
    }

    @Override
    public String build(JavaBuilderContext context) {
        return "new " + type.build(context) + buildMandatoryList(context, "(", parameters, ")", ", ");
    }
}
