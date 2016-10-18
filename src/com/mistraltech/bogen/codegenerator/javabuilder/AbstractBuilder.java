package com.mistraltech.bogen.codegenerator.javabuilder;

abstract class AbstractBuilder<R extends AbstractBuilder> implements Builder {
    AbstractBuilder() {
    }

    @SuppressWarnings("unchecked")
    R self() {
        return (R) this;
    }
}
