package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/8/2 20:35
 * @describe 每日一题，希望自己坚持的下去
 */
@Slf4j
public class DailyQuestion {

    Random random = new Random();
    public void swap(int[] fronts, int a, int b){
        int temp = fronts[a];
        fronts[a] = fronts[b];
        fronts[b] = temp;
    }

    public static void main(String[] args) {
        DailyQuestion dq = new DailyQuestion();
        flipGameTest(dq);
        StringUtils.divisionLine();
    }

    // 822. 翻转卡片游戏    我是垃圾，告辞


    public static void flipGameTest(DailyQuestion dq){
        System.out.println(dq.flipgame(new int[]{1,2,4,4,7}, new int[]{1,3,4,1,3}));
        System.out.println(dq.flipgame(new int[]{1}, new int[]{1}));
        System.out.println(dq.flipgame(new int[]{1,1,4,5}, new int[]{1,3,3,4}));
        System.out.println(dq.flipgame(new int[]{1,3,4,5}, new int[]{1,1,3,4}));
        System.out.println(dq.flipgame(new int[]{1}, new int[]{2}));
        System.out.println(dq.flipgame(new int[]{2,1,1,2,2}, new int[]{2,2,1,2,1}));
    }

    public int flipgame(int[] fronts, int[] backs) {
        this.bigTop(fronts, backs);
        sort(fronts, backs);
        Set<Integer> set = new HashSet<>();
        int i;
        for(i=0; i<fronts.length; i++){
            if (fronts[i]  == backs[i]){
                set.add(fronts[i]);
                continue;
            }
            if (set.add(backs[i]))return backs[i];
            break;
        }
        if (i == fronts.length)return 0;
        int min =  fronts[i], b = backs[i];
        boolean first = false;
        for(i = i+1; i<fronts.length; i++){
            if (backs[i] != b){
                first = true;
            }
            if (!first && backs[i] == fronts[i]){
                continue;
            }
            if (backs[i] == b && !first){
                min = Math.min(min, fronts[i]);
            }

            if (first && backs[i] != fronts[i]){
                min = Math.min(min, backs[i]);
                break;
            }
        }
        return min;
    }

    public void bigTop(int[] fronts, int[] backs){
        int temp;
        for(int i=0; i<fronts.length; i++){
            if (fronts[i] < backs[i]){
                temp = fronts[i];
                fronts[i] = backs[i];
                backs[i] = temp;
            }
        }
    }

    public void sort(int[] fronts, int[] backs){
        quickSort(fronts, backs, 0, fronts.length-1);
        bubbleSort(fronts, backs);
    }

    public void bubbleSort(int[] fronts, int[] backs){
        int len = fronts.length;
        if (len < 2)return;
        boolean swapItem = false;
        while (!swapItem){
            swapItem = true;
            for(int i=0; i<len-1; i++){
                if (backs[i] > backs[i+1]){
                    this.twoArraySwap(fronts, backs, i, i+1);
                }
            }
        }
    }


    public void quickSort(int[] fronts, int[] backs, int l, int r){
        if (l >= r){
            return;
        }
        int pos = random.nextInt(r-l) + l;
        this.twoArraySwap(fronts, backs, l, pos);
        int i = l, j = r, val = fronts[pos];
        while(i < j){
            while (i < j && fronts[j] > val){
                j--;
            }
            this.twoArraySwap(fronts, backs, i, j);
            while (i < j && fronts[i] <= val){
                i++;
            }
            this.twoArraySwap(fronts, backs, i, j);
        }
        quickSort(fronts, backs, l, pos-1);
        quickSort(fronts, backs, pos+1, r);
    }

    public void twoArraySwap(int[] fronts, int[] backs, int a,int b){
        if (a == b)return;
        this.swap(fronts, a, b);
        this.swap(backs, a, b);
    }



}
