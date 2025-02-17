package main

// Product of Array Except Self

import (
	"fmt"
	"slices"
)

type TestCase struct {
	nums   []int
	output []int
}

// Example 1
// Input: nums = [1,2,3,4]
// Output: [24,12,8,6]
//
// Example 2
// Input: nums = [-1,1,0,-3,3]
// Output: [0,0,9,0,0]
func main() {
	first := &TestCase{[]int{1, 2, 3, 4}, []int{24, 12, 8, 6}}
	fmt.Println(productExceptSelf(first.nums), " should be: ", first.output)

	second := &TestCase{[]int{-1, 1, 0, -3, 3}, []int{0, 0, 9, 0, 0}}
	fmt.Println(productExceptSelf(second.nums), " should be: ", second.output)
}

func productExceptSelf(nums []int) []int {
	res := slices.Repeat([]int{1}, len(nums))

	prefix := 1
	for i, n := range nums {
		res[i] = prefix
		prefix *= n
	}

	postfix := 1
	for i := len(nums) - 1; i >= 0; i-- {
		res[i] *= postfix
		postfix *= nums[i]
	}

	return res
}
