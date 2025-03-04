package com.github.lipinskipawel.leetcode.tree;

// 110
// https://leetcode.com/problems/balanced-binary-tree/
final class BalancedBinaryTree {
    private record Tuple(boolean isBalanced, int height) {
    }

    public boolean isBalanced(TreeNode root) {
        return checkBalanced(root).isBalanced();
    }

    private Tuple checkBalanced(TreeNode root) {
        if (root == null) {
            return new Tuple(true, 0);
        }

        final var left = checkBalanced(root.left);
        final var right = checkBalanced(root.right);

        final var diff = Math.abs(left.height() - right.height());

        if (left.isBalanced() && right.isBalanced()) {
            if (diff <= 1) {
                return new Tuple(true, Math.max(left.height(), right.height()) + 1);
            }
        }

        return new Tuple(false, Math.max(left.height(), right.height()) + 1);
    }
}
