package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/1/1 15:07
 * @describe
 */
public class DailyQuestion202401 {

    public static void main(String[] args) {
        DailyQuestion202401 dq = new DailyQuestion202401();
        minOperationsMaxProfitTest(dq);
        StringUtils.divisionLine();
    }


    // 1599. 经营摩天轮的最大利润

    private static void minOperationsMaxProfitTest(DailyQuestion202401 dq){
        System.out.println(dq.minOperationsMaxProfit(new int[]{0,43,37,9,23,35,18,7,45,3,8,24,1,6,37,2,38,15,1,14,39,27,4,25,27,33,43,8,44,30,38,40,20,5,17,27,43,11,6,2,30,49,30,25,32,3,18,23,45,43,30,14,41,17,42,42,44,38,18,26,32,48,37,5,37,21,2,9,48,48,40,45,25,30,49,41,4,48,40,29,23,17,7,5,44,23,43,9,35,26,44,3,26,16,31,11,9,4,28,49,43,39,9,39,37,7,6,7,16,1,30,2,4,43,23,16,39,5,30,23,39,29,31,26,35,15,5,11,45,44,45,43,4,24,40,7,36,10,10,18,6,20,13,11,20,3,32,49,34,41,13,11,3,13,0,13,44,48,43,23,12,23,2}, 43, 54));
        System.out.println(dq.minOperationsMaxProfit(new int[]{8,3}, 5, 6));
        System.out.println(dq.minOperationsMaxProfit(new int[]{10,9,6}, 6, 4));
        System.out.println(dq.minOperationsMaxProfit(new int[]{3,4,0,5,1}, 1, 92));
        System.out.println(dq.minOperationsMaxProfit(StringUtils.randomIntArray(10000, 0, 50), 4, 9));
    }

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        if (boardingCost * 4 <= runningCost){
            return -1;
        }
        int preRetain = 0, runCost, times=0, sum=0, resultTimes=0, result=0;
        for (int i=0; i<customers.length; i++){
            preRetain += customers[i];
            runCost = Math.min(preRetain, 4);
            times ++;
            sum += runCost * boardingCost - runningCost;
            preRetain -= runCost;
            if (sum > result){
                resultTimes = times;
                result = sum;
            }
        }
        if (preRetain > 4){
            times += preRetain / 4;
            sum += preRetain / 4 * 4 * boardingCost - runningCost;
            preRetain %= 4;
        }
        if (sum == 0 && preRetain * boardingCost - runningCost <= 0){
            return -1;
        }
        if (preRetain * boardingCost - runningCost > 0){
            times++;
            sum++;
        }
        if (sum > result){
            resultTimes = times;
        }
        return resultTimes;
    }


}
