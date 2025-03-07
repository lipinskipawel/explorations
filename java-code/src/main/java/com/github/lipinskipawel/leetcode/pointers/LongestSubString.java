package com.github.lipinskipawel.leetcode.pointers;

import java.util.HashSet;

// 3
// https://leetcode.com/problems/longest-substring-without-repeating-characters/
final class LongestSubString {

    public int lengthOfLongestSubstring(String s) {
        var longest = 0;
        var left = 0;

        final var characters = new HashSet<Character>();

        for (var i = 0; i < s.length(); i++) {
            if (!characters.contains(s.charAt(i))) {
                characters.add(s.charAt(i));
                longest = Math.max(longest, characters.size());
            } else {
                longest = Math.max(longest, characters.size());
                while (characters.contains(s.charAt(i))) {
                    characters.remove(s.charAt(left));
                    left += 1;
                }
                characters.add(s.charAt(i));
            }
        }

        return longest;
    }
}
