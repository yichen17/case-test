package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

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

    int[][] dir = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static void main(String[] args) {
        DailyQuestion dq = new DailyQuestion();
        flipGameTest(dq);
        StringUtils.divisionLine();
        removeCommentsTest(dq);
        StringUtils.divisionLine();
        uniquePathIIITest(dq);
    }

    // 980. 不同路径 III

    public static void uniquePathIIITest(DailyQuestion dq){
        System.out.println(dq.uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}}));
        System.out.println(dq.uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,0,2}}));
        System.out.println(dq.uniquePathsIII(new int[][]{{1,2}}));
        System.out.println(dq.uniquePathsIII(new int[][]{{1,-1,2}}));
    }

    int uniquePathResult;
    int totalAvail;
    public int uniquePathsIII(int[][] grid) {
        int  row = grid.length, column = grid[0].length;
        totalAvail = row * column;
        int[] start = null, end = null;
        for (int i=0; i<row; i++){
            for(int j=0; j<column; j++){
                if (grid[i][j] == 1){
                    start = new int[]{i, j};
                }
                else if (grid[i][j] == 2){
                    end = new int[]{i, j};
                }
                else if (grid[i][j] == -1){
                    totalAvail--;
                }
            }
        }
        uniquePathResult = 0;
        Set<Integer> visit = new HashSet<>();
        visit.add(this.visitPos(start[0], start[1]));
        dfs(grid, start, end, visit);
        return uniquePathResult;
    }

    public void dfs(int[][] grid, int[] start, int[] end, Set<Integer> visit){
        int  row = grid.length, column = grid[0].length;
        if (start[0] == end[0] && start[1] == end[1]){
            if (totalAvail == visit.size()){
                uniquePathResult++;
            }
            return;
        }
        for(int[] item : dir){
            int x = start[0] + item[0];
            int y = start[1] + item[1];
            if (x >=0 && x < row && y >=0 && y < column
                    && grid[x][y] != -1 && visit.add(this.visitPos(x, y))){
                dfs(grid, new int[]{x, y}, end, visit);
                visit.remove(this.visitPos(x, y));
            }
        }
    }

    public int visitPos(int i, int j){
        return i | (j << 10);
    }

//    722. 删除注释

    public static void removeCommentsTest(DailyQuestion dq){
        StringUtils.rowPrintList(dq.removeComments(new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"}));
        StringUtils.rowPrintList(dq.removeComments(new String[]{"a/*comment", "line", "more_comment*/b"}));
        StringUtils.rowPrintList(dq.removeComments(new String[]{""}));
        StringUtils.rowPrintList(dq.removeComments(new String[]{"/**/"}));
        StringUtils.rowPrintList(dq.removeComments(new String[]{"struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"}));
        StringUtils.rowPrintList(dq.removeComments(new String[]{"void func(int k) {", "// this function does nothing /*", "   k = k*2/4;", "   k = k/2;*/", "}"}));
    }

    public List<String> removeComments(String[] source) {
        List<String> result = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        for (String item : source){
            for (int i=0; i<item.length(); i++){
                // 块注释结束
                if (flag){
                    if (i+1 < item.length() && item.charAt(i) == '*' && item.charAt(i+1) == '/'){
                        i++;
                        flag = false;
                    }
                    continue;
                }

                // 非注释场景
                if (item.charAt(i) != '/'){
                    builder.append(item.charAt(i));
                    continue;
                }
                // 可能触发注释
                if (i+1 < item.length()){
                    if (item.charAt(i+1) == '/'){
                        break;
                    }
                    if (item.charAt(i+1) == '*'){
                        i++;
                        flag = true;
                        continue;
                    }
                }
                builder.append(item.charAt(i));
            }
            if (!flag && builder.length() != 0){
                result.add(builder.toString());
                builder = new StringBuilder();
            }
        }
        if (builder.length() != 0){
            result.add(builder.toString());
        }
        return result;
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
