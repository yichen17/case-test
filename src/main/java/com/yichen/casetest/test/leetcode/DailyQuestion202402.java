package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;

import java.util.*;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/2/1 08:27
 * @describe
 */
class DailyQuestion202402 {

    public static void main(String[] args) {
        DailyQuestion202402 dq = new DailyQuestion202402();
        stoneGameVITest(dq);
        StringUtils.divisionLine();
        canWinNimTest(dq);
        StringUtils.divisionLine();
        maxResultTest(dq);
        StringUtils.divisionLine();
        magicTowerTest(dq);
        StringUtils.divisionLine();
        replaceValueInTreeTest(dq);
        StringUtils.divisionLine();
        isCousinsTest(dq);
        StringUtils.divisionLine();
        inorderTraversalTest(dq);
        StringUtils.divisionLine();
        postorderTest(dq);
        StringUtils.divisionLine();
        buildTreeTest(dq);
        StringUtils.divisionLine();
    }

    // 1686. 石子游戏 VI

    private static void stoneGameVITest(DailyQuestion202402 dq){
        //  1
        System.out.println(dq.stoneGameVI(new int[]{1,3}, new int[]{2,1}));
        //  0
        System.out.println(dq.stoneGameVI(new int[]{1,2}, new int[]{3,1}));
        //  -1
        System.out.println(dq.stoneGameVI(new int[]{2,4,3}, new int[]{1,6,7}));
    }

    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        return 0;
    }

    // 105. 从前序与中序遍历序列构造二叉树

    private static void buildTreeTest(DailyQuestion202402 dq){
        // [3,9,20,null,null,15,7]
        TreeNode.printTree(dq.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));
        // [-1]
        TreeNode.printTree(dq.buildTree(new int[]{-1}, new int[]{-1}));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    private TreeNode buildTree(int[] preOrder, int pl, int pr, int[] inOrder, int il, int ir){
        if (pl > pr || il >ir){
            return null;
        }
        int i;
        for (i=il; i<=ir; i++){
            if (inOrder[i] == preOrder[pl]){
                break;
            }
        }
        TreeNode root = new TreeNode(preOrder[pl]);
        root.left = this.buildTree(preOrder, pl+1, pl+i-il, inOrder, il, i-1);
        root.right = this.buildTree(preOrder, pl+i-il+1, pr, inOrder, i+1, ir);
        return root;
    }

    // 590. N 叉树的后序遍历

    private static void  postorderTest(DailyQuestion202402 dq){
        // [5,6,3,2,4,1]
        StringUtils.rowPrintList(dq.postorder(Node.buildNode(new Integer[]{1,null,3,2,4,null,5,6})));
        // [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
        StringUtils.rowPrintList(dq.postorder(Node.buildNode(new Integer[]{1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14})));
    }

    public List<Integer> postorder(Node root) {
        List<Integer> result = new LinkedList<>();
        postSearch(root, result);
        return result;
    }

    private void postSearch(Node root, List<Integer> result){
        if (root == null){
            return;
        }
        for (Node c : root.children){
            postSearch(c, result);
        }
        result.add(root.val);
    }

    // 589. N 叉树的前序遍历

    public List<Integer> preorder(Node root) {
        List<Integer> result = new LinkedList<>();
        preSearch(root, result);
        return result;
    }

    private void preSearch(Node root, List<Integer> result){
        if (root == null){
            return;
        }
        result.add(root.val);
        if (root.children == null || root.children.isEmpty()){
            return;
        }
        for (Node c : root.children){
            preSearch(c, result);
        }
    }

    // 144. 二叉树的前序遍历

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        preSearch(root, result);
        return result;
    }

    private void preSearch(TreeNode root, List<Integer> result){
        if (root == null){
            return;
        }
        result.add(root.val);
        preSearch(root.left, result);
        preSearch(root.right, result);
    }

    // 236. 二叉树的最近公共祖先


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> pPath = new LinkedList<>(), qPath = new LinkedList<>();
        dfs(root, p, pPath);
        dfs(root, q, qPath);
        Iterator<TreeNode> pi = pPath.iterator();
        Iterator<TreeNode> qi = qPath.iterator();
        TreeNode pre = null;
        while (pi.hasNext() && qi.hasNext()){
            TreeNode a = pi.next();
            TreeNode b = qi.next();
            if (a != b) {
                return pre;
            }
            pre = a;
        }
        return pre;
    }

    private boolean dfs(TreeNode root, TreeNode target, LinkedList<TreeNode> path){
        if (root == null){
            return false;
        }
        path.add(root);
        if (root == target){
            return true;
        }
        if (dfs(root.left, target, path) || dfs(root.right, target, path)){
            return true;
        }
        path.removeLast();
        return false;
    }


    // 94. 二叉树的中序遍历

    private static void inorderTraversalTest(DailyQuestion202402 dq){
        // [1,3,2]
        System.out.println(dq.inorderTraversal(TreeNode.buildTree(new Integer[]{1,null,2,3})));
        // []
        System.out.println(dq.inorderTraversal(TreeNode.buildTree(new Integer[]{})));
        // [1]
        System.out.println(dq.inorderTraversal(TreeNode.buildTree(new Integer[]{1})));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        midSearch(root, result);
        return result;
    }


    private void midSearch(TreeNode root, List<Integer> path){
        if (root == null){
            return ;
        }
        midSearch(root.left, path);
        path.add(root.val);
        midSearch(root.right, path);
    }

    // 993. 二叉树的堂兄弟节点

    private static void isCousinsTest(DailyQuestion202402 dq){
        // false
        System.out.println(dq.isCousins(TreeNode.buildTree(new Integer[]{1,2,3,4}), 4, 3));
        // true
        System.out.println(dq.isCousins(TreeNode.buildTree(new Integer[]{1,2,3,null,4,null,5}), 5, 4));
        // false
        System.out.println(dq.isCousins(TreeNode.buildTree(new Integer[]{1,2,3,null,4}), 2, 3));
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int childMatchTimes, allMatchTimes;
        while (!queue.isEmpty()){
            int len = queue.size();
            allMatchTimes = 0;
            // 确保在同一层
            while (len > 0){
                len--;
                childMatchTimes = 0;
                TreeNode item = queue.poll();
                // 过滤是否为统一个父节点
                if (item.left != null){
                    queue.add(item.left);
                    if (item.left.val == x || item.left.val == y){
                        childMatchTimes++;
                    }
                }
                if (item.right != null){
                    queue.add(item.right);
                    if (item.right.val == x || item.right.val == y){
                        childMatchTimes++;
                    }
                }
                if (childMatchTimes == 2){
                    return false;
                }
                allMatchTimes += childMatchTimes;
            }
            if (allMatchTimes == 2){
                return true;
            }
        }
        return false;
    }

    // 2641. 二叉树的堂兄弟节点 II

    private static void replaceValueInTreeTest(DailyQuestion202402 dq){
        // [0,0,0,7,7,null,11]
        TreeNode.printTree(dq.replaceValueInTree(TreeNode.buildTree(new Integer[]{5,4,9,1,10,null,7})));
        // [0,0,0]
        TreeNode.printTree(dq.replaceValueInTree(TreeNode.buildTree(new Integer[]{3,1,2})));
        // [0,0,0,7,7,null,11,12,null,11,null,17,17]
        TreeNode.printTree(dq.replaceValueInTree(TreeNode.buildTree(new Integer[]{5,4,9,1,10,null,7,8,null,9,null,1,2})));
    }

    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int total = 0, len;
        root.val = 0;
        while (!queue.isEmpty()){
            total = 0;
            len = queue.size();
            for (TreeNode treeNode : queue) {
                if (treeNode.left != null){
                    total += treeNode.left.val;
                }
                if (treeNode.right != null){
                    total += treeNode.right.val;
                }
            }
            while (len > 0){
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null){

                }
                else if (node.left == null){
                    node.right.val = total - node.right.val;
                    queue.offer(node.right);
                }
                else if (node.right == null){
                    node.left.val = total - node.left.val;
                    queue.offer(node.left);
                }
                else {
                    node.left.val = node.right.val = total - node.right.val - node.left.val;
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
                len --;
            }
        }
        return root;
    }

    // LCP 30. 魔塔游戏

    private static void magicTowerTest(DailyQuestion202402 dq){
        System.out.println(dq.magicTower(new int[]{100,100,100,-250,-60,-140,-50,-50,100,150}));
        System.out.println(dq.magicTower(new int[]{-200,-300,400,0}));
        System.out.println(dq.magicTower(StringUtils.randomIntArray(9999, -9999, 9999)));
    }

    /**
     * [-7,1,-3,-4]应该移除前面的 -7
     * @param nums
     * @return
     */
    public int magicTower(int[] nums) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        int len = nums.length, result = 0;
        long sum = 1L, total = 1L;
        for (int i=0; i<len; i++){
            total += nums[i];
            // 小于等于0的挨个加
            if (nums[i] <= 0){
                priorityQueue.add(nums[i]);
                sum += nums[i];
                continue;
            }
            // 大于0的，做校准
            while (sum <= 0){
                sum -= priorityQueue.remove();
                result++;
            }
            sum += nums[i];
        }
        if (total <= 0){
            return -1;
        }
        return result;
    }

    // 1696. 跳跃游戏 VI

    private static void maxResultTest(DailyQuestion202402 dq){
        // 198
        System.out.println(dq.maxResult(new int[]{100,-1,-100,-1,100}, 2));
        // 7
        System.out.println(dq.maxResult(new int[]{1,-1,-2,4,-7,3}, 2));
        // 17
        System.out.println(dq.maxResult(new int[]{10,-5,-2,4,0,3}, 3));
        // 0
        System.out.println(dq.maxResult(new int[]{1,-5,-20,4,-1,3,-6,-3}, 2));
        System.out.println(dq.maxResult(StringUtils.randomIntArray(1000, -9999, 9999), 200));
    }

    public int maxResult(int[] nums, int k) {
        int len = nums.length;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] dp = new int[len];
        for (int i=0; i<len; i++){
            // 维持有效长度
            if (!stack.isEmpty() && i - stack.peekFirst() > k){
                stack.removeFirst();
            }
            dp[i] =  nums[i] + (stack.isEmpty() ? 0 : dp[stack.peekFirst()]);
            // 维持递减
            while (!stack.isEmpty() && dp[i] > dp[stack.peekLast()]){
                stack.removeLast();
            }
            stack.addLast(i);
        }
        return dp[len-1];
    }

    // 292. Nim 游戏

    private static void canWinNimTest(DailyQuestion202402 dq){
        System.out.println(dq.canWinNim(4));
        System.out.println(dq.canWinNim(2));
        System.out.println(dq.canWinNim(1));
    }

    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

}
