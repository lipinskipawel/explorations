package main

// Remove Duplicates from Sorted Array II

import (
	"fmt"
)

type TestCase struct {
	input  []int
	target int
	start  []int
}

// Example 1
// Input: nums = [1,1,1,2,2,3], target = 5
// Output: [1,1,2,2,3]
//
// Example 2
// Input: nums = [0,0,1,1,1,1,2,3,3], target = 7
// Output: [0,0,1,1,2,3,3]
func main() {
	first := &TestCase{[]int{1, 1, 1, 2, 2, 3}, 5, []int{1, 1, 2, 2, 3}}
	fmt.Println(removeDuplicates(first.input), " should be: ", first.target, " and start with ", first.start, first.input)

	second := &TestCase{[]int{0, 0, 1, 1, 1, 1, 2, 3, 3}, 7, []int{0, 0, 1, 1, 2, 3, 3}}
	fmt.Println(removeDuplicates(second.input), " should be: ", second.target, " and start with ", second.start, second.input)
}

func removeDuplicates(nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	if len(nums) == 1 {
		return 1
	}
	left := 1 // inserting position
	atLeastTwo := false

	for r := 1; r < len(nums); r++ {
		previous := nums[r-1]
		current := nums[r]

		if previous == current {
			if !atLeastTwo {
				nums[left] = current
				left++
				atLeastTwo = true
			} else {
				// do nothing
			}
		}

		if previous != current {
			nums[left] = current
			left++
			atLeastTwo = false
		}
	}

	return left
}
