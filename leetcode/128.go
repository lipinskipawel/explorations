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
	set := newIntSet(nums)
	longest := 0

	for _, current := range nums {
		if set.has(current - 1) {
			continue
		}

		length := 1
		for set.has(current + length) {
			length++
		}
		longest = max(longest, length)
	}
	return longest
}

// struct{} - is type, struct without any fields, 0 bytes
type intSet map[int]struct{}

func newIntSet(nums []int) intSet {
	set := make(intSet, len(nums))

	for _, v := range nums {
		// struct{} - type
		// {} - value
		set[v] = struct{}{}
	}

	return set
}

func (s intSet) has(v int) bool {
	_, ok := s[v]
	return ok
}
