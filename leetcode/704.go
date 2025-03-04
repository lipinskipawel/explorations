package main

// Binary Search

import (
	"fmt"
)

type TestCase struct {
	input  []int
	target int
	output int
}

// Example 1
// Input: nums = [-1,0,3,5,9,12], target = 9
// Output: 4
//
// Example 2
// Input: nums = [-1,0,3,5,9,12], target = 9
// Output: -1
func main() {
	first := &TestCase{[]int{-1, 0, 3, 5, 9, 12}, 9, 4}
	fmt.Println(search(first.input, first.target), " should be ", first.output)

	second := &TestCase{[]int{-1, 0, 3, 5, 9, 12}, 2, -1}
	fmt.Println(search(second.input, second.target), " should be ", second.output)
}

func search(nums []int, target int) int {
	if len(nums) == 1 {
		if nums[0] == target {
			return 0
		}
		return -1
	}
	left := 0
	right := len(nums) - 1
	mid := (right - left) / 2

	for left != right-1 {
		if nums[mid] == target {
			return mid
		}
		if nums[mid] > target {
			right = mid
			mid = (right - left) / 2
		} else {
			left = mid
			mid = (right + left) / 2
		}
	}

	if nums[left] == target {
		return left
	}

	if nums[right] == target {
		return right
	}

	return -1
}
