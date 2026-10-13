package main

// Remove Element

import (
	"fmt"
)

type TestCase struct {
	nums  []int
	val   int
	k     int
	start []int
}

// Example 1
// Input: nums = [3,2,2,3], val = 3
// Output: 2, nums = [2,2,_,_]
//
// Example 2
// Input: nums = [0,1,2,2,3,0,4,2], val = 2
// Output: 5, nums = [0,1,4,0,3,_,_,_]
func main() {
	first := &TestCase{
		nums:  []int{3, 2, 2, 3},
		val:   3,
		k:     2,
		start: []int{2, 2},
	}
	fmt.Println("Input array: ", []int{3, 2, 2, 3}, removeElement(first.nums, first.val), " should be: ", first.k, "and starts with: ", first.start)

	second := &TestCase{
		nums:  []int{0, 1, 2, 2, 4, 0, 4, 2},
		val:   2,
		k:     5,
		start: []int{0, 1, 4, 0, 3},
	}
	fmt.Println("Input array: ", []int{0, 1, 2, 2, 4, 0, 4, 2}, removeElement(second.nums, second.val), " should be: ", second.k, "and starts with: ", second.start)

	third := &TestCase{
		nums:  []int{1},
		val:   1,
		k:     0,
		start: []int{1},
	}
	fmt.Println("Input array: ", []int{1}, removeElement(third.nums, third.val), " should be: ", third.k, "and starts with: ", third.start)

	fourth := &TestCase{
		nums:  []int{3, 3},
		val:   3,
		k:     0,
		start: []int{3, 3},
	}
	fmt.Println("Input array: ", []int{3, 3}, removeElement(fourth.nums, fourth.val), " should be: ", fourth.k, "and starts with: ", fourth.start)

	fifth := &TestCase{
		nums:  []int{1},
		val:   2,
		k:     1,
		start: []int{1},
	}
	fmt.Println("Input array: ", []int{1}, removeElement(fifth.nums, fifth.val), " should be: ", fifth.k, "and starts with: ", fifth.start)
}

func removeElement(nums []int, val int) int {
	if len(nums) == 1 {
		if nums[0] == val {
			return 0
		} else {
			return 1
		}
	}
	start := 0
	end := len(nums) - 1
	count := 0

	for start <= end {
		if nums[start] == val {

			index := -1
			for end > start {
				if nums[end] == val {
					end--
				} else {
					index = end
					break
				}
			}

			if index == -1 {
				break
			}

			var tmp = nums[start]
			nums[start] = nums[index]
			nums[index] = tmp
			end--
		}
		count++
		start++
	}

	return count
}
