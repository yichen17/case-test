package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;

import java.util.*;

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
        removeNodesTest(dq);
        StringUtils.divisionLine();
        insertGreatestCommonDivisorsTest(dq);
        StringUtils.divisionLine();
        minExtraCharTest(dq);
        StringUtils.divisionLine();
        minLengthTest(dq);
        StringUtils.divisionLine();
        numberOfBoomerangsTest(dq);
        StringUtils.divisionLine();
        addMinimumTest(dq);
        StringUtils.divisionLine();
        countWordsTest(dq);
        StringUtils.divisionLine();
    }

    // 2085. 统计出现过一次的公共字符串

    private static void countWordsTest(DailyQuestion202401 dq){
        System.out.println(dq.countWords(new String[]{"leetcode","is","amazing","as","is"}, new String[]{"amazing","leetcode","is"}));
        System.out.println(dq.countWords(new String[]{"b","bb","bbb"}, new String[]{"a","aa","aaa"}));
        System.out.println(dq.countWords(new String[]{"a","ab"}, new String[]{"a","a","a","ab"}));
    }

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> pMap = new HashMap<>();
        for (String word : words1){
            pMap.put(word, pMap.getOrDefault(word, 0) + 1);
        }
        Map<String, Integer> qMap = new HashMap<>();
        for (String word : words2){
            qMap.put(word, qMap.getOrDefault(word, 0) + 1);
        }
        int result = 0;
        Integer val;
        for (Map.Entry<String, Integer> item : pMap.entrySet()){
            if (item.getValue() == 1 && (val = qMap.get(item.getKey())) != null && val == 1){
                result++;
            }
        }
        return result;
    }



    // 2645. 构造有效字符串的最少插入数
    // 某个点往后扫，只存在两种情况：1、有序状态，继续往后扫。2、乱序，重复场景，此时只能人为补充节点   ==>  我是傻逼，  ac bc的咋处理？？？
    // 根据有序性处理

    private static void addMinimumTest(DailyQuestion202401 dq){
        System.out.println(dq.addMinimum("b"));
        System.out.println(dq.addMinimum("aaa"));
        System.out.println(dq.addMinimum("abc"));
        System.out.println(dq.addMinimum(StringUtils.randomArrayInSpecificCharacters(new char[]{'a', 'b', 'c'},  50)));
    }



    public int addMinimum(String word) {
        int result = 0, i=0;
        Stack<Character> stack = new Stack<>();
        while (true){
            while (i<word.length() && (stack.isEmpty() || stack.peek() < word.charAt(i))){
                stack.push(word.charAt(i));
                i++;
            }
            result += 3 - stack.size();
            stack.clear();
            if (i == word.length()){
                break;
            }
        }
        return result;
    }


    // 447. 回旋镖的数量

    private static void numberOfBoomerangsTest(DailyQuestion202401 dq){
        System.out.println(dq.numberOfBoomerangs(StringUtils.convert2Array("[[0,0],[1,0],[2,0]]")));
        System.out.println(dq.numberOfBoomerangs(StringUtils.convert2Array("[[1,1],[2,2],[3,3]]")));
        System.out.println(dq.numberOfBoomerangs(StringUtils.convert2Array("[[1,1]]")));
        System.out.println(dq.numberOfBoomerangs(StringUtils.constructTwoDimensionArray(400, 2, -300, 300)));
    }

    public int numberOfBoomerangs(int[][] points) {
        int result = 0;
        for (int[] point : points){
            Map<Integer, Integer> distinceMap = new HashMap<>();
            for (int i=0; i<points.length; i++){
                int distince = (points[i][0] - point[0]) * (points[i][0] - point[0]) + (points[i][1] - point[1]) * (points[i][1] - point[1]);
                distinceMap.put(distince, distinceMap.getOrDefault(distince, 0) + 1);
            }
            for (Integer times : distinceMap.values()) {
                if (times >= 2){
                    result += (times-1) * times;
                }
            }
        }
        return result;
    }

    // 2696. 删除子串后的字符串最小长度

    private static void minLengthTest(DailyQuestion202401 dq){
        System.out.println(dq.minLength("ABFCACDB"));
        System.out.println(dq.minLength("ACBBD"));
    }

    public int minLength(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++){
            // 可以组合的移除
            if (!stack.empty() && ((stack.peek() == 'A' && s.charAt(i) == 'B') || (stack.peek() == 'C' && s.charAt(i) == 'D'))){
                stack.pop();
                continue;
            }
            // 不能组合的加入
            stack.push(s.charAt(i));
        }
        return stack.size();
    }

    // 2707. 字符串中的额外字符

    private static void minExtraCharTest(DailyQuestion202401 dq){
        System.out.println(dq.minExtraChar("leetscode", new String[]{"leet","code","leetcode"}));
        System.out.println(dq.minExtraChar("sayhelloworld", new String[]{"hello","world"}));
        System.out.println(dq.minExtraChar("hello", new String[]{"hel","ello"}));
    }

    public int minExtraChar(String s, String[] dictionary) {
        Set<String> set = new HashSet<>();
        int maxLen = 0;
        for (String item : dictionary){
            set.add(item);
            maxLen = Math.max(maxLen, item.length());
        }
        int[] dp = new int[s.length()+1];
        for (int i=0; i<dp.length; i++){
            dp[i] = i+1;
        }
        for (int i=0; i<s.length(); i++){
            this.calMinExtraChar(set, dp, i, s, maxLen);
        }
        return dp[s.length()-1];
    }

    private void calMinExtraChar(Set<String> set, int[] dp, int pos, String s, int maxLen){
        for (int i=pos; i<s.length() && i<pos+maxLen; i++){
            if (set.contains(s.substring(pos, i+1))){
                int pre = pos == 0 ? 0 : dp[pos-1];
                dp[i] = Math.min(dp[i], pre);
            }
        }
        if (pos != 0){
            dp[pos] = Math.min(dp[pos], dp[pos-1]+1);
        }
    }




    // 2807. 在链表中插入最大公约数

    private static void insertGreatestCommonDivisorsTest(DailyQuestion202401 dq){
        ListNode.printPath(dq.insertGreatestCommonDivisors(ListNode.buildListedList(new Integer[]{18,6,10,3})));
        ListNode.printPath(dq.insertGreatestCommonDivisors(ListNode.buildListedList(new Integer[]{7})));
        ListNode.printPath(dq.insertGreatestCommonDivisors(ListNode.buildListedList(StringUtils.randomIntArrayWrapper(2000, 1, 999))));
    }

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode pre = null, pos = head;
        while (pos != null){
            if (pre == null){
                pre = pos;
                pos = pos.next;
                continue;
            }
            ListNode midNode = new ListNode(this.getGreatestCommonDivisor(pre.val, pos.val));
            midNode.next = pre.next;
            pre.next = midNode;
            pre = pos;
            pos = pos.next;
        }
        return head;
    }

    private int getGreatestCommonDivisor(int a, int b){
        if (a < b){
            int tmp = a;
            a = b;
            b = tmp;
        }
        while (a % b != 0){
            int remainder = a%b;
            a=b;
            b=remainder;
        }
        return b;
    }

    // 383. 赎金信

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] f = new int[26];
        for (int i=0; i<magazine.length(); i++){
            f[magazine.charAt(i) - 'a']++;
        }
        for (int i=0; i<ransomNote.length(); i++){
            f[ransomNote.charAt(i)-'a']--;
            if (f[ransomNote.charAt(i) - 'a'] <0){
                return false;
            }
        }
        return true;
    }

    // 2487. 从链表中移除节点

    private static void removeNodesTest(DailyQuestion202401 dq){
        ListNode.printPath(dq.removeNodes(ListNode.buildListedList("2,3")));
        ListNode.printPath(dq.removeNodes(ListNode.buildListedList("5,2,13,3,8")));
        ListNode.printPath(dq.removeNodes(ListNode.buildListedList("1,1,1,1")));
        ListNode.printPath(dq.removeNodes(ListNode.buildListedList(StringUtils.randomIntArrayWrapper(10000, 1, 99999))));
    }

    public ListNode removeNodes(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head, next, tail = null;
        while (p != null){
            next = p.next;
            while (!stack.isEmpty() && stack.peek().val < p.val){
                stack.pop();
            }
            stack.push(p);
            p = next;
        }
        while (!stack.isEmpty()){
            if (tail == null){
                tail = stack.pop();
                tail.next = null;
                continue;
            }
            head = stack.pop();
            head.next = tail;
            tail = head;
        }
        return tail;
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
