package main

// Longest Consecutive Sequence

import (
	"fmt"
)

type TestCase struct {
	input  []int
	output int
}

// Example 1
// Input: [100, 4, 200, 1, 3, 2]
// Output: 4
//
// Example 2
// Input: [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
// Output: 9
//
// Example 3
// Input: [1, 0, 1, 2]
// Output: 3
func main() {
	first := &TestCase{[]int{100, 4, 200, 1, 3, 2}, 4}
	fmt.Println(longestConsecutive(first.input), " should be: ", first.output)

	second := &TestCase{[]int{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9}
	fmt.Println(longestConsecutive(second.input), " should be: ", second.output)

	third := &TestCase{[]int{1, 0, 1, 2}, 3}
	fmt.Println(longestConsecutive(third.input), " should be: ", third.output)
}

func longestConsecutive(nums []int) int {
	set := toMap(nums)
	longest := 0

	for _, current := range nums {
		// missing values in map return 0,
		// here we can omit the val, ok := set[current-1]
		// because false (or 0) is not a valid case for us
		if set[current-1] {
			continue
		}

		length := 1
		for set[current+length] {
			length++
		}
		longest = max(longest, length)
	}
	return longest
}

func toMap(nums []int) map[int]bool {
	set := make(map[int]bool, len(nums))

	for _, v := range nums {
		set[v] = true
	}

	return set
}
