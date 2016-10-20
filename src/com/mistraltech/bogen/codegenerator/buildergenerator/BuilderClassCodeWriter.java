package com.mistraltech.bogen.codegenerator.buildergenerator;

import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiMethod;
import com.mistraltech.bogen.codegenerator.javabuilder.AbstractClassBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.BlockStatementBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.ClassBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.ExpressionBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.ExpressionTermBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.JavaDocumentBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.MethodBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.MethodCallBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.NestedClassBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.NewInstanceBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.ReturnStatementBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.StatementBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.StatementContainer;
import com.mistraltech.bogen.codegenerator.javabuilder.TryCatchBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.TypeBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.TypeParameterDeclBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.VariableBuilder;
import com.mistraltech.bogen.property.Property;
import com.mistraltech.bogen.property.PropertyLocator;
import com.mistraltech.bogen.utils.PsiUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.java.generate.psi.PsiAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

import static com.mistraltech.bogen.codegenerator.javabuilder.AnnotationBuilder.anAnnotation;
import static com.mistraltech.bogen.codegenerator.javabuilder.BlockStatementBuilder.aBlockStatement;
import static com.mistraltech.bogen.codegenerator.javabuilder.CastBuilder.aCast;
import static com.mistraltech.bogen.codegenerator.javabuilder.ClassBuilder.aJavaClass;
import static com.mistraltech.bogen.codegenerator.javabuilder.ExpressionBuilder.anExpression;
import static com.mistraltech.bogen.codegenerator.javabuilder.ExpressionStatementBuilder.anExpressionStatement;
import static com.mistraltech.bogen.codegenerator.javabuilder.ExpressionTextBuilder.expressionText;
import static com.mistraltech.bogen.codegenerator.javabuilder.FieldTermBuilder.aField;
import static com.mistraltech.bogen.codegenerator.javabuilder.MethodBuilder.aMethod;
import static com.mistraltech.bogen.codegenerator.javabuilder.MethodCallBuilder.aMethodCall;
import static com.mistraltech.bogen.codegenerator.javabuilder.NestedClassBuilder.aNestedClass;
import static com.mistraltech.bogen.codegenerator.javabuilder.NewInstanceBuilder.aNewInstance;
import static com.mistraltech.bogen.codegenerator.javabuilder.ParameterBuilder.aParameter;
import static com.mistraltech.bogen.codegenerator.javabuilder.ReturnStatementBuilder.aReturnStatement;
import static com.mistraltech.bogen.codegenerator.javabuilder.TryCatchBuilder.aTryCatch;
import static com.mistraltech.bogen.codegenerator.javabuilder.TryCatchHandlerBuilder.aTryCatchHandler;
import static com.mistraltech.bogen.codegenerator.javabuilder.TypeBuilder.VOID;
import static com.mistraltech.bogen.codegenerator.javabuilder.TypeBuilder.aType;
import static com.mistraltech.bogen.codegenerator.javabuilder.TypeParameterBuilder.aTypeParameter;
import static com.mistraltech.bogen.codegenerator.javabuilder.TypeParameterDeclBuilder.aTypeParameterDecl;
import static com.mistraltech.bogen.codegenerator.javabuilder.VariableBuilder.aVariable;
import static com.mistraltech.bogen.utils.NameUtils.createFQN;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class BuilderClassCodeWriter extends AbstractBuilderCodeWriter {

    public BuilderClassCodeWriter(BuilderGeneratorProperties builderGeneratorProperties) {
        super(builderGeneratorProperties);
    }

    @Override
    protected void generateDocumentContent(JavaDocumentBuilder document) {
        document.addClass(generateBuilderClass());
    }

    private ClassBuilder generateBuilderClass() {
        final String generatedClassFQN = createFQN(getPackage().getQualifiedName(), generatorProperties.getClassName());

        ClassBuilder clazz = aJavaClass()
                .withAccessModifier("public")
                .withName(generatorProperties.getClassName())
                .withTypeParameters(typeParameterDecls())
                .withAnnotation(anAnnotation()
                        .withType(aType()
                                .withName("com.mistraltech.bog.core.annotation.Builds"))
                        .withParameter(aField()
                                .withType(getSourceClassFQName())
                                .withField("class")));

        final TypeBuilder builtType = aType()
                .withName(getSourceClassFQName())
                .withTypeBindings(typeParameters());

        final TypeBuilder returnType;
        final TypeBuilder builtTypeParam;
        final TypeBuilder builderType;

        if (generatorProperties.isExtensible()) {
            TypeParameterDeclBuilder returnTypeDecl = aTypeParameterDecl()
                    .withName("R")
                    .withExtends(aType()
                            .withName(generatedClassFQN)
                            .withTypeBindings(typeParameters())
                            .withTypeBinding(aTypeParameter()
                                    .withName("R"))
                            .withTypeBinding(aTypeParameter()
                                    .withName("T")));

            returnType = returnTypeDecl.getType();

            TypeParameterDeclBuilder builtTypeDecl = aTypeParameterDecl()
                    .withName("T")
                    .withExtends(builtType);

            builtTypeParam = builtTypeDecl.getType();

            builderType = aType()
                    .withName(nestedClassName())
                    .withTypeBindings(typeParameters());

            clazz.withAbstractFlag(true)
                    .withTypeParameter(returnTypeDecl)
                    .withTypeParameter(builtTypeDecl);
        } else {
            clazz.withFinalFlag(true);
            returnType = aType()
                    .withName(generatedClassFQN)
                    .withTypeBindings(typeParameters());
            builtTypeParam = builtType;
            builderType = returnType;
        }

        applySuperClass(clazz, returnType, builtTypeParam);

        applyClassBody(clazz, returnType, builtType, builtTypeParam, builderType);

        return clazz;
    }

    private void applySuperClass(AbstractClassBuilder clazz, TypeBuilder returnType, TypeBuilder builtTypeParam) {
        TypeBuilder superType;

        if (generatorProperties.getBuilderSuperClassName() != null) {
            superType = aType()
                    .withName(generatorProperties.getBuilderSuperClassName())
                    .withTypeBindings(getSourceSuperClassParameterBuilders())
                    .withTypeBinding(returnType)
                    .withTypeBinding(builtTypeParam);
        } else {
            superType = aType()
                    .withName(generatorProperties.getBaseClassName())
                    .withTypeBinding(builtTypeParam);
        }

        clazz.withSuperclass(superType);
    }

    private void applyClassBody(AbstractClassBuilder clazz, TypeBuilder returnType, TypeBuilder builtType,
            TypeBuilder builtTypeParam, TypeBuilder builderType) {
        boolean includeSuperClassProperties = generatorProperties.getBuilderSuperClassName() == null;

        List<Property> mutableProperties = PropertyLocator.locatePropertiesFromSetters(
                getSourceClass(), includeSuperClassProperties);

        List<Property> constructorProperties = PropertyLocator.locatePropertiesFromConstructor(
                getSourceClass(), generatorProperties.getConstructor());

        Set<Property> sourceClassProperties = new TreeSet<>(comparing(Property::getFieldName));
        sourceClassProperties.addAll(mutableProperties);
        sourceClassProperties.addAll(constructorProperties);

        clazz.withVariables(generateBuilderVariables(sourceClassProperties))
                .withMethod(generateConstructor(sourceClassProperties, builtTypeParam));

        if (generatorProperties.isGenerateFactoryMethods()) {
            clazz.withMethod(generateStaticFactoryMethod(builderType));

            if (generatorProperties.isGenerateTemplateFactoryMethod()) {
                clazz.withMethod(generateFromStaticFactoryMethod(builderType, builtType));
            }
        }

        if (generatorProperties.isExtensible()) {
            clazz.withMethod(generateSelfMethod(returnType));
        }

        sourceClassProperties.forEach(p -> clazz.withMethods(generateBuilderSetters(p, returnType)));

        sourceClassProperties.forEach(p -> clazz.withMethod(generateBuilderGetters(p)));

        boolean constructorThrows = generatorProperties.getConstructor() != null &&
                throwsCheckedException(generatorProperties.getConstructor());

        if (generatorProperties.isExtensible()) {
            clazz.withNestedClass(generateNestedClass(builderType, builtType, constructorProperties, constructorThrows));
        } else {
            clazz.withMethod(generateConstructMethod(builtType, constructorProperties, constructorThrows));
        }

        clazz.withMethod(generateAssignMethod(builtTypeParam, sourceClassProperties));

        clazz.withMethod(generatePostUpdateMethod(builtType, sourceClassProperties));

    }

    private boolean throwsCheckedException(PsiMethod method) {
        PsiClassType[] thrownTypes = method.getThrowsList().getReferencedTypes();

        Predicate<Class<?>> isCheckedException =
                c -> Exception.class.isAssignableFrom(c) && !RuntimeException.class.isAssignableFrom(c);

        return Arrays.stream(thrownTypes)
                .map(t -> PsiUtils.classFrom(t).orElse(Exception.class))
                .anyMatch(isCheckedException);
    }

    private List<VariableBuilder> generateBuilderVariables(@NotNull Set<Property> properties) {
        return properties.stream()
                .map(this::generateBuilderVariable)
                .collect(toList());
    }

    private VariableBuilder generateBuilderVariable(@NotNull Property property) {

        NewInstanceBuilder valueContainerInstance = aNewInstance()
                .withType(aType()
                        .withName(CONCRETE_BUILDER_PROPERTY_TYPE_NAME)
                        .withDiamondOperator());

        if (PsiAdapter.isPrimitiveType(property.getPsiType())) {
            valueContainerInstance.withParameter(getPropertyTypeBuilder(property, false).getTypeFQN() + ".class");
        }

        return aVariable()
                .withAccessModifier("private")
                .withFinalFlag(true)
                .withType(aType()
                        .withName(CONCRETE_BUILDER_PROPERTY_TYPE_NAME)
                        .withTypeBinding(getPropertyTypeBuilder(property, true)))
                .withName(builderAttributeName(property))
                .withInitialiser(valueContainerInstance);
    }

    private MethodBuilder generateConstructor(@NotNull Set<Property> sourceClassProperties, TypeBuilder builtType) {
        MethodCallBuilder superMethodCall = aMethodCall()
                .withName("super");

        if (generatorProperties.getBuilderSuperClassName() != null && generatorProperties.isGenerateTemplateFactoryMethod()) {
            superMethodCall.withParameter("template");
        }

        final MethodBuilder constructor = aMethod()
                .withAccessModifier(generatorProperties.isExtensible() ? "protected" : "private")
                .withName(generatorProperties.getClassName())
                .withStatement(anExpressionStatement()
                        .withExpression(superMethodCall));

        if (generatorProperties.isGenerateTemplateFactoryMethod()) {
            constructor.withParameter(aParameter()
                    .withFinalFlag(generatorProperties.isMakeMethodParametersFinal())
                    .withType(builtType)
                    .withName("template"));

            if (sourceClassProperties.size() > 0) {
                BlockStatementBuilder setFromTemplate = aBlockStatement()
                        .withHeader("if (template != null)");

                //noinspection OptionalGetWithoutIsPresent
                sourceClassProperties.stream()
                        .filter(p -> p.getAccessorName().isPresent())
                        .forEach(p ->
                                setFromTemplate.withStatement(aMethodCall()
                                        .withObject(builderAttributeName(p))
                                        .withName("set")
                                        .withParameter(aMethodCall()
                                                .withObject("template")
                                                .withName(p.getAccessorName().get()))));

                constructor.withStatement(setFromTemplate);
            }
        }

        return constructor;
    }

    private NestedClassBuilder generateNestedClass(TypeBuilder builderType, TypeBuilder builtType,
            List<Property> constructorProperties, boolean constructorThrows) {

        final MethodCallBuilder superCall = aMethodCall()
                .withName("super");

        if (generatorProperties.isGenerateTemplateFactoryMethod()) {
            superCall.withParameter("template");
        }

        final MethodBuilder constructor = aMethod()
                .withAccessModifier("private")
                .withName(nestedClassName())
                .withStatement(anExpressionStatement().withExpression(superCall));

        if (generatorProperties.isGenerateTemplateFactoryMethod()) {
            constructor.withParameter(aParameter()
                    .withFinalFlag(generatorProperties.isMakeMethodParametersFinal())
                    .withType(builtType)
                    .withName("template"));
        }

        final MethodBuilder constructMethod = generateConstructMethod(builtType, constructorProperties, constructorThrows);

        NestedClassBuilder nestedClass = aNestedClass()
                .withAccessModifier("public")
                .withStaticFlag(true)
                .withName(nestedClassName())
                .withTypeParameters(typeParameterDecls())
                .withSuperclass(aType()
                        .withName(generatorProperties.getClassName())
                        .withTypeBindings(typeParameters())
                        .withTypeBinding(builderType)
                        .withTypeBinding(builtType))
                .withMethod(constructor)
                .withMethod(constructMethod);

        return nestedClass;
    }

    private MethodBuilder generateStaticFactoryMethod(TypeBuilder builderType) {
        final NewInstanceBuilder newInstance = aNewInstance()
                .withType(builderType);

        if (generatorProperties.isGenerateTemplateFactoryMethod()) {
            newInstance.withParameter("null");
        }

        return aMethod()
                .withAccessModifier("public")
                .withStaticFlag(true)
                .withReturnType(builderType)
                .withTypeParameters(typeParameterDecls())
                .withName(generatorProperties.getFactoryMethodPrefix() + getSourceClassName() + generatorProperties.getFactoryMethodSuffix())
                .withStatement(aReturnStatement()
                        .withExpression(newInstance));
    }

    private MethodBuilder generateFromStaticFactoryMethod(TypeBuilder builderType, TypeBuilder builtType) {
        return aMethod()
                .withAccessModifier("public")
                .withStaticFlag(true)
                .withReturnType(builderType)
                .withTypeParameters(typeParameterDecls())
                .withName(generatorProperties.getFactoryMethodPrefix() + getSourceClassName() + generatorProperties.getTemplateFactoryMethodSuffix())
                .withParameter(aParameter()
                        .withFinalFlag(generatorProperties.isMakeMethodParametersFinal())
                        .withType(builtType)
                        .withName("template"))
                .withStatement(aReturnStatement()
                        .withExpression(aNewInstance()
                                .withType(builderType)
                                .withParameter("template")));
    }

    private MethodBuilder generateSelfMethod(TypeBuilder typeParameterR) {
        return aMethod()
                .withAnnotation(anAnnotation()
                        .withType(aType()
                                .withName("java.lang.SuppressWarnings"))
                        .withParameter(expressionText("\"unchecked\"")))
                .withAccessModifier("private")
                .withReturnType(typeParameterR)
                .withName("self")
                .withStatement(aReturnStatement()
                        .withExpression(anExpression()
                                .withTerm(aCast().withType(typeParameterR))
                                .withText("this")));
    }

    private List<MethodBuilder> generateBuilderSetters(@NotNull Property property, TypeBuilder returnType) {
        List<MethodBuilder> methods = new ArrayList<>();

        final TypeBuilder boxedPropertyType = getPropertyTypeBuilder(property, true);

        ExpressionBuilder returnExpression = generatorProperties.isExtensible()
                ? anExpression().withTerm(aMethodCall().withName("self"))
                : anExpression().withText("this");

        methods.add(aMethod()
                .withAccessModifier("public")
                .withReturnType(returnType)
                .withName(setterMethodName(property))
                .withParameter(aParameter()
                        .withFinalFlag(generatorProperties.isMakeMethodParametersFinal())
                        .withType(getPropertyTypeBuilder(property, false))
                        .withName(property.getFieldName()))
                .withStatement(anExpressionStatement()
                        .withExpression(aMethodCall()
                                .withObject("this." + builderAttributeName(property))
                                .withName("set")
                                .withParameter(anExpression().withText(property.getFieldName()))))
                .withStatement(aReturnStatement()
                        .withExpression(returnExpression)));


        methods.add(aMethod()
                .withAccessModifier("public")
                .withReturnType(returnType)
                .withName(setterMethodName(property))
                .withParameter(aParameter()
                        .withFinalFlag(generatorProperties.isMakeMethodParametersFinal())
                        .withType(aType()
                                .withName(BUILDER_TYPE_NAME)
                                .withTypeBinding(aTypeParameter()
                                        .withType(boxedPropertyType)
                                        .withSubTypes(true)))
                        .withName(builderAttributeName(property)))
                .withStatement(anExpressionStatement()
                        .withExpression(aMethodCall()
                                .withObject("this." + builderAttributeName(property))
                                .withName("set")
                                .withParameter(anExpression().withText(builderAttributeName(property)))))
                .withStatement(aReturnStatement()
                        .withExpression(returnExpression)));
        return methods;
    }

    private MethodBuilder generateBuilderGetters(@NotNull Property property) {
        TypeBuilder returnType = aType()
                .withName(EXPOSED_BUILDER_PROPERTY_TYPE_NAME)
                .withTypeBinding(getPropertyTypeBuilder(property, true));

        return aMethod()
                .withAccessModifier("public")
                .withReturnType(returnType)
                .withName(getterMethodName(property))
                .withStatement(aReturnStatement()
                        .withExpression(expressionText(builderAttributeName(property))));
    }

    private MethodBuilder generateAssignMethod(TypeBuilder builtType, Set<Property> properties) {
        boolean throwsException = properties.stream()
                .map(Property::getMutatorMethod)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .anyMatch(this::throwsCheckedException);

        final MethodBuilder assignMethod = aMethod()
                .withAnnotation(anAnnotation()
                        .withType(aType()
                                .withName("java.lang.Override")))
                .withAccessModifier("protected")
                .withReturnType(VOID)
                .withName("assign")
                .withParameter(aParameter()
                        .withFinalFlag(generatorProperties.isMakeMethodParametersFinal())
                        .withType(builtType)
                        .withName("instance"))
                .withStatement(anExpressionStatement()
                        .withExpression(aMethodCall()
                                .withObject("super")
                                .withName("assign")
                                .withParameter("instance")));

        StatementContainer statementContainer;

        if (throwsException) {
            TryCatchBuilder tryCatchBuilder = aTryCatch()
                    .withHandler(aTryCatchHandler()
                            .withException(Exception.class)
                            .withStatement(anExpressionStatement()
                                    .withExpression(expressionText("throw new RuntimeException(e)"))));

            assignMethod.withStatement(tryCatchBuilder);

            statementContainer = tryCatchBuilder;
        } else {
            statementContainer = assignMethod;
        }

        //noinspection OptionalGetWithoutIsPresent
        properties.stream()
                .filter(p -> p.getMutatorName().isPresent())
                .forEach(p ->
                        statementContainer
                                .withStatement(anExpressionStatement()
                                        .withExpression(aMethodCall()
                                                .withObject("instance")
                                                .withName(p.getMutatorName().get())
                                                .withParameter(anExpression()
                                                        .withTerm(getValueMethodCall(p, false))))));

        return assignMethod;
    }

    private MethodBuilder generateConstructMethod(TypeBuilder builtType, List<Property> properties, boolean throwsException) {
        TypeBuilder returnType = aType()
                .withName(generatorProperties.getSourceClass().getName());

        TypeBuilder instanceType = aType()
                .withName(generatorProperties.getSourceClass().getName());

        if (!builtType.getTypeBindings().isEmpty()) {
            builtType.getTypeBindings().forEach(returnType::withTypeBinding);
            instanceType.withDiamondOperator();
        }

        final MethodBuilder constructMethod = aMethod()
                .withAnnotation(anAnnotation()
                        .withType(aType()
                                .withName("java.lang.Override")))
                .withAccessModifier("protected")
                .withReturnType(returnType)
                .withName("construct");

        NewInstanceBuilder constructorCall = aNewInstance()
                .withType(instanceType);

        //noinspection OptionalGetWithoutIsPresent
        properties.forEach(p ->
                constructorCall
                        .withParameter(getValueMethodCall(p, generatorProperties.isExtensible())));

        ReturnStatementBuilder returnStatement = aReturnStatement()
                .withExpression(constructorCall);

        StatementBuilder methodStatement = throwsException ?
                aTryCatch()
                        .withStatement(returnStatement)
                        .withHandler(aTryCatchHandler()
                                .withException(Exception.class)
                                .withStatement(anExpressionStatement()
                                        .withExpression(expressionText("throw new RuntimeException(e)")))) :
                returnStatement;

        constructMethod.withStatement(methodStatement);

        return constructMethod;
    }

    private MethodCallBuilder getValueMethodCall(Property property, boolean useGetter) {
        ExpressionTermBuilder target = useGetter ?
                aMethodCall().withName(getterMethodName(property)) :
                expressionText(builderAttributeName(property));

        return aMethodCall()
                .withObject(target)
                .withName("value");
    }

    private MethodBuilder generatePostUpdateMethod(TypeBuilder builtType, Set<Property> properties) {
        final MethodBuilder postUpdateMethod = aMethod()
                .withAnnotation(anAnnotation()
                        .withType(aType()
                                .withName("java.lang.Override")))
                .withAccessModifier("protected")
                .withReturnType(VOID)
                .withName("postUpdate")
                .withStatement(anExpressionStatement()
                        .withExpression(aMethodCall()
                                .withObject("super")
                                .withName("postUpdate")));

        //noinspection OptionalGetWithoutIsPresent
        properties.forEach(p ->
                postUpdateMethod
                        .withStatement(anExpressionStatement()
                                .withExpression(aMethodCall()
                                        .withObject(builderAttributeName(p))
                                        .withName("reset"))));

        return postUpdateMethod;
    }

    private String nestedClassName() {
        return generatorProperties.getClassName() + "Type";
    }

}
