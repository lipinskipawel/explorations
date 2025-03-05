package com.github.lipinskipawel.leetcode.tree;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

class InvertBinaryTreeTest implements WithAssertions {

    private final InvertBinaryTree invertBinaryTree = new InvertBinaryTree();

    private static Stream<Arguments> trees() {
        return Stream.of(
            of(new TreeNode(4,
                new TreeNode(2,
                    new TreeNode(1),
                    new TreeNode(3)),
                new TreeNode(7,
                    new TreeNode(6),
                    new TreeNode(9))), new TreeNode(4,
                new TreeNode(7,
                    new TreeNode(9),
                    new TreeNode(6)),
                new TreeNode(2,
                    new TreeNode(3),
                    new TreeNode(1)))),
            of(new TreeNode(2,
                new TreeNode(1),
                new TreeNode(3)), new TreeNode(2,
                new TreeNode(3),
                new TreeNode(1)))
        );
    }

    @ParameterizedTest
    @MethodSource("trees")
    void inverted(TreeNode tree, TreeNode expected) {
        final var result = invertBinaryTree.invertTree(tree);

        assertThat(result).isEqualTo(expected);
    }
}
