package com.github.lipinskipawel.leetcode.tree;

import java.util.ArrayDeque;

// 226
// https://leetcode.com/problems/invert-binary-tree/description/
final class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        final var queue = new ArrayDeque<TreeNode>();
        queue.push(root);

        while (!queue.isEmpty()) {
            final var pop = queue.pop();

            final var tmp = pop.left;
            pop.left = pop.right;
            pop.right = tmp;

            if (pop.left != null) {
                queue.push(pop.left);
            }
            if (pop.right != null) {
                queue.push(pop.right);
            }
        }

        return root;
    }
}
