package main

// Rotate Array

import (
	"fmt"
)

type TestCase struct {
	input  []int
	k      int
	output []int
}

// Example 1
// Input: nums = [1,2,3,4,5,6,7], k = 3
// Output: [5,6,7,1,2,3,4]
//
// Example 2
// Input: nums = [-1,-100,3,99], k = 2
// Output: [3,99,-1,-100]
func main() {
	first := &TestCase{[]int{1, 2, 3, 4, 5, 6, 7}, 3, []int{5, 6, 7, 1, 2, 3, 4}}
	rotate(first.input, first.k)
	fmt.Println(first.input, " should be: ", first.output)

	second := &TestCase{[]int{-1, -100, 3, 99}, 2, []int{3, 99, -1, -100}}
	rotate(second.input, second.k)
	fmt.Println(second.input, " should be: ", second.output)
}

func rotate(nums []int, k int) {
	k = k % len(nums)

	l, r := 0, len(nums)-1
	for l < r {
		nums[l], nums[r] = nums[r], nums[l]
		l, r = l+1, r-1
	}

	l, r = 0, k-1
	for l < r {
		nums[l], nums[r] = nums[r], nums[l]
		l, r = l+1, r-1
	}

	l, r = k, len(nums)-1
	for l < r {
		nums[l], nums[r] = nums[r], nums[l]
		l, r = l+1, r-1
	}
}
