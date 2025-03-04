package com.github.lipinskipawel.leetcode.tree;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

class BalancedBinaryTreeTest implements WithAssertions {

    private final BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();

    private static Stream<Arguments> trees() {
        return Stream.of(
            of(new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                    new TreeNode(15),
                    new TreeNode(7))), true),
            of(new TreeNode(1,
                new TreeNode(2,
                    new TreeNode(3,
                        new TreeNode(4),
                        new TreeNode(4)),
                    new TreeNode(3)),
                new TreeNode(2)), false)
        );
    }

    @ParameterizedTest
    @MethodSource("trees")
    void isBalanced(TreeNode tree, boolean expected) {
        final var result = balancedBinaryTree.isBalanced(tree);

        assertThat(result).isEqualTo(expected);
    }
}
