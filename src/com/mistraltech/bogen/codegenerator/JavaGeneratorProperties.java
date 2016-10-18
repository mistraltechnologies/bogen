package com.mistraltech.bogen.codegenerator;

public abstract class JavaGeneratorProperties<T extends JavaGeneratorProperties> extends GeneratorProperties<T> {

    public String getFileName() {
        return getClassName() + ".java";
    }

}
