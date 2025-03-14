package com.github.lipinskipawel.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 49
// https://leetcode.com/problems/group-anagrams/description/
final class AnagramGroup {

    public List<List<String>> groupAnagrams(String[] strs) {
        final Map<String, List<String>> anagrams = new HashMap<>();

        for (var str : strs) {
            final var chars = str.toCharArray();
            Arrays.sort(chars);
            final var key = new String(chars);

            if (!anagrams.containsKey(key)) {
                anagrams.put(key, new ArrayList<>());
            }

            anagrams.get(key).add(str);
        }

        return new ArrayList<>(anagrams.values());
    }
}
