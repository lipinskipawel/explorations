package com.github.lipinskipawel.generic.abstractExample;

public final class ConcreteClass extends AbstractClass<ConcreteClass, ConcreteClass.Builder> {
    private final double precision;

    public ConcreteClass(Builder builder) {
        super(builder);
        this.precision = builder.precision;
    }

    public void printConcreteClass() {
        System.out.println(this.getClass());
        System.out.println("Precision: " + precision);
    }

    public static class Builder extends AbstractClass.Builder<ConcreteClass, ConcreteClass.Builder> {
        private double precision;

        private Builder() {

        }

        public static ConcreteClass.Builder conreteBuilder() {
            return new ConcreteClass.Builder();
        }

        public ConcreteClass.Builder precision(double precision) {
            this.precision = precision;
            return this;
        }

        @Override
        public ConcreteClass build() {
            return new ConcreteClass(this);
        }
    }
}
