package main

// Median of Two Sorted Arrays

import (
	"fmt"
	"math"
)

type TestCase struct {
	nums1  []int
	nums2  []int
	output float64
}

// Example 1
// Input: nums1 = [1,3], nums2 = [2]
// Output: 2.00000
//
// Example 2
// Input: nums1 = [1,2], nums2 = [3, 4]
// Output: 2.50000
func main() {
	first := &TestCase{[]int{1, 3}, []int{2}, 2.0}
	fmt.Println(findMedianSortedArrays(first.nums1, first.nums2), " should be: ", first.output)

	second := &TestCase{[]int{1, 2}, []int{3, 4}, 2.5}
	fmt.Println(findMedianSortedArrays(second.nums1, second.nums2), " should be: ", second.output)
}

func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	A, B := nums1, nums2
	total := len(nums1) + len(nums2)
	half := (total + 1) / 2

	var Aleft, Aright float64
	var Bleft, Bright float64

	if len(B) < len(A) {
		A, B = B, A
	}

	l, r := 0, len(A)-1
	for {
		i := (l + r) >> 1 // A
		j := half - i - 2 // B

		if i >= 0 {
			Aleft = float64(A[i])
		} else {
			Aleft = math.Inf(-1)
		}

		if (i + 1) < len(A) {
			Aright = float64(A[i+1])
		} else {
			Aright = math.Inf(1)
		}

		if j >= 0 {
			Bleft = float64(B[j])
		} else {
			Bleft = math.Inf(-1)
		}

		if (j + 1) < len(B) {
			Bright = float64(B[j+1])
		} else {
			Bright = math.Inf(1)
		}

		// partition is correct
		if Aleft <= Bright && Bleft <= Aright {
			// odd
			if total%2 == 1 {
				return max(Aleft, Bleft)
			}
			// even
			return (max(Aleft, Bleft) + min(Aright, Bright)) / 2
		} else if Aleft > Bright {
			r = i - 1
		} else {
			l = i + 1
		}
	}
}

func leftCheck(i int, A []int) int {
	if i < 0 {
		return math.MinInt64
	}
	return A[i]
}

func rightCheck(i int, A []int) int {
	if i > len(A) {
		return math.MaxInt64
	}
	return A[i]
}
