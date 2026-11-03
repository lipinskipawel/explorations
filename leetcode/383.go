package main

// Ransom Note

import (
	"fmt"
)

type TestCase struct {
	ransomeNote string
	magazine    string
	output      bool
}

// Example 1
// Input: ransomNote = "a", magazine = "b"
// Output: false
//
// Example 2
// Input: ransomNote = "aa", magazine = "ab"
// Output: false
//
// Example 3
// Input: ransomNote = "aa", magazine = "aab"
// Output: true
func main() {
	first := &TestCase{"a", "b", false}
	fmt.Println(canConstruct(first.ransomeNote, first.magazine), " should be: ", first.output)

	second := &TestCase{"aa", "ab", false}
	fmt.Println(canConstruct(second.ransomeNote, second.magazine), " should be: ", second.output)

	third := &TestCase{"aa", "aab", true}
	fmt.Println(canConstruct(third.ransomeNote, third.magazine), " should be: ", third.output)
}

func canConstruct(ransomeNote string, magazine string) bool {
	return true
}
