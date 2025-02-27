package main

// Maximum Product Subarray

import (
	"fmt"
)

type TestCase struct {
	nums   []int
	output int
}

// Example 1
// Input: nums = [2,3,-2,4]
// Output: 6
//
// Example 2
// Input: nums = [-2,0,-1]
// Output: 0
func main() {
	first := &TestCase{[]int{2, 3, -2, 4}, 6}
	fmt.Println(maxProduct(first.nums), " should be: ", first.output)

	second := &TestCase{[]int{-2, 0, -1}, 0}
	fmt.Println(maxProduct(second.nums), " should be: ", second.output)
}

func maxProduct(nums []int) int {
	res := maximum(nums)
	curMin, curMax := 1, 1

	for _, n := range nums {
		if n == 0 {
			curMin, curMax = 1, 1
			continue
		}

		tmp := curMax * n
		curMax = max(n*curMax, n*curMin, n)
		curMin = min(tmp, n*curMin, n)

		res = max(res, curMax, curMin)
	}

	return res
}

func maximum(array []int) int {
	res := array[0]
	for _, n := range array {
		if res < n {
			res = n
		}
	}
	return res
}
