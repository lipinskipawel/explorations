package com.github.lipinskipawel.leetcode.pointers;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

class LongestSubStringTest implements WithAssertions {

    private final LongestSubString longestSubString = new LongestSubString();

    private static Stream<Arguments> strings() {
        return Stream.of(
            of("abcabcbb", 3),
            of("bbbbb", 1),
            of("pwwkew", 3),
            of(" ", 1),
            of("dvdf", 3)
        );
    }

    @ParameterizedTest
    @MethodSource("strings")
    void subString(String input, int expected) {
        final var result = longestSubString.lengthOfLongestSubstring(input);

        assertThat(result).isEqualTo(expected);
    }
}
