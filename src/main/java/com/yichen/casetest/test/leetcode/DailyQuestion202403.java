package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/3/1 08:17
 * @describe
 */
public class DailyQuestion202403 {

    public static void main(String[] args) {
        DailyQuestion202403 dq = new DailyQuestion202403();
        validPartitionTest(dq);
        StringUtils.divisionLine();
    }

    // 2369. 检查数组是否存在有效划分

    private static void validPartitionTest(DailyQuestion202403 dq) {
        // false
        System.out.println(dq.validPartition(new int[]{993335, 993336, 993337, 993338, 993339, 993340, 993341}));
        // true
        System.out.println(dq.validPartition(new int[]{4, 4, 4, 5, 6}));
        // false
        System.out.println(dq.validPartition(new int[]{1, 1, 1, 2}));
    }

    public boolean validPartition(int[] nums) {
        int len = nums.length;
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 0; i <= len; i++) {
            if (i >= 2) {
                dp[i] = dp[i - 2] && nums[i - 2] == nums[i - 1];
            }
            if (i >= 3) {
                dp[i] = dp[i] || dp[i - 3] && (nums[i - 1] - nums[i - 2] == 1 && nums[i - 2] - nums[i - 3] == 1 || nums[i - 3] == nums[i - 1] && nums[i - 3] == nums[i - 2]);
            }
        }
        return dp[len];
    }


}
