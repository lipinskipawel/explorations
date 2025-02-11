package main

// Two Sum

import (
	"fmt"
)

type TestCase struct {
	input  []int
	target int
	output []int
}

// Example 1
// Input: nums = [2,7,11,15], target = 9
// Output: [0,1]
//
// Example 2
// Input: nums = [3,2,4], target = 6
// Output: [1,2]
//
// Example 3
// Input: nums = [3,3], target = 6
// Output: [0,1]
func main() {
	first := &TestCase{[]int{2, 7, 11, 15}, 9, []int{0, 1}}
	fmt.Println(twoSum(first.input, first.target), " should be: ", first.output)

	second := &TestCase{[]int{3, 2, 4}, 6, []int{1, 2}}
	fmt.Println(twoSum(second.input, second.target), " should be: ", second.output)

	thrid := &TestCase{[]int{3, 3}, 6, []int{0, 1}}
	fmt.Println(twoSum(thrid.input, thrid.target), " should be: ", thrid.output)
}

func twoSum(nums []int, target int) []int {
	numbers := make(map[int]int)

	for i, num := range nums {
		seek := target - num
		if index, ok := numbers[seek]; ok {
			if nums[index]+num == target {
				return []int{numbers[seek], i}
			}
		}
		numbers[num] = i
	}
	return []int{0, 0}
}
