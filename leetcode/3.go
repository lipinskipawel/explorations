package main

// Longest Substring Without Repeating Characters

import (
	"fmt"
)

type TestCase struct {
	input  string
	output int
}

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
	first := &TestCase{input: "abcabcbb", output: 3}
	second := &TestCase{input: "bbbbb", output: 1}
	third := &TestCase{input: "pwwkew", output: 3}

	fmt.Println(lengthOfLongestSubstring(first.input), " should be ", first.output)
	fmt.Println(lengthOfLongestSubstring(second.input), " should be ", second.output)
	fmt.Println(lengthOfLongestSubstring(third.input), " should be ", third.output)
}

func lengthOfLongestSubstring(s string) int {
	result := 0
	left := 0
	right := 0

	seenLetters := make(map[byte]int)

	for right < len(s) {
		_, saw := seenLetters[s[right]]
		if !saw {
			seenLetters[s[right]] = 1
			result = max(result, len(seenLetters))
		} else {
			result = max(result, len(seenLetters))
			_, saw := seenLetters[s[right]]
			for saw {
				delete(seenLetters, s[left])
				left += 1
				_, saw = seenLetters[s[right]]
			}
			seenLetters[s[right]] = 1
		}

		right += 1
	}

	return result
}
