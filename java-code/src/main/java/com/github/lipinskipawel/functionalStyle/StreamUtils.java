package com.github.lipinskipawel.functionalStyle;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public class StreamUtils {

    public static <A, B, C> Stream<C> zip(
            final Stream<? extends A> first,
            final Stream<? extends B> second,
            final BiFunction<? super A, ? super B, ? extends C> zipper
    ) {
        final var iterator = second.iterator();
        return first
                .filter(x -> iterator.hasNext())
                .map(x -> zipper.apply(x, iterator.next()));
    }
}