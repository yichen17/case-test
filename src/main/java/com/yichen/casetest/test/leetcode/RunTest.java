package com.yichen.casetest.test.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/18 22:35
 * @describe idea 运行测试
 */
@Slf4j
public class RunTest {

    public static void main(String[] args) {
        try {
            //        Set<Node> set = new HashSet<>();
//        log.info("{}", set.add(new Node(1, 1)));
//        log.info("{}", set.add(new Node(1, 1)));
//        log.info("{}", set.add(new Node(1, 1)));
        log.info("{}", kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
        log.info("{}", kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2));
        log.info("{}", kSmallestPairs(new int[]{1, 2}, new int[]{3}, 3));
            log.info("{}", kSmallestPairs(new int[]{1,2,4,5,6}, new int[]{3,5,7,9}, 20));
        }
        catch (Exception e){
            log.error("执行异常 {}", e.getMessage(), e);
        }

    }


    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return nums1[o1.a] + nums2[o1.b] - nums1[o2.a] - nums2[o2.b];
//                return nums1[o2.a] + nums2[o2.b] - nums1[o1.a] - nums2[o2.b];
            }
        });
        Set<Node> set = new HashSet<>();
        Node item = new Node(0, 0);
        queue.offer(item);
        set.add(item);
        List<List<Integer>> result = new ArrayList<>();

        while (k>0 && !queue.isEmpty()){
            k--;
            Node node = queue.poll();
            set.remove(node);
            result.add(Arrays.asList(nums1[node.a], nums2[node.b]));
            if (node.a < nums1.length - 1){
                item = new Node(node.a+1, node.b);
                if (set.add(item)){
                    queue.offer(item);
                }
            }
            if (node.b < nums2.length - 1){
                item = new Node(node.a, node.b+1);
                if (set.add(item)){
                    queue.offer(item);
                }
            }
        }
        return result;
    }

    static class Node{
        public int a;
        public int b;
        Node(int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode() {
            return (a << 15) & b;
        }

        @Override
        public boolean equals(Object obj) {
            Node n = (Node) obj;
            return this.a == n.a && this.b == n.b;
        }
    }

}
