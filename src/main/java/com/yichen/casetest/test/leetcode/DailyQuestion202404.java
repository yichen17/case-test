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

    // 1379. 找出克隆二叉树中的相同节点


    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == target){
            return cloned;
        }
        TreeNode result;
        if (original.left != null && (result = getTargetCopy(original.left, cloned.left, target)) != null){
            return result;
        }
        if (original.right != null && (result = getTargetCopy(original.right, cloned.right, target)) != null){
            return result;
        }
        return null;
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
