package com.github.lipinskipawel.leetcode.pointers;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class BinarySearchTest implements WithAssertions {

    private final BinarySearch binarySearch = new BinarySearch();

    private static Stream<Arguments> searches() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4, 5), 2, 1),
            Arguments.of(List.of(1, 2, 3, 4, 5), 1, 0),
            Arguments.of(List.of(1, 2, 3, 4, 5), 4, 3),
            Arguments.of(List.of(1, 2, 3, 4, 5), 5, 4),
            Arguments.of(List.of(1, 2, 3, 4, 5), 3, 2),
            Arguments.of(List.of(1, 2, 3, 4, 5), 6, -1),
            Arguments.of(List.of(1, 2), 1, 0),
            Arguments.of(List.of(1, 2), 2, 1),
            Arguments.of(List.of(1), 2, -1),
            Arguments.of(List.of(1), 1, 0),
            Arguments.of(List.of(1, 2, 3, 4), 1, 0),
            Arguments.of(List.of(1, 2, 3, 4), 2, 1),
            Arguments.of(List.of(1, 2, 3, 4), 3, 2),
            Arguments.of(List.of(1, 2, 3, 4), 4, 3),
            Arguments.of(List.of(1, 2, 3, 4), 5, -1)
        );
    }

    @ParameterizedTest
    @MethodSource("searches")
    void search(List<Integer> list, int target, int expected) {
        final var result = binarySearch.search(list, target);

        assertThat(result).isEqualTo(expected);
    }
}
