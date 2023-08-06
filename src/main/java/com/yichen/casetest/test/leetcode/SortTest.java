package com.yichen.casetest.test.leetcode;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.yichen.casetest.utils.StringUtils;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/22 08:38
 * @describe 排序测试
 *  插入排序、希尔排序、选择排序、堆排序、冒泡排序、快速排序、归并排序、计数排序、桶排序、基数排序
 *  参考文章：
 *      => https://www.cnblogs.com/onepixel/articles/7674659.html
 */
@Slf4j
public class SortTest {

    public static void main(String[] args) {

        //设置main方法日志级别   => 可用
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> {
            logger.setLevel(Level.WARN);
        });

        ChainInfo chainInfo = ChainInfo.builder().len(10000).limit(10000).times(10000)
                .randomLen(true)
                .randomLimit(true)
                .build();
        ChainExec chainExec = buildChainExec(chainInfo);
        chainExec.exec();

//        Integer[] array = StringUtils.randomIntArray(100, 0 ,100);
//        mergeSort(array);
//        System.out.println(StringUtils.printArray(array));
//        System.out.println(StringUtils.checkOrder(array, true));

    }





    // 计数排序

    private static void countingSort(Integer[] data){
        if (Objects.isNull(data) || data.length < 2){
            return;
        }
//        Integer max = Arrays.stream(data).max(Integer::compare).get();
//        Integer min = Arrays.stream(data).min(Integer::compare).get();
        Pair<Integer, Integer> maxAndMin = getMaxAndMin(data);
        Integer max = maxAndMin.getKey();
        Integer min = maxAndMin.getValue();
        int[] f = new int[max-min+1];
        // 遍历填充
        for(Integer item : data){
            f[item - min]++;
        }
        int pos = 0;
        // 依次取数填充结果
        for (int i=0; i<f.length; i++){
            if (f[i] == 0){
                continue;
            }
            while (f[i] > 0){
                f[i]--;
                data[pos++] = min + i;
            }
        }
    }

    // 桶排序

    /**
     * 桶排序是计数排序的升级版本，可以自定义跨度以节省存储空间
     * @param data
     */
    private static void bucketSort(Integer[] data){
        if (Objects.isNull(data) || data.length < 2){
            return;
        }
        int interval = 10;
        Pair<Integer, Integer> maxAndMin = getMaxAndMin(data);
        Integer max = maxAndMin.getKey();
        Integer min = maxAndMin.getValue();
        // 去整麻烦，直接 多一个
        int len = (max - min)/ interval + 2;
        ArrayList<Integer>[] bucket = new ArrayList[len];
        // 对应桶计数
        int pos,i,temp,val;
        for (int item : data){
            pos = (item - min) / interval;
            ArrayList<Integer> lists = bucket[pos];
            if (Objects.isNull(lists)){
                lists = new ArrayList<>();
                bucket[pos] = lists;
            }
            i = lists.size() - 1;
            // 执行插入排序
            while (i>=0 && lists.get(i) > item){
                i--;
            }
            lists.add(i+1, item);
        }
        // 单次遍历构建
        pos = 0;
        for (i=0; i<bucket.length; i++){
            if (Objects.isNull(bucket[i]) || bucket[i].size() == 0){
                continue;
            }
            for (int j=0; j<bucket[i].size(); j++){
                data[pos++] = bucket[i].get(j);
            }
        }
    }

    // 基数排序

    /**
     * 个、十、百、千 每一位都排序比较
     *      只能对等长比较，不等长的存在问题
     * @param data
     */
    private static void radixSort(Integer[] data){
        if (Objects.isNull(data) || data.length < 2){
            return;
        }
        int interval = 10;
        Pair<Integer, Integer> maxAndMin = getMaxAndMin(data);
        Integer max = maxAndMin.getKey();
        // 计算最大位数
        int times = 1;
        while (interval < max){
            times++;
            interval *= 10;
        }
        int mod = 10, dev = 1, i, j, k;
        ArrayList<Integer>[] buckets;
        for (i=0; i<times; i++, dev *= 10, mod *= 10){
            buckets = new ArrayList[10];
            // 桶置入
            for (j=0; j<data.length; j++){
                int pos = data[j] % mod / dev;
                ArrayList<Integer> item = buckets[pos];
                if (Objects.isNull(item)){
                    item = new ArrayList<>();
                }
                buckets[pos] = item;
                // 插入排序
                k = item.size() - 1;
                while (k >=0 && item.get(k) > data[j]){
                    k--;
                }
                item.add(k+1, data[j]);
            }
            // 逐位排序
            j = 0;
            for (k=0; k<buckets.length; k++){
                if (Objects.isNull(buckets[k]) || buckets[k].size() == 0){
                    continue;
                }
                for (int item : buckets[k]){
                    data[j++] = item;
                }
            }
        }
    }

    // 堆排序

    private static void heapSort(Integer[] data){
        if (Objects.isNull(data) || data.length < 2){
            return;
        }
        transformBigHeap(data);
        // 每次确定最大的数
        for (int i=data.length-1; i>0; i--){
            // 确定结果保存
            swap(data, 0, i);
            // 再次重建
            rebuild(data, 0, i);
        }
    }



    /**
     * 构造成最大堆
     * @param data
     */
    private static void transformBigHeap(Integer[] data){
        if (Objects.isNull(data) || data.length < 2){
            return;
        }
        int len = data.length, j;
        for (int i=Math.floorDiv(len, 2); i>=0; i--){
            rebuild(data, i, len);
        }
    }

    private static void rebuild(Integer[] data, int start, int limit){
        int left = start * 2 + 1;
        int right = start * 2 + 2;
        int max = start;
        if (left < limit && data[left] > data[max]){
            max = left;
        }
        if (right < limit && data[right] > data[max]){
            max = right;
        }
        if (start != max){
            swap(data, start, max);
            rebuild(data, max, limit);
        }
    }



    // 希尔排序   简单插入排序的优化版本

    private static void shellSort(Integer[] data){
        // 宽度每次除二，逐渐变小 例如总长度16 =>   16、8、4、2、1、0
        int len = data.length;
        for (int gap = Math.floorDiv(len, 2); gap > 0; gap = Math.floorDiv(gap, 2)){
            // why ???   我认为应该是 i=len-gap
            for (int i=gap; i<len; i++){
                int j = i;
                int current = data[j];
                // 子序列使用简单插入排序  查找和移动可以合并成一步，nice
                while (j - gap >= 0 && current < data[j-gap]){
                    data[j] = data[j - gap];
                    j = j - gap;
                }
                data[j] = current;
            }
        }

    }

    // 插入排序

    private static void insertionSort(Integer[] data){
        int i,j,val,k;
        for (i=0; i<data.length; i++){
            j = i-1;
            while (j>=0 && data[j] > data[i]){
                j--;
            }
            // j的坐标为第一个小于等于它的，如果它比最大值都大，结束
            if (j == i-1){
                continue;
            }
            // 保存节点值
            val = data[i];
            // 整体后移
            for (k=i; k>j+1; k--){
                data[k] = data[k-1];
            }
            // 确定位置插入
            data[j+1] = val;
        }
    }

    /**
     * 优化版本  将找到和后移合并，一次处理
     * @param data
     */
    private static void insertionSortOptimize(Integer[] data){
        int i,j,val,k;
        for (i=0; i<data.length; i++){
            j = i;
            val = data[j];
            // 查找位置和后移合并
            while (j - 1 >= 0 && val < data[j-1]){
                data[j] = data[j-1];
                j--;
            }
            // 确定位置插入
            data[j] = val;
        }
    }

    // 简单选择排序

    private static void simpleSelectionSort(Integer[] data){
        int minPos;
        // 每次选择最小的，放在遍历的首位
        for (int i=0; i<data.length - 1; i++ ){
            minPos = i;
            for (int j=i+1; j<data.length; j++){
                if (data[minPos] > data[j]){
                    minPos = j;
                }
            }
            swap(data, i, minPos);
        }
    }

    //  冒泡排序

    private static void bubbleSort(Integer[] data){
        if (Objects.isNull(data) || data.length < 2){
            return;
        }
        boolean swapItem = false;
        // 循环比较，直至有序
        while (!swapItem){
            swapItem = true;
            for (int i=0; i<data.length - 1; i++){
                if (data[i] > data[i+1]){
                    swapItem = false;
                    swap(data, i, i+1);
                }
            }
        }
    }

    //  归并排序

    private static void mergeSort(Integer[] data){
        if (Objects.isNull(data) || data.length < 2){
            return;
        }
        mergeSort(data, 0, data.length-1);
    }

    private static void mergeSort(Integer[] data, int left, int right){
        if (left == right){
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(data, left, mid);
        mergeSort(data, mid+1, right);
        merge(data, left, mid, mid+1, right);
    }

    private static void merge(Integer[] data, int l1, int r1, int l2, int r2){
        Integer[] temp = Arrays.copyOfRange(data, l1, r2+1);
        int limit = l1;
        int pos = l1;
        while (l1 <= r1 && l2 <= r2){
            if (temp[l1-limit] > temp[l2-limit]){
                data[pos++] = temp[l2-limit];
                l2++;
            }
            else {
                data[pos++] = temp[l1-limit];
                l1++;
            }
        }
        while (l1 <= r1){
            data[pos++] = temp[l1-limit];
            l1++;
        }
        while (l2 <= r2){
            data[pos++] = temp[l2-limit];
            l2++;
        }
    }




    //=============  归并排序  链表

    private static ListNode linkedListMergeSort(ListNode head){
        ListNode pos = getMid(head);
        if (pos == head){
            return head;
        }
        ListNode next = pos.next;
        pos.next = null;
        ListNode left = linkedListMergeSort(head);
        ListNode right = linkedListMergeSort(next);
        return merge(left, right);
    }


    private static ListNode merge(ListNode left, ListNode right){
        if (left == null){
            return right;
        }
        if (right == null){
            return left;
        }
        ListNode head = new ListNode();
        ListNode pos = head;
        ListNode p = left, q = right;
        while(p != null && q != null){
            if (p.val > q.val){
                pos.next = q;
                q = q.next;
            }
            else {
                pos.next = p;
                p = p.next;
            }
            pos = pos.next;
        }
        if (p == null){
            pos.next = q;
        }
        if (q == null){
            pos.next = p;
        }
        return head.next;
    }


    private static ListNode getMid(ListNode root){
        if (root == null || root.next == null){
            return root;
        }
        ListNode p = root, q = root;
        while(q.next != null && q.next.next!= null){
            p = p.next;
            q = q.next.next;
        }
        return p;
    }

    
    //===================快排

    private static Random random = new Random();
    /**
     * 快排实现
     * @param nums 待排序数组
     */
    private static void quickSort(Integer[] nums){
        quickSort(nums, 0, nums.length-1);
    }

    private static void quickSort(Integer[] nums, int left, int right){
        if (nums.length == 0 || left  > right){
            return ;
        }
        int pos = partitionPos(nums, left, right);
        // 左子域处理
        quickSort(nums, left, pos -1);
        // 右子域处理
        quickSort(nums, pos+1, right);
    }

    private static int partitionPos(Integer[] nums, int left, int right){
        left = Math.max(0, left);
        right = Math.min(right, nums.length-1);
        // 随机位置可以避免子域划分不合理问题，即每次都有一个长度为1
        int randomPos = random.nextInt(right-left+1) + left;
        swap(nums, left, randomPos);
        int first = nums[left];
        int i=left, j=right;
        while (i < j){
            while(nums[j] >= first && i<j){
                j--;
            }
            if (i >= j){
                break;
            }
            swap(nums, i, j);
            while (nums[i] < first && i < j){
                i++;
            }
            if (i >= j){
                break;
            }
            swap(nums, i, j);
        }
        nums[j] = first;
        return j;
    }

    private static void swap(Integer[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static Pair<Integer, Integer> getMaxAndMin(Integer[] data){
        if (Objects.isNull(data) || data.length == 0){
            return null;
        }
        int max = data[0], min = data[0];
        for (int i=1; i<data.length; i++){
            if (max < data[i]){
                max = data[i];
            }
            if (min > data[i]){
                min = data[i];
            }
        }
        return new Pair<>(max, min);
    }


    private static ChainExec buildChainExec(ChainInfo chainInfo){
        ChainExec chainExec = new ChainExec(chainInfo);
        chainExec.initRoot(ChainItem.builder().desc("quick sort").consumer(SortTest::quickSort).build());
        chainExec.appendTail(ChainItem.builder().desc("insert sort").consumer(SortTest::insertionSort).build())
                .appendTail(ChainItem.builder().desc("simple select sort").consumer(SortTest::simpleSelectionSort).build())
                .appendTail(ChainItem.builder().desc("shell sort").consumer(SortTest::shellSort).build())
                .appendTail(ChainItem.builder().desc("count sort").consumer(SortTest::countingSort).build())
                .appendTail(ChainItem.builder().desc("bubble sort").consumer(SortTest::bubbleSort).build())
                .appendTail(ChainItem.builder().desc("heap sort").consumer(SortTest::heapSort).build())
                .appendTail(ChainItem.builder().desc("bucket sort").consumer(SortTest::bucketSort).build())
//                .appendTail(ChainItem.builder().desc("radix sort").consumer(SortTest::radixSort).build())
                .appendTail(ChainItem.builder().desc("merge sort").consumer(SortTest::mergeSort).build())
        ;
        return chainExec;
    }

    @AllArgsConstructor
    @Data
    @Builder
    @NoArgsConstructor
    static class ChainItem{
        private String desc;
        private Consumer<Integer[]> consumer;
        private ChainItem next;

        public static ChainItem emptyItem(){
            return new ChainItem("空头", null, null);
        }

    }

    static class ChainExec{
        private ChainItem root;
        private Integer[] array;
        private ChainItem tail;
        private ChainInfo chainInfo;



        public ChainExec(ChainInfo chainInfo) {
            this.chainInfo = chainInfo;
        }


        public void initRoot(ChainItem root){
            this.root = root;
            this.tail = root;
        }

        public ChainExec appendTail(ChainItem next){
            this.tail.next = next;
            this.tail = next;
            return this;
        }

        private int execSingle(){
            ChainItem pos = root;
            long start;
            int result = 0;
            String sortName = "";
            Long cost, costMin = null;
            while (pos != null){
                Integer[] newItem = Arrays.copyOf(this.array, this.array.length);

                // 手动gc，避免期间gc
                System.gc();

                start = System.nanoTime();
                pos.getConsumer().accept(newItem);
                cost = System.nanoTime() - start;
                log.info("执行耗时 {} => {}", cost, pos.getDesc());
                boolean flag = StringUtils.checkOrder(newItem, true);
                if (!flag){
                    log.warn("{} 排序实现异常\n待排序{}\n排序结果{}", pos.getDesc(),
                            StringUtils.printArray(this.array), StringUtils.printArray(newItem));
                }
                if (log.isDebugEnabled()){
                    log.debug("排序后数据 {}", StringUtils.printArray(newItem));
                }

                if (Objects.isNull(costMin) || costMin > cost){
                    costMin = cost;
                    sortName = pos.getDesc();
                }

                result += flag ? 0 : 1;
                pos = pos.next;
            }
            log.info("当次排序最快的为{}，耗时 {}", sortName, costMin);
            StringUtils.divisionLine("next round");
            return result;
        }

        public boolean exec(){
            for (int i=0; i<chainInfo.getTimes(); i++){
                // 一次轮换变更数据源
                int len = chainInfo.isRandomLen() ? (int) (Math.random() * chainInfo.getLen()) : chainInfo.getLen();
                int limit = chainInfo.isRandomLimit() ? (int) (Math.random() * chainInfo.getLimit()) : chainInfo.getLimit();
                StringUtils.divisionLine(String.format("  len:%s, limit:%s", len, limit));
                this.array = StringUtils.randomIntArray(len, 0, limit);
                int failTimes = this.execSingle();
                if (failTimes > 0){
                    return true;
                }
            }
            return false;
        }
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class ChainInfo{
        /**
         * 执行次数
         */
        private int times;
        /**
         * 数据量 长度
         */
        private int len;
        /**
         * 数据范围
         */
        private int limit;
        /**
         * 随机长度限定  true表示在 0-len内随机
         */
        private boolean randomLen;
        /**
         * 随机范围限定  true 表示在 0-limit内随机
         */
        private boolean randomLimit;


    }


}
