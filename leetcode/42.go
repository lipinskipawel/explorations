package main

// Trapping Rain Water

import "fmt"

type TestCase struct {
	input  []int
	output int
}

// Example 1
// Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
//
// Example 2
// Input: height = [4,2,0,3,2,5]
// Output: 9
func main() {
	first := &TestCase{[]int{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6}
	fmt.Println(trapBetterMemory(first.input), " should be: ", first.output)

	second := &TestCase{[]int{4, 2, 0, 3, 2, 5}, 9}
	fmt.Println(trapBetterMemory(second.input), " should be: ", second.output)
}

func trapBetterMemory(height []int) int {
	if height == nil {
		return 0
	}

	left := 0
	right := len(height) - 1

	leftMax := height[left]
	rightMax := height[right]
	result := 0

	for left < right {
		if leftMax < rightMax {
			left += 1
			leftMax = max(leftMax, height[left])
			result += leftMax - height[left]
		} else {
			right -= 1
			rightMax = max(rightMax, height[right])
			result += rightMax - height[right]
		}
	}
	return result
}

func trap(height []int) int {
	if height == nil {
		return 0
	}

	maxLeft := make([]int, len(height))
	maxRight := make([]int, len(height))
	minSlice := make([]int, len(height))

	maxValue := 0
	for i, n := range height {
		maxLeft[i] = max(maxValue, n)
		if n > maxValue {
			maxValue = n
		}
	}

	maxValue = 0
	for i := len(height) - 1; i >= 0; i-- {
		maxRight[i] = max(maxValue, height[i])
		if height[i] > maxValue {
			maxValue = height[i]
		}
	}

	for i := 0; i < len(height)-1; i++ {
		minSlice[i] = min(maxLeft[i], maxRight[i])
	}

	result := 0
	for i := 0; i < len(height)-1; i++ {
		if diff := minSlice[i] - height[i]; diff > 0 {
			result += diff
		}
	}

	return result
}
