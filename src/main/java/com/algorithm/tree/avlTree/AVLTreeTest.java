package com.algorithm.tree.avlTree;

public class AVLTreeTest {

    private static int[] arr = {20, 19, 11, 7, 19, 93, 3, 28, 90, 2, 21};

    public static void main(String[] args) {
        int k;
        AVLTree<Integer> tree = new AVLTree<>();
        System.out.printf("=== 依次添加: ");

        for (int val : arr) {
            System.out.printf(val + " ");
            tree.insert(val);
        }

        System.out.printf("\n=== 前序遍历: ");
        tree.preOrder();

        System.out.printf("\n=== 中序遍历: ");
        tree.inOrder();

        System.out.printf("\n=== 后序遍历: ");
        tree.postOrder();

        System.out.printf("\n=== 高度: " + tree.height());
        System.out.printf("\n=== 最小值: " + tree.minNode());
        System.out.printf("\n=== 最大值: " + tree.maxNode());
        System.out.printf("\n=== 树的详细信息: ");
        tree.print();

        k = 7;
        System.out.printf("\n=== 删除节点: " + k);
        tree.remove(k);
        System.out.printf("\n=== 高度: " + tree.height());

        System.out.printf("\n=== 中序遍历: ");
        tree.inOrder();

        System.out.printf("\n=== 树的详细信息: ");
        tree.print();

        // 销毁二叉树
        tree.destroy();
    }

}
