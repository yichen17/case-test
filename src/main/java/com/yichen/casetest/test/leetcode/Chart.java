package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/7/23 13:54
 * @describe 图相关算法
 */
@Slf4j
public class Chart {

    public static void main(String[] args) {
        Chart chart = new Chart();
        System.out.println(chart.maxAreaOfIsland(new int[][]{{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}}));
        StringUtils.divisionLine();
        System.out.println(StringUtils.batchReplaceBracket("[[0,0,0],[0,1,0],[0,0,0]]", new String[]{"[", "]"}, new String[]{"{", "}"}));
        StringUtils.arrayTwoDimensionPrint(chart.updateMatrix(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
        StringUtils.divisionLine();
//        System.out.println(chart.ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
//        System.out.println(chart.ladderLength("ymain", "oecij", Arrays.asList("ymann","yycrj","oecij","ymcnj","yzcrj","yycij","xecij","yecij","ymanj","yzcnj","ymain")));
        System.out.println(chart.ladderLength("qa", "sq", Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye")));
        System.out.println(chart.openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202"));
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        double[] values = new double[]{2.0f, 3.0f};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));

        double[] result = chart.calcEquation(equations, values, queries);
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (double item : result){
            builder.append(item).append(",");
        }
        builder.replace(builder.length()-1, builder.length(), "]");
        System.out.println(builder);
        StringUtils.divisionLine();
        System.out.println(chart.longestIncreasingPath(new int[][]{{9,9,6},{6,6,8},{2,1,1}}));
        StringUtils.divisionLine();
        System.out.println(chart.alienOrder(new String[]{"wrt","wrf","er","ett","rftt"}));
//        System.out.println(chart.sequenceReconstruction(new int[]{1,2,3}, new int[][]{{1,2},{1,3},{2,3}}));
        System.out.println(chart.sequenceReconstruction(new int[]{1,2,3,4}, new int[][]{{1,2,3},{1,2,4},{3,4}}));
        StringUtils.divisionLine();
        System.out.println(chart.findCircleNum(new int[][]{{1,1,1},{1,1,1},{1,1,1}}));
        StringUtils.divisionLine();
        System.out.println(chart.longestConsecutive(new int[]{100,4,200,1,3,2}));
        StringUtils.divisionLine();
        System.out.println(chart.numSimilarGroups(new String[]{"tars","rats","arts","star"}));
    }

    // 相似的字符串

    public int numSimilarGroups(String[] strs) {
        List<Set<String>> list = new LinkedList<>();
        for(String str : strs){
            if (list.size() == 0){
                Set<String> set = new HashSet<>();
                set.add(str);
                list.add(set);
                continue;
            }
            Set<String> cc = new HashSet<>();
            Iterator<Set<String>> iter = list.iterator();
            while(iter.hasNext()){
                Set<String> item = iter.next();
                boolean match = false;
                for(String s : item){
                    if (like(s, str)){
                        match = true;
                        break;
                    }
                }
                if (match){
                    cc.addAll(item);
                    iter.remove();
                }
            }
            cc.add(str);
            list.add(cc);
        }
        return list.size();
    }

    public boolean like(String a, String b){
        int count = 0;
        for(int i=0; i<a.length(); i++){
            if (a.charAt(i) != b.charAt(i)){
                count ++;
            }
        }
        return count == 2 || count == 0;
    }

    // 最长连续序列

    public int longestConsecutive(int[] nums) {
        int max = 0;
        Arrays.sort(nums);
        int count = 1, i=1, pre=nums[0];
        while (i < nums.length){
            if (pre == nums[i]){
                i++;
                continue;
            }
            if (pre+1 == nums[i]){
                pre = nums[i];
                i++;
                count++;
            }
            else {
                max = Math.max(max, count);
                count = 1;
                pre = nums[i];
                i++;
            }
        }
        if (count != 1){
            max = Math.max(max, count);
        }
        return max;
    }


    // 省份数量

    public int findCircleNum(int[][] isConnected) {
        int len = isConnected.length;
        int[] dp = new int[len];
        Arrays.fill(dp, -1);
        for(int i=0; i<len; i++){
            for(int j=0; j<i; j++){
                if (isConnected[i][j] == 0)continue;
                int p = i, q = j;
                while(dp[p] != -1){
                    p = dp[p];
                }
                while(dp[q] != -1){
                    q = dp[q];
                }
                if(p == q)continue;
                dp[q] = p;
            }
        }
        int result = 0;
        for(int i=0; i<len; i++){
            if (dp[i] == -1)result++;
        }
        return result;
    }

    // 重建序列

    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        Map<Integer, Set<Integer>> dp = new HashMap<>();
        Map<Integer, Integer> inDegrees = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for(int[] item : sequences){
            int i;
            for(i=0; i<item.length-1; i++){
                fill(dp, inDegrees, item[i], item[i+1]);
            }
            inDegrees.putIfAbsent(item[i], 0);
        }
        if (nums.length != inDegrees.size())return false;
        Queue<Integer> queue = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry : inDegrees.entrySet()){
            if (entry.getValue() == 0){
                queue.offer(entry.getKey());
            }
        }
        while(!queue.isEmpty()){
            if (queue.size() != 1)return false;
            int item = queue.poll();
            result.add(item);
            if (dp.get(item) == null){
                continue;
            }
            for(int tt : dp.get(item)){
                int v = inDegrees.get(tt);
                v--;
                if (v==0){
                    queue.offer(tt);
                }
                else {
                    inDegrees.put(tt, v);
                }
            }
        }
        if (result.size() != nums.length)return false;
        for(int i=0; i<nums.length; i++){
            if (nums[i] != result.get(i))return false;
        }
        return true;
    }

    public void fill(Map<Integer, Set<Integer>> dp, Map<Integer, Integer> inDegrees, int a, int b){
        Set<Integer> out = dp.getOrDefault(a, new HashSet<>());
        inDegrees.putIfAbsent(a, 0);
        if (out.add(b)){
            int v =inDegrees.getOrDefault(b, 0);
            v++;
            inDegrees.put(b, v);
        }
        dp.put(a, out);
    }

    // 外星文字典

    public String alienOrder(String[] words) {
        if(words.length == 1){
            return words[0];
        }
        Set<Integer>[] dp = new Set[26];
        int[] indegree = new int[26];
        for (int i=0; i<26; i++){
            indegree[i] = -1;
        }
        int total = build(dp, indegree, words);
        if (total == -1)return "";
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<26; i++){
            if (indegree[i] == 0){
                queue.offer(i);
            }
        }
        StringBuilder result = new StringBuilder();
        while(!queue.isEmpty()){
            int c = queue.poll();
            result.append((char)(c + 'a'));
            if (dp[c] == null)continue;
            for(int item : dp[c]){
                indegree[item]--;
                if (indegree[item] == 0){
                    queue.offer(item);
                }
            }
        }
        if (result.length() == total)return result.toString();
        return "";
    }

    public int build(Set<Integer>[] dp, int[] indegree, String[] words){
        boolean[] visit = new boolean[26];
        for(String word : words){
            fill(word, visit, indegree);
        }
        // 单词之间比较
        for(int i=0; i<words.length; i++){
            for(int j=i+1; j<words.length; j++){
                int pos = check(words[i], words[j]);
                if (pos == -1)return -1;
                if (pos != -2){
                    int a = words[i].charAt(pos) - 'a';
                    int b = words[j].charAt(pos) - 'a';
                    if (dp[a] == null){
                        dp[a] = new HashSet<>();
                    }
                    if (dp[a].add(b)){
                        indegree[b]++;
                    }
                }
            }
        }
        int count = 0;
        for (boolean b : visit) {
            if (b) {
                count++;
            }
        }
        return count;
    }

    public void fill(String a, boolean[] visit, int[] indegree){
        for(int i=0; i<a.length(); i++){
            int pos = a.charAt(i) - 'a';
            visit[pos] = true;
            if (indegree[pos] == -1){
                indegree[pos] = 0;
            }
        }
    }

    public int check(String a, String b){
        int len = Math.min(a.length(), b.length());
        for(int i=0; i<len; i++){
            if (a.charAt(i) != b.charAt(i)){
                return i;
            }
        }
        return a.length() > b.length() ? -1 : -2;
    }

    // 最长递增路径  visit 可以移除，无用功。。

    int[][] direct = new int[][]{{-1,0}, {1,0}, {0, -1}, {0, 1}};

    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        Set<Integer> visit = new HashSet<>();
        int[][] bp = new int[row][col];
        int max = -1;
        for (int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                bp[i][j]= -1;
            }
        }
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                visit.add(calTT(i, j));
                max = Math.max(max, dfs(matrix, i, j, visit, 1, bp));
                visit.remove(calTT(i, j));
            }
        }
        return max;
    }

    public int dfs(int[][] matrix, int x, int y, Set<Integer> visit, int count, int[][]bp){
        int p,q;
        int row = matrix.length;
        int col = matrix[0].length;
        int max = count, result;
        for(int[] directItem : direct){
            p = x + directItem[0];
            q = y + directItem[1];
            if (p>=0 && p<row && q >=0 && q < col && matrix[p][q] > matrix[x][y] && visit.add(calTT(p, q))){
                if (bp[p][q] != -1){
                    result = count + bp[p][q];
                }
                else {
                    result = dfs(matrix, p, q, visit, count+1, bp);
                    bp[p][q] = result - count;
                }
                max = Math.max(result, max);
                visit.remove(calTT(p, q));
            }
        }
        return max;
    }

    public int calTT(int x, int y){
        return (x<<8) | y;
    }



    // 计算除法

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> maps = constructPosMap(equations);
        double[][] dp = constructDp(equations, values, maps);
        double[] result = new double[queries.size()];
        boolean[] visit = new boolean[dp.length];
        for (int i=0; i<queries.size(); i++){
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if (!maps.containsKey(a) || !maps.containsKey(b)){
                result[i] = -1.0f;
                continue;
            }
            int p = maps.get(a);
            int q = maps.get(b);
            Double val = searchEqualVal(maps, dp, p, q, 1.0f, visit);
            if (val == null){
                result[i] = -1.0f;
            }
            else {
                result[i] = val;
            }
        }
        return result;
    }

    private Double searchEqualVal(Map<String, Integer> maps, double[][] dp, int from, int to, double v, boolean[] visit){
        if (from == to){
            return v;
        }
        visit[from] = true;
        for(int i=0; i<dp[from].length; i++){
            if (dp[from][i] == 0 || visit[i]){
                continue;
            }
            Double result = searchEqualVal(maps, dp, i, to, v*dp[from][i], visit);
            if (result != null){
                visit[from] = false;
                return result;
            }
        }
        visit[from] = false;
        return null;
    }



    private double[][] constructDp(List<List<String>> equations, double[] values, Map<String, Integer> maps){
        double[][] dp = new double[maps.size()][maps.size()];
        int p,q;
        for (int i=0; i<equations.size(); i++){
            p = maps.get(equations.get(i).get(0));
            q = maps.get(equations.get(i).get(1));
            dp[p][q] = values[i];
            dp[q][p] = 1/values[i];
        }
        return dp;
    }


    private Map<String, Integer> constructPosMap(List<List<String>> equations){
        Map<String, Integer> maps = new HashMap<>();
        int pos = 0, p, q;
        for (int i=0; i<equations.size(); i++){
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            if (!maps.containsKey(a)){
                maps.put(a, pos++);
            }
            if (!maps.containsKey(b)){
                maps.put(b, pos++);
            }
        }
        return maps;
    }

    // 开密码锁

    public int openLock(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        if (deadSet.contains("0000"))return -1;
        if (target.equals("0000"))return 0;
        Set<String> visit = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        int times = 0;
        while (!queue.isEmpty()){
            int len = queue.size();
            times++;
            while (len> 0){
                len--;
                String val = queue.poll();
                List<String> items = rotate(val);
                for (String item : items){
                    if (deadSet.contains(item)){
                        continue;
                    }
                    if (item.equals(target)){
                        return times;
                    }
                    // 没有访问过的记录，下次遍历
                    if (visit.add(item)){
                        queue.add(item);
                    }
                }
            }
        }
        return  -1;
    }

    public List<String> rotate(String s){
        char[] items = s.toCharArray();
        List<String> result = new ArrayList<>();
        char old;
        for (int i=0; i<items.length; i++){
            old = items[i];
            items[i] =(char) (((old - '0' + 1) % 10) + '0');
            result.add(new String(items));
            items[i] = (char)(((old - '0' + 9) % 10 )+ '0');
            result.add(new String(items));
            items[i] = old;
        }
        return result;
    }

    // 单词演变  考虑清楚

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, Boolean> maps = new HashMap<>();
        for (String word : wordList){
            maps.put(word, false);
        }
        return ladderLength(beginWord, endWord, maps, 1);
    }

    public int ladderLength(String begin, String end, Map<String, Boolean> maps, int depth){

        Queue<String> useWord = new LinkedList<>();
        useWord.add(begin);
        while(!useWord.isEmpty()){
            depth++;
            int len = useWord.size();
            while (len > 0){
                String word = useWord.poll();
                for (int k=0; k<word.length(); k++){
                    int pos = -1;
                    StringBuilder builder = new StringBuilder(word);
                    // 变换，找到第一个在字典中且未使用过的  pos为下一个扫描位置，26说明到末尾了
                    pos = posSwitch(builder, k, pos, maps);
                    while (pos < 26){
                        if (end.equals(builder.toString())){
                            return depth;
                        }
                        useWord.add(builder.toString());
                        pos = posSwitch(builder, k, pos, maps);
                    }
                }
                len--;
            }
        }
        return 0;
    }



    public static final String[] LETTER = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public int posSwitch(StringBuilder b, int pos, int time, Map<String, Boolean> maps){
        if (time >= 25){
            return 27;
        }
        time++;
        do {
            b.replace(pos, pos+1, LETTER[time]);
            if (!maps.getOrDefault(b.toString(), true)){
                maps.put(b.toString(), true);
                return time;
            }
            time++;
        }
        while (time < 26);
        return time;
    }

    // 矩阵中的距离

    public int[][] updateMatrix(int[][] mat) {
        int row = mat.length, col = mat[0].length;
        int[][] result = new int[row][col];
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (mat[i][j] == 0){
                    result[i][j] = 0;
                    queue.add(new Pair<>(i, j));
                }
                else {
                    result[i][j] = -1;
                }
            }
        }
        int depth = 1;
        while (!queue.isEmpty()){
            int len = queue.size();
            while (len > 0){
                len--;
                Pair<Integer, Integer> item = queue.poll();
                if (item.getKey() > 0 && result[item.getKey()-1][item.getValue()] == -1){
                    result[item.getKey()-1][item.getValue()] = depth;
                    queue.add(new Pair<>(item.getKey()-1, item.getValue()));
                }
                if (item.getKey() < row-1 && result[item.getKey()+1][item.getValue()] == -1){
                    result[item.getKey() + 1][item.getValue()] = depth;
                    queue.add(new Pair<>(item.getKey()+1, item.getValue()));
                }
                if (item.getValue() > 0 && result[item.getKey()][item.getValue()-1] == -1){
                    result[item.getKey()][item.getValue()-1] = depth;
                    queue.add(new Pair<>(item.getKey(), item.getValue()-1));
                }
                if (item.getValue() < col-1 && result[item.getKey()][item.getValue()+1] == -1){
                    result[item.getKey()][item.getValue()+1] = depth;
                    queue.add(new Pair<>(item.getKey(), item.getValue()+1));
                }
            }
            depth++;
        }
        return result;
    }

    // 岛屿的最大面积

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0, rl = grid.length, cl = grid[0].length;
        Set<Integer> visit = new HashSet<>();
        for (int i=0; i<rl; i++){
            for (int j=0; j<cl; j++){
                if (grid[i][j] == 1 && visit.add(cal(i,j))){
                    max = Math.max(max, disposableSearch(grid, visit, i, j, 1));
                }
            }
        }
        return max;
    }

    public int disposableSearch(int[][] grid, Set<Integer> visit, int r,int c, int count){
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length)return count;
        int result = count;
        if (r > 0 && (grid[r-1][c] == 1) && visit.add(cal(r-1, c))){
            result = disposableSearch(grid, visit, r-1, c, result+1);
        }
        if ((r < grid.length -1) && (grid[r+1][c] == 1) && visit.add(cal(r+1, c))){
            result =  disposableSearch(grid, visit, r+1, c, result+1);
        }
        if (c > 0 && (grid[r][c-1] == 1) && visit.add(cal(r, c-1))){
            result = disposableSearch(grid, visit, r, c-1, result+1);
        }
        if ((c < grid[0].length -1) && (grid[r][c+1] == 1) && visit.add(cal(r, c+1))){
            result = disposableSearch(grid, visit, r, c+1, result+1);
        }
        return result;
    }

    public int cal(int r, int c){
        return (r << 8) | c;
    }

}
