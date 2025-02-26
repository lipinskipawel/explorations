package main

// Reverse Linked List

import (
	"fmt"
)

type TestCase struct {
	input  *ListNode
	output *ListNode
}

type ListNode struct {
	Val  int
	Next *ListNode
}

// Example 1
// Input: head = [1,2,3,4,5]
// Output: [5,4,3,2,1]
//
// Example 2
// Input: head = [1,2]
// Output: [2,1]
func main() {
	first := &TestCase{input: makeListNodes([]int{1, 2, 3, 4, 5}), output: makeListNodes([]int{5, 4, 3, 2, 1})}
	fmt.Println(toString(reverseList(first.input)), " should be: ", toString(first.output))

	second := &TestCase{input: makeListNodes([]int{1, 2}), output: makeListNodes([]int{2, 1})}
	fmt.Println(toString(reverseList(second.input)), " should be: ", toString(second.output))

	fmt.Println("recursive")
	third := &TestCase{input: makeListNodes([]int{1, 2, 3, 4, 5}), output: makeListNodes([]int{5, 4, 3, 2, 1})}
	fmt.Println(toString(reverseList(third.input)), " should be: ", toString(third.output))

	fourth := &TestCase{input: makeListNodes([]int{1, 2}), output: makeListNodes([]int{2, 1})}
	fmt.Println(toString(reverseList(fourth.input)), " should be: ", toString(fourth.output))
}

func reverseList(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}

	curr := head
	var prev *ListNode

	for curr != nil {
		next := curr.Next
		curr.Next = prev
		prev = curr
		curr = next
	}
	return prev
}

func reverseListRecursive(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}

	newHead := head
	if head.Next != nil {
		newHead = reverseListRecursive(head.Next)
		head.Next.Next = head
	}
	head.Next = nil

	return newHead
}

func toString(nodes *ListNode) []int {
	result := make([]int, 0, 8)
	currentNode := nodes

	for {
		if currentNode != nil {
			result = append(result, currentNode.Val)
			currentNode = currentNode.Next
		} else {
			break
		}
	}

	return result
}

func makeListNodes(values []int) *ListNode {
	nodes := make([]ListNode, 0, 8)

	for _, n := range values {
		nodes = append(nodes, ListNode{Val: n, Next: nil})
	}

	maxLen := len(nodes) - 1
	for i := range nodes {
		if i == maxLen {
			break
		}
		nodes[i].Next = &nodes[i+1]
	}

	return &nodes[0]
}
