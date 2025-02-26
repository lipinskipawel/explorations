package main

// Reverse Nodes in k-Group

import (
	"fmt"
)

type TestCase struct {
	input  *ListNode
	k      int
	output *ListNode
}

type ListNode struct {
	Val  int
	Next *ListNode
}

// Example 1
// Input: head = [1,2,3,4,5], k = 2
// Output: [2,1,4,3,5]
//
// Example 2
// Input: head = [1,2,3,4,5], k = 3
// Output: [3,2,1,4,5]
func main() {
	first := &TestCase{
		input:  makeListNodes([]int{1, 2, 3, 4, 5}),
		k:      2,
		output: makeListNodes([]int{2, 1, 4, 3, 5}),
	}
	fmt.Println(toString(reverseKGroup(first.input, first.k)), " should be: ", toString(first.output))
}

func reverseKGroup(head *ListNode, k int) *ListNode {
	dummy := &ListNode{0, head}
	groupPrev := dummy

	for {
		kth := getKth(groupPrev, k)
		if kth == nil {
			break
		}
		groupNext := kth.Next

		// reverse group
		prev, curr := kth.Next, groupPrev.Next
		for curr != groupNext {
			next := curr.Next
			curr.Next = prev
			prev = curr
			curr = next
		}

		next := groupPrev.Next
		groupPrev.Next = kth
		groupPrev = next
	}

	return dummy.Next
}

func getKth(currentNode *ListNode, k int) *ListNode {
	for currentNode != nil && k > 0 {
		currentNode = currentNode.Next
		k -= 1
	}
	return currentNode
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
