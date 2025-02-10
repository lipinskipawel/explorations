package main

// Balanced Binary Tree

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
// Input: root = [3,9,20,null,null,15,7]
// Output: true
//
// Example 2
// Input: root = [1,2,2,3,3,null,null,4,4]
// Output: false
//
// Example 3
// Input: root = []
// Output: true
func main() {
	first := TreeNode{
		3,
		&TreeNode{9, nil, nil},
		&TreeNode{
			20,
			&TreeNode{15, nil, nil},
			&TreeNode{7, nil, nil},
		},
	}
	second := TreeNode{
		1,
		&TreeNode{
			2,
			&TreeNode{
				3,
				&TreeNode{4, nil, nil},
				&TreeNode{4, nil, nil},
			},
			&TreeNode{3, nil, nil}},
		&TreeNode{
			2,
			nil,
			nil,
		},
	}
	fmt.Printf("Is this tree, %#v a balanced: %t\n", first, isBalanced(&first))
	fmt.Printf("Is this tree, %#v a balanced: %t\n", second, isBalanced(&second))
}

func isBalanced(root *TreeNode) bool {
	b, _ := checkBalanced(root)
	return b
}

func checkBalanced(root *TreeNode) (bool, int) {
	if root == nil {
		return true, 0
	}

	left, lh := checkBalanced(root.Left)
	right, rh := checkBalanced(root.Right)

	diff := lh - rh
	if diff < 0 {
		diff = -diff
	}
	balanced := left && right && (diff <= 1)

	return balanced, 1 + max(lh, rh)
}
