package main

// Majority Element

import (
	"fmt"
)

type TestCase struct {
	nums   []int
	output int
}

// Example 1
// Input: nums = [3,2,3]
// Output: 3
//
// Example 2
// Input: nums = [2,2,1,1,1,2,2]
// Output: 2
func main() {
	first := &TestCase{[]int{3, 2, 3}, 3}
	fmt.Println(majorityElement(first.nums), " should be: ", first.output)

	second := &TestCase{[]int{2, 2, 1, 1, 1, 2, 2}, 2}
	fmt.Println(majorityElement(second.nums), " should be: ", second.output)

	third := &TestCase{[]int{6, 5, 5}, 5}
	fmt.Println(majorityElement(third.nums), " should be: ", third.output)
}

// Boyer–Moore Voting Algorithm
func majorityElement(nums []int) int {
	candidate := 0
	count := 0

	for _, v := range nums {
		if count == 0 {
			candidate = v
			// this will work without those 2 lines
			count = 1
			continue
		}
		if candidate == v {
			count += 1
		} else {
			count -= 1
		}
	}

	return candidate
}
