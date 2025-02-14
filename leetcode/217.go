package main

// Contains Duplicate

import "fmt"

type TestCase struct {
	nums   []int
	output bool
}

// Example 1
// Input: nums = [1,2,3,1]
// Output: true
//
// Example 2
// Input: nums = [1,2,3,4]
// Output: false
//
// Example 3
// Input: nums = [1,1,1,3,3,4,3,2,4,2]
// Output: true
func main() {
	first := &TestCase{[]int{1, 2, 3, 1}, true}
	fmt.Printf("Those %v contains duplicate %v, testing =%v\n", first.nums, first.output, containsDuplicate(first.nums))

	second := &TestCase{[]int{1, 2, 3, 4}, false}
	fmt.Printf("Those %v contains duplicate %v, testing =%v\n", second.nums, second.output, containsDuplicate(second.nums))

	third := &TestCase{[]int{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, true}
	fmt.Printf("Those %v contains duplicate %v, testing =%v\n", third.nums, third.output, containsDuplicate(third.nums))
}

func containsDuplicate(nums []int) bool {
	valuesSoFar := make(map[int]int)

	for _, num := range nums {
		if _, ok := valuesSoFar[num]; ok {
			return true
		}
		valuesSoFar[num] = num
	}
	return false
}
