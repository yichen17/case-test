package com.yichen.casetest.test.leetcode;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/8/2 20:35
 * @describe 每日一题，希望自己坚持的下去
 */
@Slf4j
public class DailyQuestion {

    private static final Random random = new Random();
    private static final int MOD = 1000000007;
    public void swap(int[] fronts, int a, int b){
        int temp = fronts[a];
        fronts[a] = fronts[b];
        fronts[b] = temp;
    }

    int[][] dir = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};

    private static void setErrorLogLevel(){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<ch.qos.logback.classic.Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> {
            logger.setLevel(Level.INFO);
        });
    }

    public static void main(String[] args) {

        setErrorLogLevel();

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
        StringUtils.divisionLine();
        mergeTest(dq);
        StringUtils.divisionLine();
        mergeTreesTest(dq);
        StringUtils.divisionLine();
        findReplaceString(dq);
        StringUtils.divisionLine();
        circularGameLosersTest(dq);
        StringUtils.divisionLine();
//        waysTest(dq);
        StringUtils.divisionLine("waysTest");
        maxSizeSlicesTest(dq);
        StringUtils.divisionLine("maxSizeSlices");
        checkTreeTest(dq);
        StringUtils.divisionLine();
        canChangeTest(dq);
        StringUtils.divisionLine();
        maxDistToClosestTest(dq);
        StringUtils.divisionLine();
        countPairsTest(dq);
        StringUtils.divisionLine();
        countServersTest(dq);
    }

    // 1267. 统计参与通信的服务器

    private static void countServersTest(DailyQuestion dq){
        System.out.println(dq.countServers(StringUtils.convert2Array("[[1,0],[0,1]]")));
        System.out.println(dq.countServers(StringUtils.convert2Array("[[1,0],[1,1]]")));
        System.out.println(dq.countServers(StringUtils.convert2Array("[[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]")));
    }

    public int countServers(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int result = 0;
        int[] rowCount = new int[row];
        int[] colCount = new int[col];
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (grid[i][j] == 1){
                    rowCount[i]++;
                    colCount[j]++;
                    result++;
                }
            }
        }
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (grid[i][j] == 1 && (rowCount[i] == 1 && colCount[j] == 1)){
                    result--;
                }
            }
        }
        return result;
    }




    // 1782. 统计点对的数目
    // 我是废物  第一次边界值问题  第二次超出时间显示  第三次超出空间限制  第四次自闭。。。

    public static void countPairsTest(DailyQuestion dq){
        StringUtils.printIntArray(dq.countPairs(4, new int[][]{{1,2}, {1,3}, {2,3}, {2,4}, {2,1}}, new int[]{2, 3}));
        StringUtils.printIntArray(dq.countPairsOptimize(4, new int[][]{{1,2}, {1,3}, {2,3}, {2,4}, {2,1}}, new int[]{2, 3}));
        StringUtils.printIntArray(dq.countPairs(5,
                StringUtils.convert2Array("[[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]]"), new int[]{1,2,3,4,5}));
        StringUtils.printIntArray(dq.countPairsOptimize(5,
                StringUtils.convert2Array("[[1,5],[1,5],[3,4],[2,5],[1,3],[5,1],[2,3],[2,5]]"), new int[]{1,2,3,4,5}));
        StringUtils.printIntArray(dq.countPairs(6,
                StringUtils.convert2Array("[[5,2],[3,5],[4,5],[1,5],[1,4],[3,5],[2,6],[6,4],[5,6],[4,6],[6,2],[2,6],[5,4],[6,1],[6,1],[2,5],[1,3],[1,3],[4,5]]"),
                new int[]{6,9,2,1,2,3,6,6,6,5,9,7,0,4,5,9,1,18,8,9}));
        StringUtils.printIntArray(dq.countPairsOptimize(6,
                StringUtils.convert2Array("[[5,2],[3,5],[4,5],[1,5],[1,4],[3,5],[2,6],[6,4],[5,6],[4,6],[6,2],[2,6],[5,4],[6,1],[6,1],[2,5],[1,3],[1,3],[4,5]]"),
                new int[]{6,9,2,1,2,3,6,6,6,5,9,7,0,4,5,9,1,18,8,9}));
//        countPairsTestCompare(dq);
    }


    /**
     * 比较结果，countPairs 明显慢，原因是因为queries太小，对整体数据做处理的优势不大，如果queries长度为万级别范围，而不是20，那应该会展露出优势
     *     这里可以体现出对最初需求的理解，根据不同的场景使用不同的实现，方案的选择是基于具体的业务场景的。
     * @param dq
     */
    private static void countPairsTestCompare(DailyQuestion dq){
        long aCost=0, bCost=0, aCostTotal=0, bCostTotal=0, lessA=0, lessB=0, same=0;
        int times = 200;
        for (int i=0; i<times; i++){
            int n = random.nextInt(2*10000-1) + 2;
            int[][] edges = StringUtils.constructEdges(1, n, random.nextInt(100000)+1, false);
            int[] queries = StringUtils.randomIntArray(random.nextInt(2000) + 1, 0, edges.length-1);
            System.gc();
            aCost = System.currentTimeMillis();
            int[] resultA = dq.countPairs(n, edges, queries);
            aCost = System.currentTimeMillis() - aCost;
            System.gc();
            bCost = System.currentTimeMillis();
            int[] resultB = dq.countPairsOptimize(n, edges, queries);
            bCost = System.currentTimeMillis() - bCost;
            if (!StringUtils.compareArray(resultA, resultB)){
                log.info("countPairsTestCompare结果有差异");
            }
            if (aCost>bCost){
                lessB++;
            }
            else if (aCost < bCost){
                lessA++;
            }
            else {
                same++;
            }
            aCostTotal += aCost;
            bCostTotal += bCost;
        }
        log.info("countPairsTestCompare结果汇总，{}次，A快{}次，B块{}次，一样快{}次，A总耗时{}, B总耗时{}",
                times, lessA, lessB, same, aCostTotal, bCostTotal);
    }

    private final int tripLimit = 15;
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        Map<Integer, Integer> maps = new HashMap<>();
        int[] link = new int[n+1];
        int[] allCase = new int[edges.length+1];
        for (int i=0; i<edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            link[from]++; link[to]++;
            maps.put(getPos(from, to), maps.getOrDefault(getPos(from, to), 0) + 1);
        }
        for(int i=1; i<n+1; i++){
            for(int j=i+1; j<n+1; j++){
                allCase[link[i] + link[j] - maps.getOrDefault(getPos(i, j), 0)]++;
            }
        }
        // 累加和
        for (int i=allCase.length-2; i>=0; i--){
            allCase[i] += allCase[i+1];
        }
        int[] result = new int[queries.length];
        for (int i=0; i<queries.length; i++){
            result[i] = allCase[queries[i]+1];
        }
        return result;
    }

    /**
     * 理论上优化版本  =>  的确快
     */
    public int[] countPairsOptimize(int n, int[][] edges, int[] queries){
        Map<Integer, Integer> maps = new HashMap<>();
        int[] link = new int[n];
        for (int i=0; i<edges.length; i++){
            int from = edges[i][0]-1;
            int to = edges[i][1]-1;
            link[from]++; link[to]++;
            maps.put(getPos(from, to), maps.getOrDefault(getPos(from, to), 0) + 1);
        }
        int[] arr = Arrays.copyOf(link, link.length);
        Arrays.sort(arr);
        int[] result = new int[queries.length];
        final int base = 0x7fff;
        for (int i=0; i<queries.length; i++){
            int total=0, bound=queries[i];
            // 二分查找快速筛选项
            for (int j=0; j<arr.length; j++){
                total += arr.length - this.binarySearchGreaterThan(arr,j+1, n-1, bound-arr[j]);
            }
            // 过滤掉不合理的
            for (Map.Entry<Integer, Integer> item : maps.entrySet()) {
                int x=(item.getKey() & base), y=(item.getKey() >> tripLimit);
//                int x=item.getKey()/n, y=item.getKey()%n;
                if (link[x] + link[y] > bound && link[x] + link[y] - item.getValue() <= bound){
                    total--;
                }
            }
            result[i] = total;
        }
        return result;
    }

    private int binarySearchGreaterThan(int[] arr,int left, int right, int target){
        int ans = right + 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    private int getPos(int i, int j){
        if (i>j){
            return (j << tripLimit) | i;
        }
        return (i << tripLimit) | j;
    }



    // 849. 到最近的人的最大距离

    private static void maxDistToClosestTest(DailyQuestion dq){
        System.out.println(dq.maxDistToClosest(new int[]{1,0,0,0,1,0,1}));
        System.out.println(dq.maxDistToClosest(new int[]{1,0,0,0}));
        System.out.println(dq.maxDistToClosest(new int[]{0,1}));
        System.out.println(dq.maxDistToClosest(StringUtils.randomIntArray(10000, 0, 2)));
    }

    public int maxDistToClosest(int[] seats) {
        int max = Integer.MIN_VALUE;
        int pre = -1;
        for (int i=0; i<seats.length; i++){
            if (seats[i] == 1){
                if (pre == -1){
                    max = i;
                }
                else if (pre != -2){
                    max = Math.max(max, (i-pre)%2 + (i-pre)/2);
                }
                pre = -2;
            }
            else if(pre == -2){
                pre = i;
            }
        }
        if (pre == -1){
            max = Math.max(max, (seats.length-1)%2 + (seats.length-1)/2);
        }
        else if (pre != -2){
            max = Math.max(max, seats.length-pre);
        }
        return max;
    }

    // 2337. 移动片段得到字符串

    public static void canChangeTest(DailyQuestion dq){
        System.out.println(dq.canChange("_L__R__R_", "L______RR"));
        System.out.println(dq.canChange("R_L_", "__LR"));
        System.out.println(dq.canChange("_R", "R_"));
        String s = StringUtils.randomArrayInSpecificCharacters(new char[]{'L', 'R', '_'}, 10000);
        String s1 = StringUtils.randomSwap(s.toCharArray(), 100);
        System.out.println(dq.canChange(s, s1));
    }

    public boolean canChange(String start, String target) {
        log.debug("\n{}\n{}", start, target);
        if (start.length() != target.length()
                || !start.replace("_", "").equals(target.replace("_", ""))){
            return false;
        }
        for (int p = 0, q = 0, len = start.length(); p<len&&q<len;p++,q++){
            while (p<len && start.charAt(p) != 'L'){
                p++;
            }
            if (p == len){
                break;
            }
            while (target.charAt(q) != 'L'){
                q++;
            }
            if (p<q){
                return false;
            }
        }
        for (int p = start.length()-1, q = start.length()-1; p>=0&&q>=0;p--,q--){
            while (p>=0 && start.charAt(p) != 'R'){
                p--;
            }
            if (p == -1){
                break;
            }
            while (target.charAt(q) != 'R'){
                q--;
            }
            if (q < p){
                return false;
            }
        }
        return true;
    }




    // 2236. 判断根结点是否等于子结点之和

    public static void checkTreeTest(DailyQuestion dq){
        System.out.println(dq.checkTree(TreeNode.buildTree(new Integer[]{10, 4, 6})));
        System.out.println(dq.checkTree(TreeNode.buildTree(new Integer[]{5, 3, 1})));
    }

    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }

    // 1388. 3n 块披萨

    public static void maxSizeSlicesTest(DailyQuestion dq){
        System.out.println(dq.maxSizeSlices(new int[]{1,2,3,4,5,6}));
        System.out.println(dq.maxSizeSlices(new int[]{8,9,8,6,1,1}));
        System.out.println(dq.maxSizeSlices(StringUtils.randomIntArray(498, 1, 1000)));
//        compareMaxSizeSlices(dq);
    }

    private static void compareMaxSizeSlices(DailyQuestion dq){
        // 执行时长测试
        long totalCostA = 0, costA, totalCostB=0, costB, lessA=0, lessB=0, same=0;
        for (int i=0; i<10000; i++){
            int[] slices = StringUtils.randomIntArray(3 + 3 * random.nextInt(187), 1, 1000);
            System.gc();
            costA = System.currentTimeMillis();
            dq.maxSizeSlicesOther(slices);
            costA = System.currentTimeMillis() - costA;
            System.gc();
            costB = System.currentTimeMillis();
            dq.maxSizeSlices(slices);
            costB = System.currentTimeMillis() - costB;
            if (costA>costB){
                lessB++;
            }
            else if (costA<costB){
                lessA++;
            }
            else {
                same++;
            }
            totalCostB += costB;
            totalCostA += costA;
        }
        log.info("10000次，A耗时短{}次，B耗时短{}次,相同耗时{}，A总耗时{},B总耗时{}", lessA, lessB, same, totalCostA, totalCostB);
    }

    public int maxSizeSlices(int[] slices) {
        return Math.max(this.maxSizeSlices(slices, 0, slices.length-2), this.maxSizeSlices(slices, 1, slices.length-1));
//        return this.maxSizeSlicesOther(slices);
    }

    public int maxSizeSlicesOther(int[] slices) {
        int len = slices.length, times = len/3;
        int[][][] dp = new int[len][times+1][2];
        int from = 0, to = len-2;
        // 节点初始化
        dp[1][1][0] = slices[from];
        dp[2][1][0] = Math.max(slices[from], slices[from+1]);
        dp[1][1][1] = slices[from+1];
        dp[2][1][1] = Math.max(slices[from+1], slices[from+2]);
        for (int i=3; i<=to-from+1; i++){
            for(int k=1; k<i&&k<=times; k++){
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-2][k-1][0] + slices[from-1+i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-2][k-1][1] + slices[from+i]);
            }
        }
        return Math.max(dp[to-from+1][times][0], dp[to-from+1][times][1]);
    }

    private int maxSizeSlices(int[] slices, int from, int to){
        int len = slices.length, times = len/3;
        int[][] dp = new int[len][times+1];
        // 节点初始化
        dp[1][1] = slices[from];
        dp[2][1] = Math.max(slices[from], slices[from+1]);
        for (int i=3; i<=to-from+1; i++){
            for(int k=1; k<i&&k<=times; k++){
                dp[i][k] = Math.max(dp[i-1][k], dp[i-2][k-1] + slices[from-1+i]);
            }
        }
        return dp[to-from+1][times];
    }


    

    // 1444. 切披萨的方案数   52/54 超时，几个小时过去了，我是废物

    public static void waysTest(DailyQuestion dq){
        System.out.println(dq.ways(new String[]{"AAA","AAA","AAA","AAA"}, 6));
        System.out.println(dq.ways(new String[]{"A..","AAA","..."}, 3));
        System.out.println(dq.ways(new String[]{"A..","AA.","..."}, 3));
        System.out.println(dq.ways(new String[]{"A..","A..","..."}, 1));
        System.out.println(dq.ways(new String[]{"A"}, 1));
        System.out.println(dq.ways(new String[]{"."}, 1));
        System.out.println(dq.ways(new String[]{"AAA","AAA","AAA","AAA","AAA","AAA"}, 6));
        System.out.println(dq.ways(StringUtils.randomArrayInSpecificCharacters(new char[]{'A', '.'}, 40, 40), 5));
    }

    private int k;
    private int splitPizzaResult;
    public int ways(String[] pizza, int k) {
        this.k = k;
        this.splitPizzaResult = 0;
        int row = pizza.length, col = pizza[0].length();
        int[][] dp = new int[row+1][col+1];
        // 以左上角统计苹果个数
        for (int i=1; i<row+1; i++){
            for (int j=1; j<col+1; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] -dp[i-1][j-1] + (pizza[i-1].charAt(j-1) == 'A' ? 1 : 0);
            }
        }
        int[][][] f = new int[row+1][col+1][k+1];
        for (int i=1; i<=row; i++){
            for (int j=1; j<=col; j++){
                for (int t=0; t<=k; t++){
                    int max = f[i-1][j][t];
                    max = Math.max(max, f[i][j-1][t]);
                    if (this.getAppleNum(dp, i-1, j, i, j) > 0){
                        max = Math.max(max, f[i-1][j][t-1]);
                    }
                    if (this.getAppleNum(dp, i, j-1, i, j) > 0){
                        max = Math.max(max, f[i][j-1][t-1]);
                    }
                    f[i][j][t] = max;
                }
            }
        }
        return f[row][col][k];
    }

    private int getAppleNum(int[][] dp, int r1, int c1, int r2, int c2){
        int row = dp.length, col = dp[0].length;
        if (r1 >= row || r2 >= row || c1 >= col || c2 >= col){
            return 0;
        }
        return dp[r2][c2] - dp[r2][c1-1] - dp[r1-1][c2] + dp[r1-1][c1-1];
    }

    // 2682. 找出转圈游戏输家

    public static void circularGameLosersTest(DailyQuestion dq){
        StringUtils.printIntArray(dq.circularGameLosers(5, 2));
        StringUtils.printIntArray(dq.circularGameLosers(4, 4));
        StringUtils.printIntArray(dq.circularGameLosers(20, 4));
    }

    public int[] circularGameLosers(int n, int k) {
        boolean[] dp = new boolean[n];
        int i = 0, len = n, limit = 1;
        while (true){
            if (dp[i]){
                break;
            }
            n--;
            dp[i] = true;
            i = (i + k * limit) % len;
            limit++;
        }
        int[] result = new int[n];
        i = 0;
        for (int p=0; p<len; p++){
            if (!dp[p]){
                result[i++] = p+1;
            }
        }
        return result;
    }

    // 833. 字符串中的查找与替换

    public static void findReplaceString(DailyQuestion dq){
        System.out.println(dq.findReplaceString("abcd", new int[]{0,2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"}));
        System.out.println(dq.findReplaceString("abcd", new int[]{0,2}, new String[]{"ab", "ec"}, new String[]{"eee", "ffff"}));
        System.out.println(dq.findReplaceString("abcdefgh", new int[]{4,1,2}, new String[]{"e", "b", "c"}, new String[]{"eee", "bbb", "ccc"}));
    }

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        if (indices.length < 1){
            return s;
        }
        // 排序使得整体有序
        this.sort(indices, sources, targets, 0, indices.length-1);
        // 开始逐个替换操作，拼凑结果
        StringBuilder builder = new StringBuilder();
        int pos = 0;
        for (int i=0,len=indices.length; i<len;){
            if (pos == indices[i]){
                if (this.checkSubStr(s, pos, sources[i])){
                    builder.append(targets[i]);
                    pos += sources[i].length();
                }
                i++;
                continue;
            }
            builder.append(s.charAt(pos++));
        }
        // 拼上多余的
        if (pos < s.length()){
            builder.append(s, pos ,s.length());
        }
        // 返回结果
        return builder.toString();
    }

    private boolean checkSubStr(String s, int pos, String substr){
        if (pos + substr.length() > s.length()){
            return false;
        }
        for (int i=pos,j=0; j<substr.length(); i++,j++){
            if (s.charAt(i) != substr.charAt(j)){
                return false;
            }
        }
        return true;
    }

    private void sort(int[] indices, String[] sources, String[] targets, int l, int r){
        if (l >= r){
            return;
        }
        int check = random.nextInt(r-l) + l;
        int i = l, j = r, k = indices[check];
        this.swap(indices, sources, targets, l, check);
        String temp;
        while (i < j){
            while (i < j && indices[j] >= k){
                j--;
            }
            if (i < j){
                this.swap(indices, sources, targets, i, j);
            }
            while (i < j && indices[i] < k){
                i++;
            }
            if (i < j){
                this.swap(indices, sources, targets, i, j);
            }
        }
        this.sort(indices, sources, targets, l, i-1);
        this.sort(indices, sources, targets, i+1, r);
    }


    private void swap(int[] indices, String[] sources, String[] targets, int l, int r){
        if (l >= r){
            return;
        }
        int k;
        String temp;
        k = indices[l]; indices[l] = indices[r]; indices[r] = k;
        temp = sources[l]; sources[l] = sources[r]; sources[r] = temp;
        temp = targets[l]; targets[l] = targets[r]; targets[r] = temp;
    }

    // 617. 合并二叉树

    public static void  mergeTreesTest(DailyQuestion dq){
        TreeNode.printTree(dq.mergeTrees(TreeNode.buildTree(new Integer[]{1,3,2,5}),
                TreeNode.buildTree(new Integer[]{2,1,3,null,4,null,7})));
        TreeNode.printTree(dq.mergeTrees(TreeNode.buildTree(new Integer[]{1}),
                TreeNode.buildTree(new Integer[]{1,2})));
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 != null && root2 != null){
            TreeNode newRoot = new TreeNode(root1.val + root2.val);
            newRoot.left = this.mergeTrees(root1.left, root2.left);
            newRoot.right = this.mergeTrees(root1.right, root2.right);
            return newRoot;
        }
        else if (root1 != null){
            return root1;
        }
        else if (root2 != null){
            return root2;
        }
        else {
            return null;
        }
    }

    // 88. 合并两个有序数组

    public static void mergeTest(DailyQuestion dq){
        int[] result = new int[]{1,2,3,0,0,0};
        dq.merge(result, 3 ,new int[]{2,5,6}, 3);
        StringUtils.printIntArray(result);
        result = new int[]{1};
        dq.merge(result, 1 ,new int[]{}, 0);
        StringUtils.printIntArray(result);
        result = new int[]{0};
        dq.merge(result, 0 ,new int[]{1}, 1);
        StringUtils.printIntArray(result);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0){
            return;
        }
        int i=m-1, j=n-1, pos=nums1.length-1;
        while (i >=0 && j >= 0){
            if (nums1[i] > nums2[j]){
                nums1[pos--] = nums1[i--];
            }
            else {
                nums1[pos--] = nums2[j--];
            }
        }
        // 提前跳出场景
        if (i == pos){
            return;
        }
        while (i >= 0){
            nums1[pos--] = nums1[i--];
        }
        while (j >= 0){
            nums1[pos--] = nums2[j--];
        }
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
            if (set.add(backs[i])){
                return backs[i];
            }
            break;
        }
        if (i == fronts.length){
            return 0;
        }
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
        if (len < 2){
            return;
        }
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
        if (a == b){
            return;
        }
        this.swap(fronts, a, b);
        this.swap(backs, a, b);
    }



}
