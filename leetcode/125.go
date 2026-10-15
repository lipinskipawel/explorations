package main

// Valid Palindrome

import (
	"fmt"
	"regexp"
	"strings"
)

type TestCase struct {
	input  string
	output bool
}

// Example 1
// Input: s = "A man, a plan, a canal: Panama"
// Output: true
//
// Example 2
// Input: s = "race a car"
// Output: false
//
// Example 3
// Input: s = " "
// Output: true
func main() {
	first := &TestCase{"A man, a plan, a canal: Panama", true}
	fmt.Println(isPalindrome(first.input), " should be: ", first.output)

	second := &TestCase{"race a car", false}
	fmt.Println(isPalindrome(second.input), " should be: ", second.output)

	third := &TestCase{"abba", true}
	fmt.Println(isPalindrome(third.input), " should be: ", third.output)

	fouth := &TestCase{"abcba", true}
	fmt.Println(isPalindrome(fouth.input), " should be: ", fouth.output)

	fifth := &TestCase{" ", true}
	fmt.Println(isPalindrome(fifth.input), " should be: ", fifth.output)
}

func isPalindrome(s string) bool {
	var nonAlnum = regexp.MustCompile(`[^a-zA-Z0-9]`)

	clean := strings.ToLower(nonAlnum.ReplaceAllString(s, ""))

	l := 0
	r := len(clean) - 1
	for l < r {
		if clean[l] == clean[r] {
			l++
			r--
			continue
		}
		return false
	}

	return true
}
