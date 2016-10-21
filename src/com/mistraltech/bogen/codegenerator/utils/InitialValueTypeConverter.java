package com.mistraltech.bogen.codegenerator.utils;

import com.intellij.psi.PsiArrayType;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiPrimitiveType;
import com.intellij.psi.PsiTypeVisitor;
import org.jetbrains.annotations.Nullable;

public class InitialValueTypeConverter extends PsiTypeVisitor<String> {

    @Nullable
    @Override
    public String visitPrimitiveType(PsiPrimitiveType primitiveType) {
        switch (primitiveType.getInternalCanonicalText()) {
            case "byte":
                return "(byte)0";
            case "char":
                return "'\\0'";
            case "short":
                return "(short)0";
            case "int":
                return "0";
            case "long":
                return "0L";
            case "float":
                return "0.0F";
            case "double":
                return "0.0D";
            default:
                return "null";
        }
    }

    @Nullable
    @Override
    public String visitArrayType(PsiArrayType arrayType) {
        return "null";
    }

    @Nullable
    @Override
    public String visitClassType(PsiClassType classType) {
        return "null";
    }
}
