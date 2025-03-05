package com.github.lipinskipawel.leetcode.tree;

import java.util.Stack;

// 104
// https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
final class MaximumDepthBinaryTree {
    private record Tuple(TreeNode node, int depth) {
    }

    public int maxDepth(TreeNode root) {
//        return checkDepthRecursive(root);
        return checkDepthIterative(root);
    }

    private int checkDepthRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        final var left = checkDepthRecursive(root.left);
        final var right = checkDepthRecursive(root.right);

        return Math.max(left, right) + 1;
    }

    private int checkDepthIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }

        final var stack = new Stack<Tuple>();
        stack.push(new Tuple(root, 1));
        var maxDepth = 1;

        while (!stack.empty()) {
            final var pop = stack.pop();

            if (pop.node() != null) {
                maxDepth = Math.max(maxDepth, pop.depth());
                stack.push(new Tuple(pop.node().right, pop.depth() + 1));
                stack.push(new Tuple(pop.node().left, pop.depth() + 1));
            }
        }

        return maxDepth;
    }
}
