package main

// Maximum Subarray

import (
	"fmt"
)

type TestCase struct {
	input  []int
	output int
}

// Example 1
// Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
// Output: 6
//
// Example 2
// Input: nums = [1]
// Output: 1
//
// Example 3
// Input: nums = [5,4,-1,7,8]
// Output: 23
func main() {
	first := &TestCase{[]int{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6}
	fmt.Println(maxSubArray(first.input), " should be: ", first.output)

	second := &TestCase{[]int{1}, 1}
	fmt.Println(maxSubArray(second.input), " should be: ", second.output)

	third := &TestCase{[]int{5, 4, -1, 7, 8}, 23}
	fmt.Println(maxSubArray(third.input), " should be: ", third.output)
}

func maxSubArray(nums []int) int {
	currentMax := nums[0]
	localMax := 0

	for i := range nums {
		if localMax < 0 {
			localMax = 0
		}
		localMax += nums[i]
		currentMax = max(currentMax, localMax)
	}
	return currentMax
}
