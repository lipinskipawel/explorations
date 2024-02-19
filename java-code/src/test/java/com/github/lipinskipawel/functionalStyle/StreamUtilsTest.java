package com.github.lipinskipawel.functionalStyle;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static com.github.lipinskipawel.functionalStyle.StreamUtils.zip;

final class StreamUtilsTest implements WithAssertions {

    @Test
    void shouldZipStreams() {
        final var first = Stream.of("sdf", "SDf");
        final var second = Stream.of("1", "324");

        final var zipped = zip(first, second, (a, b) -> a + "-" + b).toList();

        assertThat(zipped).containsExactlyInAnyOrder("sdf-1", "SDf-324");
    }
}
