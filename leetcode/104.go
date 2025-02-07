package main

// Maximum Depth of Binary Tree

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

type Stack struct {
	Node  *TreeNode
	Depth int
}

// Example 1
// Input: root = [3,9,20,null,null,15,7]
// Output: [3]
//
// Example 2
// Input: root = [1,null,2]
// Output: [2]
func dfs(root *TreeNode) int {
	if root == nil {
		return 0
	}

	stack := make([]*Stack, 0, 8)
	stack = append(stack, &Stack{root, 1})
	maxDepth := 0

	for len(stack) > 0 {
		node := stack[len(stack)-1]
		stack = stack[:len(stack)-1]

		if node.Node != nil {
			maxDepth = max(maxDepth, node.Depth)
			stack = append(stack, &Stack{node.Node.Left, node.Depth + 1})
			stack = append(stack, &Stack{node.Node.Right, node.Depth + 1})
		}
	}

	return maxDepth
}

func main() {
	fmt.Println(dfs(
		&TreeNode{
			3,
			&TreeNode{
				9,
				nil,
				nil,
				// &TreeNode{5, nil, nil},
				// &TreeNode{62, nil, nil},
			},
			&TreeNode{
				20,
				&TreeNode{15, nil, nil},
				&TreeNode{
					7,
					nil,
					nil,
					// &TreeNode{200, nil, nil},
				},
			},
		},
	))

	fmt.Println(dfs(
		&TreeNode{
			1,
			nil,
			&TreeNode{2, nil, nil}},
	))
}
