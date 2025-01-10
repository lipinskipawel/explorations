package main

//Fizz Buzz

import (
	"fmt"
	"strconv"
)

// Given an integer n, return a string array answer (1-indexed) where:
//
//	answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
//	answer[i] == "Fizz" if i is divisible by 3.
//	answer[i] == "Buzz" if i is divisible by 5.
//	answer[i] == i (as a string) if none of the above conditions are true.
//
// Example 1:
// Input: n = 3
// Output: ["1","2","Fizz"]
//
// Example 2:
// Input: n = 5
// Output: ["1","2","Fizz","4","Buzz"]
//
// Example 3:
// Input: n = 15
// Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
func main() {
	fmt.Println(fizzBuzz(3))
	fmt.Println(fizzBuzz(5))
	fmt.Println(fizzBuzz(15))
}

func fizzBuzz(number int) []string {
	result := make([]string, 0, number)

	for i := 1; i < number+1; i++ {
		if i%3 == 0 && i%5 == 0 {
			result = append(result, "FizzBuzz")
			continue
		}
		if i%3 == 0 {
			result = append(result, "Fizz")
			continue
		}

		if i%5 == 0 {
			result = append(result, "Buzz")
			continue
		}
		result = append(result, strconv.Itoa(i))
	}

	return result
}
