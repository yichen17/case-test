package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;

import java.util.*;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/11/2 07:59
 * @describe
 */
public class DailyQuestion202311 {

    public static void main(String[] args) {
        DailyQuestion202311 dq = new DailyQuestion202311();
        countPointsTest(dq);
        StringUtils.divisionLine();
        findMaximumXORTest(dq);
        StringUtils.divisionLine();
        findRepeatedDnaSequencesTest(dq);
        StringUtils.divisionLine();
    }

    // 187. 重复的DNA序列

    private static void findRepeatedDnaSequencesTest(DailyQuestion202311 dq){
        StringUtils.rowPrintList(dq.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        StringUtils.rowPrintList(dq.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
        StringUtils.rowPrintList(dq.findRepeatedDnaSequences("AAAAAAAAAAA"));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> stringCases = new HashSet<>();
        Set<String> result = new HashSet<>();
        for (int i=10; i<=s.length(); i++){
            String item = s.substring(i-10, i);
            if (result.contains(item)){
                continue;
            }
            if (!stringCases.add(item)){
                stringCases.remove(item);
                result.add(item);
            }
        }
        return new LinkedList<>(result);
    }

    // 数组中两个数的最大异或值

    private static void findMaximumXORTest(DailyQuestion202311 dq){
        System.out.printf("%s%n%s%n%s%n%s", Integer.toBinaryString(670116204), Integer.toBinaryString(693454773),
                Integer.toBinaryString(234881024), Integer.toBinaryString(245657305));
        System.out.println(dq.findMaximumXOR(new int[]{670116204,693454773}));
        System.out.println(dq.findMaximumXOR(new int[]{3,10,5,25,2,8}));
        System.out.println(dq.findMaximumXOR(new int[]{14,70,53,83,49,91,36,80,92,51,66,70}));
        System.out.println(dq.findMaximumXOR(StringUtils.randomIntArray(3000, 0, 1_000_000_000)));
    }


    public int findMaximumXOR(int[] nums) {
        DictNode root = new DictNode();
        int result = 0;
        this.create(nums[0], root);
        for (int i=1; i<nums.length; i++){
            result = Math.max(result, this.getMaxXor(nums[i], root));
            this.create(nums[i], root);
        }
        return result;
    }

    private int getMaxXor(int compare, DictNode root){
        int i = 1 << 30;
        int result = 0;
        while (i > 0){
            if ((compare & i) > 0){
                if (root.right != null){
                    result = result * 2 + 1;
                    root = root.right;
                }
                else {
                    root = root.left;
                    result = result * 2;
                }
            }
            else {
                if (root.left != null){
                    result = result * 2 + 1;
                    root = root.left;
                }
                else {
                    root = root.right;
                    result = result * 2;
                }
            }
            i = i >> 1;
        }
        return result;
    }

    private void create(int n, DictNode root){
        int i = 1 << 30;
        while (i > 0){
            if ((n & i) > 0){
                if (root.left == null){
                    root.left = new DictNode();
                }
                root = root.left;
            }
            else {
                if (root.right == null){
                    root.right = new DictNode();
                }
                root = root.right;
            }
            i = i >> 1;
        }
    }

    private static class DictNode {
        public DictNode left, right;
    }



    // 117. 填充每个节点的下一个右侧节点指针 II

    public Node connect(Node root) {
        if (root == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int len = queue.size();
            Node pre = null, next;
            while (len > 0){
                next = queue.poll();
                if (next.left != null){
                    queue.offer(next.left);
                }
                if (next.right != null){
                    queue.offer(next.right);
                }
                if (pre != null){
                    pre.next = next;
                }
                pre = next;
                len--;
            }
        }
        return root;
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


    // 2103. 环和杆
    private static void countPointsTest(DailyQuestion202311 dq){
        System.out.println(dq.countPoints("B0B6G0R6R0R6G9"));
        System.out.println(dq.countPoints("B0R0G0R9R0B0G0"));
        System.out.println(dq.countPoints("G4"));
    }

    public int countPoints(String rings) {
        int result = 0;
        int[] poles = new int[10];
        for(int i=0; i<rings.length(); i+=2){
            poles[rings.charAt(i+1) - '0'] |= this.getColor(rings.charAt(i));
        }
        for(int pole : poles){
            if (pole == 7){
                result++;
            }
        }
        return result;
    }

    private int getColor(char s){
        if (s == 'R'){
            return 1;
        }
        else if (s == 'B'){
            return 2;
        }
        return 4;
    }
}
