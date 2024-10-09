package com.github.lipinskipawel.sorting;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

class ReverseStreamTest implements WithAssertions {

    @Test
    void reverse() {
        var numbers = Stream.of(1, 2, 3, 4, 5);

        var reverse = numbers.sorted(reverseOrder()).toList();

        assertThat(reverse).containsExactlyElementsOf(List.of(5, 4, 3, 2, 1));
    }

    @Test
    void reverse_holder() {
        var now = Instant.now();
        var nowHolder = new Holder(now);
        var minuteAgoHolder = new Holder(now.minus(60, SECONDS));
        var halfAgoHolder = new Holder(now.minus(30, SECONDS));
        var holders = Stream.of(nowHolder, minuteAgoHolder, halfAgoHolder);

        var reversed = holders
//            .sorted(comparing((Holder it) -> it.time()).reversed())
            .sorted(comparing(Holder::time).reversed())
            .toList();

        assertThat(reversed).containsExactlyElementsOf(List.of(nowHolder, halfAgoHolder, minuteAgoHolder));
    }
}
