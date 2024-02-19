package com.github.lipinskipawel.generic.builder;

/**
 * This is a example of a abstract builder used in building fluent API.
 */
public abstract class AbstractBuilder {

    public abstract static class Builder<T extends Builder<T>> {
        private String abstractText;

        public T abstractText(String exampleText) {
            this.abstractText = exampleText;
            return self(); // or just simply return (T) this;
        }

        abstract T self();

        public abstract AbstractBuilder build();
    }
}


