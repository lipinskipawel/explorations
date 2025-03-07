package com.github.lipinskipawel.leetcode.hash;

import java.util.HashMap;

// 1
// https://leetcode.com/problems/two-sum/description/
final class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        final var result = new int[2];
        final var set = new HashMap<Integer, Integer>();

        for (var i = 0; i < nums.length; i++) {
            final var diff = target - nums[i];
            if (set.containsKey(diff)) {
                result[0] = set.get(diff);
                result[1] = i;
                return result;
            }
            set.put(nums[i], i);
        }

        return result;
    }
}
