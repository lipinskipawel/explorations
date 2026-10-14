package main

// Remove Duplicates from Sorted Array

import (
	"fmt"
)

type TestCase struct {
	input []int
	k     int
	start []int
}

// Example 1
// Input: nums = [1,1,2]
// Output: 2, nums = [1,2,_]
//
// Example 2
// Input: nums = [0,0,1,1,1,2,2,3,3,4]
// Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
func main() {
	first := &TestCase{
		input: []int{1, 1, 2},
		k:     2,
		start: []int{1, 2},
	}
	fmt.Println(removeDuplicates(first.input), " should be: ", first.k, " and array ", []int{1, 2}, " must start with ", first.input)

	second := &TestCase{
		input: []int{0, 0, 1, 1, 1, 2, 2, 3, 3, 4},
		k:     5,
		start: []int{0, 1, 2, 3, 4},
	}
	fmt.Println(removeDuplicates(second.input), " should be: ", second.k, " and array ", []int{0, 1, 2, 3, 4}, " must start with ", second.input)

	third := &TestCase{
		input: []int{1, 2},
		k:     2,
		start: []int{1, 2},
	}
	fmt.Println(removeDuplicates(third.input), " should be: ", third.k, " and array ", []int{1, 2}, " must start with ", third.input)
}

func removeDuplicates(nums []int) int {
	if len(nums) == 0 {
		return 0
	}
	if len(nums) == 1 {
		return 1
	}
	l := 1

	for r := 1; r < len(nums); r++ {
		if nums[r-1] != nums[r] {
			nums[l] = nums[r]
			l++
		}
	}
	return l
}
