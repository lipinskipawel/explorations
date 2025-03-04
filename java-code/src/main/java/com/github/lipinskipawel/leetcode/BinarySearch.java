package com.github.lipinskipawel.leetcode;

import java.util.List;

// 704
// https://leetcode.com/problems/binary-search/description/
final class BinarySearch {

    public int search(List<Integer> list, int target) {
        var left = 0;
        var right = list.size() - 1;

        while (left <= right) {
            var mid = (left + right) / 2;
            if (list.get(mid) > target) {
                right = mid - 1;
            } else if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
