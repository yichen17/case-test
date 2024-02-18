package com.yichen.casetest.test.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/2/18 07:16
 * @describe
 */
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }


    /**
     * 层序遍历构造n叉树，层之间以及子树和子树之间null分隔，示例：[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * 数据参考   https://leetcode.cn/problems/n-ary-tree-preorder-traversal/description/?envType=daily-question&envId=2024-02-18
     * @param data
     * @return
     */
    public static Node buildNode(Integer[] data){
        if (data == null || data.length == 0){
            return null;
        }
        int len, i=0;
        Queue<Node> queue = new LinkedList<>();
        // 首个肯定不为null
        Node root = new Node(data[i], new LinkedList<>());
        queue.add(root);
        loop:
        while (!queue.isEmpty() && i < data.length) {
            len = queue.size();
            // 层序遍历，间隔为null
            i += 2;
            while (len > 0){
                len --;
                Node parent = queue.poll();
                while (i < data.length && data[i] != null){
                    Node c = new Node(data[i++], new LinkedList<>());
                    queue.offer(c);
                    parent.children.add(c);
                }
                if (i >= data.length){
                    break loop;
                }
                // 跳过间隔的空节点
                if (len > 0){
                    i++;
                }
            }
            i--;
        }
        return root;
    }




    public static void main(String[] args) {
        Node node = buildNode(new Integer[]{1, null, 2, 3, 4, 5, null, null, 6, 7, null, 8, null, 9, 10, null, null, 11, null, 12, null, 13, null, null, 14});
        System.out.println("6666");
    }

}
