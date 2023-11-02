package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;

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
