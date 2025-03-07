package com.github.lipinskipawel.leetcode.hash;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

class TwoSumTest implements WithAssertions {

    private final TwoSum twoSum = new TwoSum();

    private static Stream<Arguments> sums() {
        return Stream.of(
            of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
            of(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
            of(new int[]{3, 3}, 6, new int[]{0, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("sums")
    void twoSum(int[] input, int target, int[] expected) {
        final var result = twoSum.twoSum(input, target);

        assertThat(result).isEqualTo(expected);
    }
}
