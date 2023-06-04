package com.yichen.casetest.test.leetcode;

import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/3 20:31
 * @describe 树相关题目
 */
@Slf4j
public class TreeCase {

    public static class TreeNode {
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

    }



    /**
     * 构造树
     * @param nodes
     * @return
     */
    public static TreeNode buildTree(Integer[] nodes){
        if (nodes.length == 0 || (nodes.length == 1 && Objects.nonNull(nodes[0]))){
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
            if (pos == nodes.length)break;
            if (Objects.nonNull(nodes[pos])){
                node.right = new TreeNode(nodes[pos]);
                queue.offer(node.right);
            }
            pos++;
        }
        return root;
    }


    public static void printTree(TreeNode root){

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

    public static void main(String[] args) {
        Integer[] nodes = {4,1,6,0,2,5,7,null,null,null,3,null,null,null,8};
        TreeNode root = buildTree(nodes);

        TreeCase treeCase = new TreeCase();


    }










}
