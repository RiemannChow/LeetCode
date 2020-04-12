package com.leetcode.code;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *    返回其层次遍历结果：
 *  [
 *   [3],
 *   [9,20],
 *   [15,7]
 *  ]
 */


import com.leetcode.source.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        if(root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int toBePrint = 1;//这一层要打印的节点
        int nextLevelCount = 0;//下一层需要打印节点
        while (queue.isEmpty() == false)//队列不为空
        {
            TreeNode temp = queue.poll();//出队
            tempList.add(temp.val);//把需要的val保存下来
            toBePrint--;//每出队一个，这层要打印的节点数就减少一个
            if (temp.left != null)
            {
                queue.add(temp.left);//入队，先入先出，所以左子树先打印
                nextLevelCount++;//统计下一层节点
            }
            if (temp.right != null)
            {
                queue.add(temp.right);//和上面类似
                nextLevelCount++;
            }
            if (toBePrint == 0)//当这一层节点打印完了
            {
                list.add(new ArrayList<>(tempList));//把这一行的结果保存
                tempList.clear();
                toBePrint = nextLevelCount;//下一层打印的节点，进行赋值
                nextLevelCount = 0;//下下层节点初始值置位0，准备开始计数
            }
        }
        return list;
    }
}
