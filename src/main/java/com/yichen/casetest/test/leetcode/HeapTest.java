package com.yichen.casetest.test.leetcode;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/12 21:37
 * @describe 堆相关测试
 */
@Slf4j
public class HeapTest {

    public static void main(String[] args) {
        HeapTest heapTest = new HeapTest();
        heapTest.kSmallestPairs(new int[]{1,2,4,5,6}, new int[]{3,5,7,9}, 3);

    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Integer[]> queue = new PriorityQueue<Integer[]>(new Comparator<Integer[]>(){
            public int compare(Integer[] a, Integer[] b){
                return a[0] + a[1] - b[0] -b[1];
            }
        });
        for(int i=0; i<nums1.length; i++){
            for(int j=0; j<nums2.length; j++){
                queue.add(new Integer[]{nums1[i], nums2[j]});
            }
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Iterator<Integer[]> iter = queue.iterator();
        while(iter.hasNext() && k > 0){
            k--;
            Integer[] items = iter.next();
            List<Integer> list = new ArrayList<>(4);
            for(Integer item : items){
                list.add(item);
            }
            result.add(list);
        }
        return result;
    }



}
