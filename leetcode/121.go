package main

// Best Time to Buy and Sell Stock

import (
	"fmt"
)

// Example 1
// Input: prices = [7,1,5,3,6,4]
// Output: 5
//
// Example 2
// Input: prices = [7,6,4,3,1]
// Output: 0
func main() {
	first := []int{7, 1, 5, 3, 6, 4}
	second := []int{7, 6, 4, 3, 1}

	fmt.Println(maxProfit(first))
	fmt.Println(maxProfit(second))
}

func maxProfit(prices []int) int {
	left := 0
	right := 1

	maxProfit := 0

	for right < len(prices) {
		if prices[left] < prices[right] {
			profit := prices[right] - prices[left]
			maxProfit = max(maxProfit, profit)
		} else {
			left = right
		}
		right += 1
	}

	return maxProfit
}
