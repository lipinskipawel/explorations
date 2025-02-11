package main

// Minimum Window Substring

import (
	"fmt"
)

type TestCase struct {
	s      string
	t      string
	output string
}

// Example 1
// Input: s = "ADOBECODEBANC", t = "ABC"
// Output: "BANC"
//
// Example 2
// Input: s = "a", t = "a"
// Output: "a"
//
// Example 3
// Input: s = "a", t = "aa"
// Output: ""
func main() {
	first := &TestCase{
		s:      "ADOBECODEBANC",
		t:      "ABC",
		output: "BANC",
	}
	fmt.Println(minWindow(first.s, first.t), " should be: ", first.output)

	second := &TestCase{
		s:      "a",
		t:      "a",
		output: "a",
	}
	fmt.Println(minWindow(second.s, second.t), " should be: ", second.output)
	third := &TestCase{
		s:      "a",
		t:      "aa",
		output: "",
	}
	fmt.Println(minWindow(third.s, third.t), " should be: ", third.output)
}

func minWindow(s string, t string) string {
	left, right := 0, 0
	haveMap := make(map[uint8]int)
	needMap := make(map[uint8]int)

	have := 0
	minSubstring := ""

	for index := range t {
		needMap[t[index]]++
	}

	for right < len(s) {
		haveMap[s[right]]++

		if needMap[s[right]] != 0 &&
			needMap[s[right]] == haveMap[s[right]] {
			have++
		}

		for have == len(needMap) {
			if minSubstring == "" {
				minSubstring = s[left : right+1]
			}
			if right-left+1 < len(minSubstring) {
				minSubstring = s[left : right+1]
			}

			haveMap[s[left]]--
			if haveMap[s[left]] < needMap[s[left]] {
				have--
			}

			left++
		}
		right++
	}

	return minSubstring
}
