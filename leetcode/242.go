package main

// Valid Anagram

import (
	"fmt"
	"sort"
)

type TestCase struct {
	first  string
	second string
	output bool
}

// Example 1
// Input: s = "anagram", t = "nagaram"
// Output: true
//
// Example 2
// Input: s = "rat", t = "car"
// Output: fase
func main() {
	first := &TestCase{"anagram", "nagaram", true}
	fmt.Println(isAnagram(first.first, first.second), " should be: ", first.output)

	second := &TestCase{"rat", "car", false}
	fmt.Println(isAnagram(second.first, second.second), " should be: ", second.output)
}

func isAnagram(s string, t string) bool {
	a := []rune(s)
	b := []rune(t)
	sort.Slice(a, func(i int, j int) bool {
		return a[i] < a[j]
	})
	sort.Slice(b, func(i int, j int) bool {
		return b[i] < b[j]
	})
	return string(a) == string(b)
}
