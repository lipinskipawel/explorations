package com.github.lipinskipawel.leetcode.tree;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

class MaximumDepthBinaryTreeTest implements WithAssertions {

    private final MaximumDepthBinaryTree maximumDepthBinaryTree = new MaximumDepthBinaryTree();

    private static Stream<Arguments> trees() {
        return Stream.of(
            of(new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                    new TreeNode(15),
                    new TreeNode(7))), 3),
            of(new TreeNode(1, null, new TreeNode(2)), 2)
        );
    }

    @ParameterizedTest
    @MethodSource("trees")
    void maxDepth(TreeNode tree, int expected) {
        final var result = maximumDepthBinaryTree.maxDepth(tree);

        assertThat(result).isEqualTo(expected);
    }
}
