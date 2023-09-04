package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.constants.CommonConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/3 20:31
 * @describe 树相关题目
 */
@Slf4j
@Data
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    TreeNode left(int val){
        this.left = new TreeNode(val);
        return this;
    }

    TreeNode right(int val){
        this.right = new TreeNode(val);
        return this;
    }

    private static final String NULL = "null";

    /**
     * 构造树
     * @param nodes
     * @return
     */
    public static TreeNode buildTree(Integer[] nodes){
        if (nodes.length == 0 || (nodes.length == 1 && Objects.isNull(nodes[0]))){
            return null;
        }
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int pos = 1;
        while(!queue.isEmpty() && pos < nodes.length){
            TreeNode node = queue.poll();
            if (Objects.nonNull(nodes[pos])){
                node.left = new TreeNode(nodes[pos]);
                queue.offer(node.left);
            }
            pos++;
            if (pos == nodes.length){
                break;
            }
            if (Objects.nonNull(nodes[pos])){
                node.right = new TreeNode(nodes[pos]);
                queue.offer(node.right);
            }
            pos++;
        }
        return root;
    }


    public static void printTree(TreeNode root){
        if (root == null){
            log.info("[]");
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Queue<TreeNode> queue = new LinkedList<>();
        boolean allEmpty = false;
        int size;
        queue.offer(root);
        while (!queue.isEmpty() && !allEmpty){
            allEmpty = true;
            size = queue.size();
            while (size > 0){
                size--;
                TreeNode node = queue.poll();
                if (node == null){
                    builder.append(NULL).append(CommonConstants.COMMA);
                    queue.offer(null);
                    queue.offer(null);
                }
                else {
                    builder.append(node.getVal()).append(CommonConstants.COMMA);
                    queue.offer(node.left);
                    queue.offer(node.right);
                    if (node.left != null || node.right != null){
                        allEmpty = false;
                    }
                }
            }
        }
        builder.replace(builder.length()-1, builder.length(), "]");
        log.info("{}", builder);
    }

    public static void main(String[] args) {
        Integer[] nodes = {4,1,6,0,2,5,7,null,null,null,3,null,null,null,8};
        TreeNode root = buildTree(nodes);



    }


    /**
     * 获得最左元素 如果是二叉搜索树即最小元素
     * @param root
     * @return
     */
    public static TreeNode getLeftest(TreeNode root){
        if (root == null){
            return null;
        }
        TreeNode item = root, result = item;
        while(true){
            if (item.left != null){
                item = item.left;
                result = item;
            }
            else if (item.right != null){
                item = item.right;
            }
            else {
                return result;
            }
        }
    }

    /**
     * 校验两棵树是否一样
     * @param p
     * @param q
     * @return
     */
    public static boolean sameCheck(TreeNode p, TreeNode q){
        if (Objects.isNull(p) && Objects.isNull(q)){
            return true;
        }
        if (Objects.isNull(p) || Objects.isNull(q) || p.getVal() != q.getVal()){
            return false;
        }
        return sameCheck(p.getLeft(), q.getLeft()) && sameCheck(p.getRight(), q.getRight());
    }


    /**
     * 根据字符串值构造数据
     * @param s
     * @return
     */
    public static TreeNode constructNode(String s){
        if (NULL.equals(s)){
            return null;
        }
        return new TreeNode(Integer.parseInt(s));
    }

}
