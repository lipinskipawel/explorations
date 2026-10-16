package main

// Is Subsequence

import (
	"fmt"
	"strings"
)

type TestCase struct {
	s      string
	t      string
	result bool
}

// Example 1
// Input: s = "abc", t = "ahbgdc"
// Output: true
//
// Example 2
// Input: s = "axc", t = "ahbgdc"
// Output: false
func main() {
	first := &TestCase{s: "abc", t: "ahbgdc", result: true}
	second := &TestCase{s: "axc", t: "ahbgdc", result: false}
	third := &TestCase{s: "", t: "ahbgdc", result: true}
	fourth := &TestCase{s: "bb", t: "ahbgdc", result: false}

	fmt.Println(isSubsequence(first.s, first.t), " should be ", first.result)
	fmt.Println(isSubsequence(second.s, second.t), " should be ", second.result)
	fmt.Println(isSubsequence(third.s, third.t), " should be ", third.result)
	fmt.Println(isSubsequence(fourth.s, fourth.t), " should be ", fourth.result)
}

func isSubsequence(s string, t string) bool {
	if strings.TrimSpace(s) == "" {
		return true
	}
	l := 0

	for r := 0; r < len(t); r++ {
		if s[l] == t[r] {
			l++
			if l >= len(s) {
				return true
			}
		}
	}

	return false
}
