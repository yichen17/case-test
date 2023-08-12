package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;

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
        StringUtils.divisionLine();
        mergeTwoListsTest(dq);
        StringUtils.divisionLine("mergeTwoListsTest");
        swapPairsTest(dq);
        StringUtils.divisionLine();
        reverseStringTest(dq);
        StringUtils.divisionLine();
        maxAbsoluteSumTest(dq);
        StringUtils.divisionLine();
        subtractProductAndSumTest(dq);
        StringUtils.divisionLine();
        minFallingPathSumTest(dq);
        StringUtils.divisionLine();
        diagonalSumTest(dq);
        StringUtils.divisionLine();
        mergeKLists(dq);
    }

    // 23. 合并 K 个升序链表

    public static void mergeKLists(DailyQuestion dq){
        ListNode.printPath(dq.mergeKLists(ListNode.buildArray("[[1,4,5],[1,3,4],[2,6]]")));
        ListNode.printPath(dq.mergeKLists(ListNode.buildArray("[]")));
        ListNode.printPath(dq.mergeKLists(ListNode.buildArray("[[]]")));
        ListNode.printPath(dq.mergeKLists(ListNode.buildArray("[[],[1,2],[3,4,5]]")));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }
        if (lists.length == 1){
            return lists[0];
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        ListNode dummy = new ListNode(), p = dummy;
        for (ListNode node : lists){
            if (node != null){
                priorityQueue.offer(node);
            }
        }
        while (!priorityQueue.isEmpty()){
            ListNode node = priorityQueue.poll();
            p.next = node;
            p = p.next;
            if (node.next != null){
                priorityQueue.offer(node.next);
            }
        }
        return dummy.next;
    }

    // 1572. 矩阵对角线元素的和

    public static void diagonalSumTest(DailyQuestion dq){
//        String s = StringUtils.batchReplaceBracket("[[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1]]");
//        System.out.println(s);
        System.out.println(dq.diagonalSum(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        System.out.println(dq.diagonalSum(new int[][]{{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}}));
        System.out.println(dq.diagonalSum(new int[][]{}));
        System.out.println(dq.diagonalSum(new int[][]{{3}}));
    }

    public int diagonalSum(int[][] mat) {
        int len = mat.length;
        int result = 0;
        for (int i=0; i<len; i++){
            result += mat[i][i];
            if (len - i - 1 != i){
                result += mat[i][len-i-1];
            }
        }
        return result;
    }


    // 1289. 下降路径最小和 II

    public static void minFallingPathSumTest(DailyQuestion dq){
//        String s = StringUtils.batchReplaceBracket("[[1,2,3],[4,5,6],[7,8,9]]");
//        System.out.println(s);
        System.out.println(dq.minFallingPathSum(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        System.out.println(dq.minFallingPathSum(new int[][]{{-6}}));
    }

    public int minFallingPathSum(int[][] grid) {
        int len = grid.length;
        if (len == 0){
            return 0;
        }
        int[] dp = new int[len];
        for (int i=0; i<len; i++){
            dp[i] = grid[0][i];
        }
        for(int i=1; i<len; i++){
            int[] temp = new int[len];
            for (int j=0; j<len; j++){
                int minPos = this.getMinExclude(dp, j);
                temp[j] = grid[i][j] + dp[minPos];
            }
            dp = temp;
        }
        int min = Integer.MAX_VALUE;
        for(int i=0; i<len; i++){
            min = Math.min(min, dp[i]);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private int getMinExclude(int[] item, int excludePos){
        int pos = -1;
        for (int i=0; i<item.length; i++){
            if (i == excludePos) {
                continue;
            }
            if (pos == -1 || item[pos] > item[i]){
                pos = i;
            }
        }
        return pos;
    }

    // 1281. 整数的各位积和之差

    public static void subtractProductAndSumTest(DailyQuestion dq){
        System.out.println(dq.subtractProductAndSum(234));
        System.out.println(dq.subtractProductAndSum(4421));
        System.out.println(dq.subtractProductAndSum(1));
        System.out.println(dq.subtractProductAndSum(0));
        System.out.println(dq.subtractProductAndSum(99));
    }

    public int subtractProductAndSum(int n) {
        if (n == 0){
            return 0;
        }
        int multiply = 1, count = 0, temp;
        while (n > 0){
            temp = n%10;
            multiply *= temp;
            count += temp;
            n /= 10;
        }
        return multiply - count;
    }

    // 1749. 任意子数组和的绝对值的最大值

    public static void maxAbsoluteSumTest(DailyQuestion dq){
        System.out.println(dq.maxAbsoluteSum(new int[]{1,-3,2,3,-4}));
        System.out.println(dq.maxAbsoluteSum(new int[]{2,-5,1,-4,3,-2}));
        System.out.println(dq.maxAbsoluteSum(new int[]{-1}));
        System.out.println(dq.maxAbsoluteSum(new int[]{-3,-5,-3,-2,-6,3,10,-10,-8,-3,0,10,3,-5,8,7,-9,-9,5,-8}));
    }

    public int maxAbsoluteSum(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return Math.abs(nums[0]);
        }
        int min = nums[0], max = min, count = min;
//        int[] dp = new int[nums.length];
//        dp[0]=count;
        for (int i=1; i<nums.length; i++){
            count += nums[i];
//            dp[i]=count;
            min = Math.min(count, min);
            max = Math.max(count, max);
        }
        if (max < 0){
            return -min;
        }
        if (min > 0){
            return max;
        }
        return Math.abs(max - min);
    }

    // 344. 反转字符串

    public static void reverseStringTest(DailyQuestion dq){
        char[] s = "abcd".toCharArray();
        dq.reverseString(s);
        System.out.println(new String(s));
        s = "abcde".toCharArray();
        dq.reverseString(s);
        System.out.println(new String(s));
    }

    public void reverseString(char[] s) {
        int l = 0, r = s.length-1;
        char temp;
        while (l < r){
            temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }

    // 24. 两两交换链表中的节点

    public static void swapPairsTest(DailyQuestion dq){
        ListNode.printPath(dq.swapPairs(ListNode.buildListedList(new Integer[]{1,2,3,4})));
        ListNode.printPath(dq.swapPairs(ListNode.buildListedList(new Integer[]{})));
        ListNode.printPath(dq.swapPairs(ListNode.buildListedList(new Integer[]{1})));
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode n = head.next;
        ListNode nn = n.next;
        n.next = head;
        head.next = swapPairs(nn);
        return n;
    }

//    21. 合并两个有序链表   简单题还是容易的呀 一次性过

    public static void mergeTwoListsTest(DailyQuestion dq){
        ListNode p = ListNode.buildListedList(new Integer[]{1,2,4});
        ListNode q = ListNode.buildListedList(new Integer[]{1,3,4});
        System.out.println(ListNode.printPath(dq.mergeTwoLists(p, q)));
        p = ListNode.buildListedList(new Integer[]{});
        q = ListNode.buildListedList(new Integer[]{});
        System.out.println(ListNode.printPath(dq.mergeTwoLists(p, q)));
        p = ListNode.buildListedList(new Integer[]{});
        q = ListNode.buildListedList(new Integer[]{0});
        System.out.println(ListNode.printPath(dq.mergeTwoLists(p, q)));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(), p = dummy;
        while (list1 != null && list2 != null){
            if (list1.val > list2.val){
                p.next = list2;
                list2 = list2.next;
            }
            else {
                p.next = list1;
                list1 = list1.next;
            }
            p = p.next;
        }
        while (list1 != null){
            p.next = list1;
            p = p.next;
            list1 = list1.next;
        }
        while (list2 != null){
            p.next = list2;
            list2 = list2.next;
            p = p.next;
        }
        p.next = null;
        return dummy.next;
    }


    // 980. 不同路径 III    边界值没考虑清楚，执行也不是最优

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
