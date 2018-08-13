package com.sysu.jianzhi_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

    /**
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {

        boolean result = false;
        if (root1 == null || root2 == null) return false;

        result = ifHasSubTree(root1, root2); //从根结点开始比较

        if (!result) result = ifHasSubTree(root1.left, root2); //再用左子树进行比较

        if (!result) result = ifHasSubTree(root1.right, root2); //最后用右子树进行比较

        return result;
    }

    public boolean ifHasSubTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) {
            return false; //此时root2是不为null的
        }
        if (root1.val == root2.val) {
            //继续比较
            return ifHasSubTree(root1.left, root2.left) && ifHasSubTree(root1.right, root2.right);
        } else {
            return false;
        }

    }

    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * @描述 二叉树的镜像定义：源二叉树
     *     	    8
     *     	   /  \
     *     	  6   10
     *     	 / \  / \
     *     	5  7 9 11
     *     	镜像二叉树
     *     	    8
     *     	   /  \
     *     	  10   6
     *     	 / \  / \
     *     	11 9 7  5
     */
    public void Mirror(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;

        Mirror(root.left);
        Mirror(root.right);

    }

    /**
     * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) continue;
            res.add(node.val);
            queue.add(node.left);
            queue.add(node.right);
        }

        return res;
    }


}
