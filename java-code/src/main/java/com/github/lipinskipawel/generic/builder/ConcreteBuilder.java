package com.github.lipinskipawel.generic.builder;

public final class ConcreteBuilder extends AbstractBuilder {
    private final int value;

    private ConcreteBuilder(Builder builder) {
        this.value = builder.value;
    }

    public void printConcrete() {
        System.out.println(this.getClass());
        System.out.println(value);
    }

    public static class Builder extends AbstractBuilder.Builder<Builder> {
        private int value;

        public Builder integerValue(int value) {
            this.value = value;
            return self();
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public ConcreteBuilder build() {
            return new ConcreteBuilder(this);
        }
    }
}
