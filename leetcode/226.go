package main

// Invert Binary Tree

import (
	"fmt"
)

// Definition for a binary tree node.
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// Example 1
// Input: root = [4,2,7,1,3,6,9]
// Output: [4,7,2,9,6,3,1]
//
// Example 2
// Input: root = [2,1,3]
// Output: [2,3,1]
//
// Example 3
// Input: root = []
// Output: []
func main() {
	fmt.Println("--------------------------------")
	first := node(
		4,
		node(2,
			node(1, nil, nil),
			node(3, nil, nil),
		),
		node(7,
			node(6, nil, nil),
			node(9, nil, nil),
		),
	)
	fmt.Println("Normal: ", flatten(first))
	fmt.Println("Inverted: ", flatten(invertTree(first)))
	fmt.Println("--------------------------------")
	second := node(
		2,
		node(1, nil, nil),
		node(3, nil, nil),
	)
	fmt.Println("Normal: ", flatten(second))
	fmt.Println("Inverted: ", flatten(invertTree(second)))
	fmt.Println("--------------------------------")
	fmt.Println("Normal: ", flatten(nil))
	fmt.Println("Inverted: ", flatten(invertTree(nil)))
	fmt.Println("--------------------------------")
}

func invertTree(root *TreeNode) *TreeNode {
	root = invertTreeReq(root)
	return root
}

func invertTreeReq(root *TreeNode) *TreeNode {
	if root == nil {
		return nil
	}
	if root.Left == nil && root.Right == nil {
		return root
	}

	tempLeft := root.Left
	root.Left = root.Right
	root.Right = tempLeft

	root.Left = invertTreeReq(root.Left)
	root.Right = invertTreeReq(root.Right)
	return root
}

func node(val int, left *TreeNode, right *TreeNode) *TreeNode {
	return &TreeNode{
		Val:   val,
		Left:  left,
		Right: right,
	}
}

func flatten(root *TreeNode) []int {
	if root == nil {
		return []int{}
	}
	queue := make([]*TreeNode, 0, 8)
	queue = append(queue, root)

	res := make([]int, 0, 8)

	for len(queue) > 0 {
		element := queue[0]
		queue = queue[1:]

		res = append(res, element.Val)
		if element.Left != nil {
			queue = append(queue, element.Left)
		}
		if element.Right != nil {
			queue = append(queue, element.Right)
		}
	}

	return res
}
