package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/4/1 08:21
 * @describe
 */
public class DailyQuestion202404 {

    public static void main(String[] args) {
        DailyQuestion202404 dq = new DailyQuestion202404();
        finalStringTest(dq);
        StringUtils.divisionLine();
    }

    // 2810. 故障键盘

    private static void finalStringTest(DailyQuestion202404 dq){
        // rtsng
        System.out.println(dq.finalString("string"));
        // ponter
        System.out.println(dq.finalString("poiinter"));
    }

    public String finalString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++){
            if (s.charAt(i) == 'i'){
                sb.reverse();
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }


}
