package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/18 12:28
 * @describe
 */
@Slf4j
public class BinarySearch {

    public static void main(String[] args) {
        log.info("find insert pos => {}", findInsertPos(new int[]{1, 3, 5, 6}, 7));
        StringUtils.divisionLine();
        log.info("single non duplicate => {}", singleNonDuplicate(new int[]{3,3,7,7,10,11,11}));
        Math.random();
        Random random = new Random();
        random.nextInt(3);
    }


    static class WeightSearch{
        private int total;
        private int[] range;

        /**
         * 初始化
         * @param w 权重数组，例如为{1,3},则表示0出现概率25%，1出现概率75%
         */
        public WeightSearch(int[] w) {
            total = 0;
            range = new int[w.length];
            for(int i=0; i<w.length; i++){
                total += w[i];
                range[i] = total;
            }
        }

        /**
         * 随机出现概率值
         * @return
         */
        public int pickIndex() {
            int target = (int)(Math.random() * total);
            int left = 0, right = range.length-1, mid;
            while(left <= right){
                mid = (left+right) >> 1;
                if (range[mid] > target){
                    if (mid == 0 || range[mid - 1] <= target){
                        return mid;
                    }
                    right = mid - 1;
                }
                else{
                    left = mid + 1;
                }
            }
            return -1;
        }
    }


    /**
     * 找到不是重复的数字，nums为排序后的数组
     * @param nums
     * @return
     */
    public static int singleNonDuplicate(int[] nums) {
        int left = 0, right = (nums.length -1) >> 1, mid;
        while(left <= right){
            mid = (left + right) >> 1;
            // 在末尾
            if (mid*2 + 1 == nums.length){
                return nums[nums.length-1];
            }
            else if (nums[mid*2] == nums[mid*2+1]){
                left = mid + 1;
            }
            else if (mid > 0 && nums[mid*2] == nums[mid*2-1]){
                right = mid - 1;
            }
            else {
                return nums[mid*2];
            }
        }
        return -1;
    }


    /**
     * 找到可以插入的位置，nums为升序数组，不重复
     * @param nums
     * @param target
     * @return
     */
    private static int findInsertPos(int[] nums, int target){
        int left = 0, right = nums.length - 1, mid;
        while(left <= right){
            mid = (left + right)  >> 1;
            if (nums[mid] >= target ){
                if (mid < 1 || nums[mid - 1] < target){
                    return mid;
                }
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        // 最小值插入可以满足，最大值的话不能满足
        return nums.length;
    }

    /**
     * 找到第一个大于等于{@code target}的坐标
     * @param nums 递增数组，可重复
     * @param target 比对值
     * @return
     */
    private static int getFirstGreaterEqual(int[] nums, int target){
        return -1;
    }

    /**
     * 找到最后一个小于等于{@code target}的坐标
     * @param nums 递增数组，可重复
     * @param target 比对值
     * @return
     */
    private static int getLastSmallerEqual(int[] nums, int target){
        return -1;
    }

    /**
     * 找到对应类型下符合条件的坐标
     * @param nums 递增数组
     * @param target 目标值
     * @param type 类型 true表示第一个大于等于，false表示最后一个小于等于
     * @return
     */
    private static int getPos(int[] nums, int target, boolean type){
        for (int i=0; i<nums.length; i++){
            if (type && nums[i] >= target){
                return i;
            }
            else if (!type && nums[i] > target){
                return i-1;
            }
        }
        return nums.length;
    }


}
