package main

// Merge Sorted Array

import "fmt"

// Example 1
// Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
// Output: [1,2,2,3,5,6]
//
// Example 2
// Input: nums1 = [1], m = 1, nums2 = [], n = 0
// Output: [1]
func merge(nums1 []int, m int, nums2 []int, n int) {
	last := m + n - 1

	for m > 0 && n > 0 {
		if nums1[m-1] > nums2[n-1] {
			nums1[last] = nums1[m-1]
			m--
		} else {
			nums1[last] = nums2[n-1]
			n--
		}
		last--
	}

	for n > 0 {
		nums1[last] = nums2[n-1]
		n--
		last--
	}
}

func main() {
	nums1 := []int{1, 2, 3, 0, 0, 0}
	merge(nums1, 3, []int{4, 5, 6}, 3)
	fmt.Println(nums1, []int{1, 2, 3, 4, 5, 6})

	nums1 = []int{1, 2, 3, 0, 0, 0}
	merge(nums1, 3, []int{2, 5, 6}, 3)
	fmt.Println(nums1, []int{1, 2, 2, 3, 5, 6})

	nums1 = []int{-2, -1, 0, 1, 0, 0}
	merge(nums1, 4, []int{-1, 8}, 2)
	fmt.Println(nums1, []int{-2, -1, -1, 0, 1, 8})

	nums1 = []int{1}
	merge(nums1, 1, []int{}, 0)
	fmt.Println(nums1, []int{1})

	nums1 = []int{0}
	merge(nums1, 0, []int{1}, 1)
	fmt.Println(nums1, []int{1})

	nums1 = []int{4, 5, 6, 0, 0, 0}
	merge(nums1, 3, []int{1, 2, 3}, 3)
	fmt.Println(nums1, []int{1, 2, 3, 4, 5, 6})
}
