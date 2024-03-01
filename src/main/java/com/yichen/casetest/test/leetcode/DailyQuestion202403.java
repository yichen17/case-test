package com.yichen.casetest.test.leetcode;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/3/1 08:17
 * @describe
 */
public class DailyQuestion202403 {

    public static void main(String[] args) {
        DailyQuestion202403 dq = new DailyQuestion202403();

    }

    // 2369. 检查数组是否存在有效划分

    private static void validPartitionTest(DailyQuestion202403 dq){
        // true
        System.out.println(dq.validPartition(new int[]{4,4,4,5,6}));
        // false
        System.out.println(dq.validPartition(new int[]{1,1,1,2}));
    }

    public boolean validPartition(int[] nums) {

    }

}
