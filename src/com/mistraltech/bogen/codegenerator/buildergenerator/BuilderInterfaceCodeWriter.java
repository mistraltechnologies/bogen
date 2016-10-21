package com.mistraltech.bogen.codegenerator.buildergenerator;

import com.mistraltech.bogen.codegenerator.javabuilder.AnnotationBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.ExpressionBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.InterfaceBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.InterfaceMethodBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.JavaDocumentBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.MethodBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.MethodSignatureBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.StaticMethodCallBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.StaticVariableReaderBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.TypeBuilder;
import com.mistraltech.bogen.codegenerator.javabuilder.TypeParameterDeclBuilder;
import com.mistraltech.bogen.codegenerator.utils.InitialValueTypeConverter;
import com.mistraltech.bogen.codegenerator.utils.PsiTypeConverter;
import com.mistraltech.bogen.property.Property;
import com.mistraltech.bogen.property.PropertyLocator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.mistraltech.bogen.codegenerator.buildergenerator.BuilderGenerator.BUILDER_SUPER_INTERFACE;
import static com.mistraltech.bogen.codegenerator.javabuilder.AnnotationBuilder.anAnnotation;
import static com.mistraltech.bogen.codegenerator.javabuilder.CastBuilder.aCast;
import static com.mistraltech.bogen.codegenerator.javabuilder.ExpressionBuilder.anExpression;
import static com.mistraltech.bogen.codegenerator.javabuilder.ExpressionTextBuilder.expressionText;
import static com.mistraltech.bogen.codegenerator.javabuilder.FieldTermBuilder.aField;
import static com.mistraltech.bogen.codegenerator.javabuilder.InterfaceBuilder.aJavaInterface;
import static com.mistraltech.bogen.codegenerator.javabuilder.InterfaceMethodBuilder.anInterfaceMethod;
import static com.mistraltech.bogen.codegenerator.javabuilder.MethodBuilder.aMethod;
import static com.mistraltech.bogen.codegenerator.javabuilder.MethodCallBuilder.aMethodCall;
import static com.mistraltech.bogen.codegenerator.javabuilder.ParameterBuilder.aParameter;
import static com.mistraltech.bogen.codegenerator.javabuilder.ReturnStatementBuilder.aReturnStatement;
import static com.mistraltech.bogen.codegenerator.javabuilder.StaticMethodCallBuilder.aStaticMethodCall;
import static com.mistraltech.bogen.codegenerator.javabuilder.StaticVariableReaderBuilder.aStaticVariable;
import static com.mistraltech.bogen.codegenerator.javabuilder.TypeBuilder.aType;
import static com.mistraltech.bogen.codegenerator.javabuilder.TypeParameterBuilder.aTypeParameter;
import static com.mistraltech.bogen.codegenerator.javabuilder.TypeParameterDeclBuilder.aTypeParameterDecl;
import static com.mistraltech.bogen.utils.NameUtils.createFQN;
import static java.util.Comparator.comparing;

public class BuilderInterfaceCodeWriter extends AbstractBuilderCodeWriter {

    private static final String JAVASSIST_BUILDER_GENERATOR = "com.mistraltech.bog.proxy.javassist.JavassistBuilderGenerator";

    public BuilderInterfaceCodeWriter(BuilderGeneratorProperties builderGeneratorProperties) {
        super(builderGeneratorProperties);
    }

    @Override
    protected void generateDocumentContent(JavaDocumentBuilder document) {
        document.addInterface(generateBuilderInterface());
    }

    private InterfaceBuilder generateBuilderInterface() {
        final String generatedClassFQN = createFQN(getPackage().getQualifiedName(), generatorProperties.getClassName());

        InterfaceBuilder clazz = aJavaInterface()
                .withAccessModifier("public")
                .withName(generatorProperties.getClassName())
                .withTypeParameters(typeParameterDecls())
                .withAnnotation(buildsAnnotation());

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

            clazz.withTypeParameter(returnTypeDecl)
                    .withTypeParameter(builtTypeDecl);
        } else {
            returnType = aType()
                    .withName(generatedClassFQN)
                    .withTypeBindings(typeParameters());
            builtTypeParam = builtType;
            builderType = returnType;
        }

        applySuperInterface(clazz, returnType, builtTypeParam);

        applyInterfaceBody(clazz, returnType, builtType, builtTypeParam, builderType);

        return clazz;
    }

    private void applyInterfaceBody(InterfaceBuilder clazz, TypeBuilder returnType, TypeBuilder builtType, TypeBuilder builtTypeParam, TypeBuilder builderType) {
        boolean includeSuperClassProperties = generatorProperties.getBuilderSuperClassName() == null;

        List<Property> mutableProperties = PropertyLocator.locatePropertiesFromSetters(
                getSourceClass(), includeSuperClassProperties);

        List<Property> constructorProperties = PropertyLocator.locatePropertiesFromConstructor(
                getSourceClass(), generatorProperties.getConstructor());

        Set<Property> sourceClassProperties = new TreeSet<>(comparing(Property::getFieldName));
        sourceClassProperties.addAll(constructorProperties);
        sourceClassProperties.addAll(mutableProperties);

        if (generatorProperties.isGenerateFactoryMethods()) {
            clazz.withMethod(generateStaticFactoryMethod(builderType));

            if (generatorProperties.isGenerateTemplateFactoryMethod()) {
                clazz.withMethod(generateFromStaticFactoryMethod(builderType, builtType));
                clazz.withMethod(generateFromMethod(returnType, builtType));
            }
        }

        sourceClassProperties.forEach(p -> clazz.withMethods(generateBuilderSetters(p, returnType, constructorProperties.indexOf(p))));

        sourceClassProperties.forEach(p -> clazz.withMethod(generateBuilderGetters(p)));

        sourceClassProperties.forEach(p -> clazz.withMethod(generateBuilderDefaultGetter(p)));

        if (generatorProperties.isExtensible()) {
            clazz.withNestedInterface(generateNestedInterface(builderType, builtType));
        }
    }

    private InterfaceBuilder generateNestedInterface(TypeBuilder builderType, TypeBuilder builtType) {
        InterfaceBuilder nestedInterface = aJavaInterface()
                .withAnnotation(buildsAnnotation())
                .withName(nestedClassName())
                .withTypeParameters(typeParameterDecls())
                .withImplementedInterface(aType()
                        .withName(generatorProperties.getClassName())
                        .withTypeBindings(typeParameters())
                        .withTypeBinding(builderType)
                        .withTypeBinding(builtType));

        return nestedInterface;
    }

    private void applySuperInterface(InterfaceBuilder clazz, TypeBuilder returnType, TypeBuilder builtTypeParam) {
        TypeBuilder superType;

        if (generatorProperties.getBuilderSuperClassName() != null) {
            superType = aType()
                    .withName(generatorProperties.getBuilderSuperClassName())
                    .withTypeBindings(getSourceSuperClassParameterBuilders())
                    .withTypeBinding(returnType)
                    .withTypeBinding(builtTypeParam);
        } else {
            superType = aType()
                    .withName(BUILDER_SUPER_INTERFACE)
                    .withTypeBinding(builtTypeParam);
        }

        clazz.withImplementedInterface(superType);
    }

    private MethodSignatureBuilder generateStaticFactoryMethod(TypeBuilder builderType) {
        final StaticMethodCallBuilder createCall = aStaticMethodCall()
                .withType(TypeBuilder.aType().withName(JAVASSIST_BUILDER_GENERATOR))
                .withName("builderOf")
                .withParameter(typeToClass(builderType));

        return aMethod()
                .withAnnotation(createSuppressAnnotation("\"unchecked\""))
                .withStaticFlag(true)
                .withReturnType(builderType)
                .withTypeParameters(typeParameterDecls())
                .withName(generatorProperties.getFactoryMethodPrefix() + getSourceClassName() + generatorProperties.getFactoryMethodSuffix())
                .withStatement(aReturnStatement()
                        .withExpression(createCall));
    }

    private MethodSignatureBuilder generateFromStaticFactoryMethod(TypeBuilder builderType, TypeBuilder builtType) {
        final StaticMethodCallBuilder createCall = aStaticMethodCall()
                .withType(TypeBuilder.aType().withName(JAVASSIST_BUILDER_GENERATOR))
                .withName("builderOf")
                .withParameter(typeToClass(builderType));

        ExpressionBuilder returnExpression = anExpression();

        if (generatorProperties.isExtensible() && !typeParameters().isEmpty()) {
            returnExpression.withTerm(aCast()
                    .withType(builderType));
        }

        returnExpression
                .withTerm(
                        aMethodCall()
                                .withObject(createCall)
                                .withName("from")
                                .withParameter("template"));

        return aMethod()
                .withAnnotation(createSuppressAnnotation("\"unchecked\""))
                .withStaticFlag(true)
                .withReturnType(builderType)
                .withTypeParameters(typeParameterDecls())
                .withName(generatorProperties.getFactoryMethodPrefix() + getSourceClassName() + generatorProperties.getTemplateFactoryMethodSuffix())
                .withParameter(aParameter()
                        .withFinalFlag(generatorProperties.isMakeMethodParametersFinal())
                        .withType(builtType)
                        .withName("template"))
                .withStatement(aReturnStatement()
                        .withExpression(returnExpression));
    }

    private AnnotationBuilder createSuppressAnnotation(String warning) {
        return anAnnotation()
                .withType(aType()
                        .withName("java.lang.SuppressWarnings"))
                .withParameter(expressionText(warning));
    }

    private MethodSignatureBuilder generateFromMethod(TypeBuilder returnType, TypeBuilder builtType) {
        return anInterfaceMethod()
                .withReturnType(returnType)
                .withName("from")
                .withParameter(aParameter()
                        .withType(builtType)
                        .withName("template"));
    }

    private List<? extends MethodSignatureBuilder> generateBuilderSetters(Property property, TypeBuilder returnType,
            int constructorPosition) {

        List<InterfaceMethodBuilder> methods = new ArrayList<>();

        final TypeBuilder boxedPropertyType = getPropertyTypeBuilder(property, true);

        InterfaceMethodBuilder valueAssignmentMethod = anInterfaceMethod()
                .withReturnType(returnType)
                .withName(setterMethodName(property))
                .withParameter(aParameter()
                        .withType(getPropertyTypeBuilder(property, false))
                        .withName(property.getFieldName()));

        if (constructorPosition > -1) {
            valueAssignmentMethod.withAnnotation(anAnnotation()
                    .withType(aType()
                            .withName("com.mistraltech.bog.core.annotation.ConstructorParameter"))
                    .withParameter(expressionText(String.valueOf(constructorPosition))));
        }

        methods.add(valueAssignmentMethod);

        methods.add(anInterfaceMethod()
                .withReturnType(returnType)
                .withName(setterMethodName(property))
                .withParameter(aParameter()
                        .withType(aType()
                                .withName(BUILDER_TYPE_NAME)
                                .withTypeBinding(aTypeParameter()
                                        .withType(boxedPropertyType)
                                        .withSubTypes(true)))
                        .withName(builderAttributeName(property))));

        return methods;
    }

    private InterfaceMethodBuilder generateBuilderGetters(@NotNull Property property) {
        TypeBuilder returnType = aType()
                .withName(EXPOSED_BUILDER_PROPERTY_TYPE_NAME)
                .withTypeBinding(getPropertyTypeBuilder(property, true));

        return anInterfaceMethod()
                .withReturnType(returnType)
                .withName(getterMethodName(property));
    }

    protected String getPropertyDefaultValue(@NotNull Property property) {
        return property.accept(new InitialValueTypeConverter());
    }


    private MethodBuilder generateBuilderDefaultGetter(@NotNull Property property) {
        TypeBuilder boxedPropertyType = getPropertyTypeBuilder(property, true);
        String defaultValue = getPropertyDefaultValue(property);

        TypeBuilder returnType = aType()
                .withName("java.util.function.Supplier")
                .withTypeBinding(boxedPropertyType);

        ExpressionBuilder defaultLambda = anExpression()
                .withTerm(aStaticMethodCall()
                        .withType(aType()
                                .withName("com.mistraltech.bog.core.picker.SingleValuePicker"))
                        .withName("singleValuePicker")
                        .withParameter(defaultValue));

        return aMethod()
                .withDefaultFlag(true)
                .withReturnType(returnType)
                .withName(defaultGetterMethodName(property))
                .withStatement(aReturnStatement()
                        .withExpression(defaultLambda));
    }


    private AnnotationBuilder buildsAnnotation() {
        return anAnnotation()
                .withType(aType()
                        .withName("com.mistraltech.bog.core.annotation.Builds"))
                .withParameter(aField()
                        .withType(getSourceClassFQName())
                        .withField("class"));
    }

    private String nestedClassName() {
        return generatorProperties.getClassName() + "Type";
    }

    private StaticVariableReaderBuilder typeToClass(TypeBuilder builderType) {
        return aStaticVariable()
                .withType(aType()
                        .withName(builderType.getTypeFQN()))
                .withName("class");
    }
}
