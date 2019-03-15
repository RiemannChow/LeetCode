package com.leetcode.code;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *    3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */

import com.leetcode.source.TreeNode;

import java.util.*;

public class LeetCode103{

    public List<List<Integer>> zigzagLevelOrder(TreeNode root){
        List<List<Integer>> lists = new LinkedList<List<Integer>>();
        if (root == null){
            return lists;
        }
        Queue<TreeNode> currentLevel = new LinkedList<>();
        currentLevel.add(root);
        int count = 0;
        while (!(currentLevel.isEmpty())){
            int size = currentLevel.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++){
                TreeNode currentNode = new TreeNode(0);
                currentNode = currentLevel.poll();
                list.add(currentNode.val);
                if (currentNode.left != null){
                    currentLevel.add(currentNode.left);
                }
                if (currentNode.right != null){
                    currentLevel.add(currentNode.right);
                }
            }
            if (count % 2 == 1){
                Collections.reverse(list);
            }
            ++count;
            lists.add(list);
        }
        return lists;
    }
}
