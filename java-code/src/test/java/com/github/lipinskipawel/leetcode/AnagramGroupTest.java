package com.github.lipinskipawel.leetcode;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class AnagramGroupTest implements WithAssertions {

    private final AnagramGroup anagramGroup = new AnagramGroup();

    @Test
    void first() {
        final var input = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};

        final var result = anagramGroup.groupAnagrams(input);

        assertThat(result).containsExactlyInAnyOrder(
            List.of("bat"),
            List.of("tan", "nat"),
            List.of("eat", "tea", "ate")
        );
    }

    @Test
    void second() {
        final var input = new String[]{""};

        final var result = anagramGroup.groupAnagrams(input);

        assertThat(result).isEqualTo(List.of(
            List.of("")
        ));
    }

    @Test
    void third() {
        final var input = new String[]{"a"};

        final var result = anagramGroup.groupAnagrams(input);

        assertThat(result).isEqualTo(List.of(
            List.of("a")
        ));
    }
}
