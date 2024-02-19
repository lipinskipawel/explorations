package com.github.lipinskipawel.generic.abstractExample;

import java.util.UUID;

/**
 * This is a more extended version of fluent abstract builder.
 * This pattern is called with many names but to me the "self-bounding type" or "self-bound" make the most sense since
 * the class declares that only generic types that are valid are the once that put "self" as parameter.
 * This is a nice way of writing self pattern is Java since, Java is missing this functionality. Java 'this' reference
 * does not have type information.
 * <p>
 * This implementation is an enhancement over the one is the builder package because here we don't require overriding
 * the abstract self method. We simply return the generic type. This program is valid since we are using self-bounding
 * pattern. AbstractClass is generic over 2 parameters. This is unnecessary and only shows that it is possible to have
 * two self-bound types is a class. I used it because it is implemented also in assertJ, so I wanted to learn by writing
 * my own version (AbstractAssert).
 * <p>
 * Resources:
 * - <a href="http://bit.ly/1IZIRcY">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>
 * - <a href="https://unlinkedlist.org/2023/04/05/java-recursive-generics/">unlinkedlist.org blog</a>
 * - <a href="https://www.reddit.com/r/java/comments/12cygik/recursive_generics/">Reddit thread</a>
 * - <a href="https://stackoverflow.com/questions/211143/java-enum-definition">Stackoverflow question about Java enum</a>
 *
 * @param <A> self-bound type of class to build
 * @param <B> self-bound type of builder class
 */
public abstract class AbstractClass<A extends AbstractClass<A, B>, B extends AbstractClass.Builder<A, B>> {
    private final String abstractText;
    private final int abstractValue;
    private final UUID uuid;

    public AbstractClass(B builder) {
        this.abstractText = builder.abstractText;
        this.abstractValue = builder.abstractValue;
        this.uuid = builder.uuid;
    }

    public static abstract class Builder<A extends AbstractClass<A, B>, B extends Builder<A, B>> {
        String abstractText;
        int abstractValue;
        UUID uuid;

        public Builder() {

        }

        public B abstractText(String abstractText) {
            this.abstractText = abstractText;
            return (B) this;
        }

        public B abstractValue(int abstractValue) {
            this.abstractValue = abstractValue;
            return (B) this;
        }

        public B uuid(UUID uuid) {
            this.uuid = uuid;
            return (B) this;
        }

        abstract A build();
    }
}
