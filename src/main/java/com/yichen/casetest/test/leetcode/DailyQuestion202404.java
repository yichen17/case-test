package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        minOperationsTest(dq);
        StringUtils.divisionLine();
        maximumBinaryStringTest(dq);
        StringUtils.divisionLine();
    }

    // 1702. 修改后的最大二进制字符串

    private static void maximumBinaryStringTest(DailyQuestion202404 dq){
        System.out.println(dq.maximumBinaryString("1100"));
        // 111011
        System.out.println(dq.maximumBinaryString("000110"));
        // 01
        System.out.println(dq.maximumBinaryString("01"));

        System.out.println(dq.maximumBinaryString(StringUtils.randomArrayInSpecificCharacters(new char[]{'1', '0'}, 50000)));
    }

    /**
     * 从左往右，有0才去借
     * @param binary
     * @return
     */
    public String maximumBinaryString(String binary) {
        char[] charArray = binary.toCharArray();
        int prev = -1;
        for (int i=0; i<charArray.length; i++) {
            if (charArray[i] == '1') {
                continue;
            }
            if (prev == -1){
                prev = i;
                continue;
            }
            // 上一个也是0
            if (prev == i-1) {
                charArray[prev] = '1';
                charArray[i] = '0';
                prev = i;
                continue;
            }
            // 不是连续的0，中间有gap
            charArray[prev] = '1';
            charArray[prev+1] = '0';
            charArray[i] = '1';
            prev = prev + 1;
        }

        return new String(charArray);
    }

    // 2529. 正整数和负整数的最大计数

    public int maximumCount(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums){
            if (num > 0){
                a++;
            } else if (num < 0) {
                b++;
            }
        }
        return Math.max(a, b);
    }

    // 2009. 使数组连续的最少操作数

    private static void minOperationsTest(DailyQuestion202404 dq){
        //
        System.out.println(dq.minOperations(new int[]{44,28,33,49,4,2,35,28,25,38,47,20,14,30,27,38,42,14,34}));
        //
        System.out.println(dq.minOperations(new int[]{1,1,1,1}));
        // 0
        System.out.println(dq.minOperations(new int[]{4,2,5,3}));
        // 1
        System.out.println(dq.minOperations(new int[]{1,2,3,5,6}));
        // 3
        System.out.println(dq.minOperations(new int[]{1,10,100,1000}));
        System.out.println(dq.minOperations(StringUtils.randomIntArray(5000, 1, 1_000_000_000)));
    }

    public int minOperations(int[] nums) {
        Arrays.sort(nums);
        List<Integer> notRepeatList = new LinkedList<>();
        for (int i=0; i<nums.length; i++){
            if (i == 0 || nums[i] != nums[i-1]){
                notRepeatList.add(nums[i]);
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i=0; i<notRepeatList.size(); i++){
            result = Math.min(result, nums.length - (getFirstGreaterPos(notRepeatList.get(i) + nums.length - 1, notRepeatList) - i + 1) + 1);
            // 提前跳出
            if (result <= nums.length - (notRepeatList.size()) - i){
                break;
            }
        }
        return result;
    }

    private int getFirstGreaterPos(int n, List<Integer> nums){
        int left = 0, right = nums.size() - 1, mid;
        while (left <= right){
            mid = (left + right) >> 1;
            if (nums.get(mid) <= n){
                left = mid + 1;
                continue;
            }
            if (mid == 0 || nums.get(mid-1) <= n){
                return mid;
            }
            right = mid - 1;
        }
        return left;
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
