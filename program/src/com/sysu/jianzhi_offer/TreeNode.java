package com.sysu.jianzhi_offer;

import java.util.*;

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


    /**
     * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     */
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }

        public TreeLinkNode GetNext(TreeLinkNode pNode)
        {
            if (pNode == null) return null;
            if (pNode.right != null) {
                TreeLinkNode node = pNode.right;
                while (node.left != null) {
                    node = node.left;
                }
                return node;
            } else {
                while (pNode.next != null) {
                    if (pNode == pNode.next.left) return pNode.next;
                    pNode = pNode.next;
                }

            }

            return null;
        }
    }

    /**
     * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     * (注意: 在返回值的list中，数组长度大的数组靠前)
     */
    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> trace = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        DFSWithCounter(root, target, lists, trace);
        return lists;
    }

    public static void DFSWithCounter(TreeNode root, int sum, ArrayList<ArrayList<Integer>> lists, ArrayList<Integer> trace) {

        trace.add(root.val);
        if (root.left == null && root.right == null) {
            //是叶子结点
            if (root.val == sum) {
                lists.add(new ArrayList<>(trace));
            }
            trace.remove(trace.size()-1);
            return;
        }

        if (root.left != null) {
            DFSWithCounter(root.left, sum-root.val, lists, trace);
        }

        if (root.right != null) {
            DFSWithCounter(root.left, sum-root.val, lists, trace);
        }

        //用来回溯的
        trace.remove(trace.size()-1);


    }


    /**
     * DFS 非递归
     * @param root
     * @return
     */
    public ArrayList<Integer> DFS(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            //先右后左，出栈的时候才是先左后右
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
            list.add(node.val);
        }
        return list;
    }

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * @param pRootOfTree
     * @return
     *
     * 1.将左子树构造成双链表，并返回链表头节点。
     * 2.定位至左子树双链表最后一个节点。
     * 3.如果左子树链表不为空的话，将当前root追加到左子树链表。
     * 4.将右子树构造成双链表，并返回链表头节点。
     * 5.如果右子树链表不为空的话，将该链表追加到root节点之后。
     * 6.根据左子树链表是否为空确定返回的节点。
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return pRootOfTree;
        if (pRootOfTree.left == null && pRootOfTree.right == null) return pRootOfTree;
        TreeNode left = Convert(pRootOfTree.left);
        TreeNode leftTail = left;
        //找到左子树的最后一个结点，与pRootOfTree相连
        while (leftTail != null && leftTail.right != null) {
            leftTail = leftTail.right;
        }
        if (left != null) {
            pRootOfTree.left = leftTail;
            leftTail.right = pRootOfTree;
        }

        TreeNode right = Convert(pRootOfTree.right);
        if (right != null) {
            pRootOfTree.right = right;
            right.left = pRootOfTree;
        }

        if (left == null) return pRootOfTree;
        else return left;

    }


    /**
     * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     * @param  root [description]
     * @return      [description]
     */
    public static int TreeDepth(TreeNode root) {
        //假如是空节点，则返回0；
        if (root == null) return 0;

        /**
         * 否则，原树的深度由左右子树中深度较的深度加1，为原树的深度。
         */
        if (root.left == null && root.right == null) {
            return 1;
        }

        int leftDeep = TreeDepth(root.left);
        int rightDeep = TreeDepth(root.right);

        return leftDeep > rightDeep ? leftDeep + 1 : rightDeep + 1;

    }

    /**
     * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return  true;

        int left = getDeep(root.left, 0);
        int right = getDeep(root.right, 0);
        if (left - right > 1 || left - right < -1) {
            return false;
        }
        IsBalanced_Solution(root.left);
        IsBalanced_Solution(root.right);

        return true;

    }

    public int getDeep(TreeNode root, int deep) {
        if (root == null ) return deep;
        return (getDeep(root.left, deep+1) > getDeep(root.right, deep+1)) ?
                getDeep(root.left, deep+1) : getDeep(root.right, deep+1);
    }

    /**
     * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
     */
    boolean isSymmetrical(TreeNode pRoot) {
        return isSymmetricalHelp(pRoot, pRoot);
    }

    boolean isSymmetricalHelp(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;

        return isSymmetricalHelp(a.left, b.right) && isSymmetricalHelp(a.right, b.left);
    }


    /**
     * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
     */
    public ArrayList<ArrayList<Integer> > PrintZForm(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;

        //存基数层的，从左到右
        Stack<TreeNode> odd = new Stack<>();
        odd.push(pRoot);
        //存偶数层的，从右到左
        Stack<TreeNode> even = new Stack<>();

        int depth = 1;
        while (!odd.isEmpty() || !even.isEmpty()) {
            ArrayList<Integer> tmp = new ArrayList<>();
            if (!odd.isEmpty()) {
                while (!odd.isEmpty()) {
                    TreeNode node = odd.pop();
                    if (node.left != null) even.push(node.left);
                    if (node.right != null) even.push(node.right);
                    tmp.add(node.val);
                }
            } else {
                while (!even.isEmpty()) {
                    TreeNode node = even.pop();
                    if (node.right != null) odd.push(node.right);
                    if (node.left != null) odd.push(node.left);
                    tmp.add(node.val);
                }
            }

            res.add(tmp);

        }

        return res;
    }

    /**题目：把二叉树打印成多行
     * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
     * @param pRoot
     * @return
     */
    public static ArrayList<ArrayList<Integer> > PrintLineByLine(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > res = new ArrayList<>();

        if (pRoot == null) return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int count = 0;
            ArrayList<Integer> list = new ArrayList<>();
            while (count < size) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                count++;
            }
            res.add(list);
        }

        return res;
    }

    //中序遍历
    public static void reverseInOrder(TreeNode root) {
        if (root.left != null) reverseInOrder(root.left);
        System.out.println(root.val);
        if (root.right != null) reverseInOrder(root.right);
    }

    /**题目：二叉搜索树的第k个结点
     * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
     * @param pRoot
     * @param k
     * @return
     */
    public static TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k == 0) return null;

        TreeNode res = null;

        if (pRoot.left != null) res = KthNode(pRoot.left, k);

        if (res == null) {
            count++;
            if (k == count) {
                return pRoot;
            }
        }

        if (res == null && pRoot.right != null) res = KthNode(pRoot.right, k);

        return res;
    }

    private static int count = 0;


    /**题目：重建二叉树
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     * @param pre
     * @param in
     * @return
     */
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {

        return reBuild(pre, 0, pre.length-1, in, 0, in.length-1);

    }

    public static TreeNode reBuild(int [] pre, int prestart, int preend, int [] in, int instart, int inend) {
        if (prestart > preend || instart > inend) return null;

        TreeNode root = new TreeNode(pre[prestart]); // 前序的第一个一定是根节点
        //找到中序中的左边部分
        int in_left = instart;
        int index = 0;
        while (in[in_left] != pre[prestart]) {
            in_left++;
            index++;
        }

        root.left = reBuild(pre, prestart+1, prestart+index, in, instart, in_left-1);
        root.right = reBuild(pre, index+1+prestart, preend, in, in_left+1, inend);

        return root;

    }


    /**
     * 请实现两个函数，分别用来序列化和反序列化二叉树
     * @param root
     * @return
     */

    //以前序的顺序序列化，用“#”代替null空节点，“，”分割各个val
    static String Serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        serializeRecursion(root, sb);
        return sb.toString();

    }

    public static void serializeRecursion(TreeNode node, StringBuffer sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.val);
        sb.append(",");
        serializeRecursion(node.left, sb);
        serializeRecursion(node.right, sb);
        return;

    }

    public static TreeNode Deserialize(String str) {
        String[] tree = str.split(",");
        TreeNode root = deserializeRecursion(tree);
        return root;

    }

    static int index = 0;

    public static TreeNode deserializeRecursion(String[] tree) {
        if (tree[index].equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(tree[index++]));

        root.left = deserializeRecursion(tree);
        index++;
        root.right = deserializeRecursion(tree);


        return root;


    }

}
