package main

// Longest Substring Without Repeating Characters

import (
	"fmt"
)

// Example 1
// Input: s = "abcabcbb"
// Output: 3
//
// Example 2
// Input: s = "bbbbb"
// Output: 1
//
// Example 3
// Input: s = "pwwkew"
// Output: 3
func main() {
	first := "abcabcbb"
	second := "bbbbb"
	third := "pwwkew"

	fmt.Println(lengthOfLongestSubstring(first))
	fmt.Println(lengthOfLongestSubstring(second))
	fmt.Println(lengthOfLongestSubstring(third))
}

func lengthOfLongestSubstring(str string) int {
	original := []rune(str)
	if len(original) == 0 {
		return 0
	}
	left := 0
	right := 1
	maxResult := 1

	seenLetters := make([]rune, 0, 8)
	seenLetters = append(seenLetters, original[left])
	result := 1

	for right < len(str) {
		if !containsRune(seenLetters, original[right]) {
			result++
			maxResult = max(maxResult, result)
		} else {
			maxResult = max(maxResult, result)
			result = 1
			left = right
			seenLetters = make([]rune, 0, 8)
		}

		seenLetters = append(seenLetters, original[right])
		right++
	}
	return maxResult
}

func containsRune(chars []rune, char rune) bool {
	for i := 0; i < len(chars); i++ {
		if chars[i] == char {
			return true
		}
	}
	return false
}
