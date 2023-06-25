package com.yichen.casetest.test.leetcode;

import com.yichen.casetest.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
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
        // 快排实现
        Integer[] array = StringUtils.randomIntArray(999, 0, 100);
        quickSort(array);
        log.info("quick result {} \n{}", StringUtils.printArray(array),
                StringUtils.checkOrder(array, true));
        StringUtils.divisionLine();
        // 归并排序
        array = StringUtils.randomIntArray(999, 0, 100);
        LinkedListNode root = LinkedListNode.buildListedList(array);
        log.info("linked list node old path {}", LinkedListNode.printPath(root));
        linkedListMergeSort(root);
        log.info("linked list node sort path {}", LinkedListNode.printPath(root));
        StringUtils.divisionLine();
        // 冒泡排序
        array = StringUtils.randomIntArray(999, 0, 100);
        bubbleSort(array);
        log.info("bubble sort result {} \n{}", StringUtils.printArray(array),
                StringUtils.checkOrder(array, true));
        StringUtils.divisionLine();
        // 简单选择排序
        array = StringUtils.randomIntArray(999, 0, 100);
        simpleSelectionSort(array);
        log.info("simple select sort result {} \n{}", StringUtils.printArray(array),
                StringUtils.checkOrder(array, true));
        StringUtils.divisionLine();
        // 插入排序
        array = StringUtils.randomIntArray(999, 0, 100);
        insertionSort(array);
        log.info("insert sort result {} \n{}", StringUtils.printArray(array),
                StringUtils.checkOrder(array, true));
        StringUtils.divisionLine();
        // 插入排序 实现优化
        array = StringUtils.randomIntArray(999, 0, 100);
        insertionSortOptimize(array);
        log.info("insert sort optimize result {} \n{}", StringUtils.printArray(array),
                StringUtils.checkOrder(array, true));
        StringUtils.divisionLine();
        // 计数排序
        array = StringUtils.randomIntArray(9999, 0, 100);
        countingSort(array);
        log.info("count sort result {} \n{}", StringUtils.printArray(array),
                StringUtils.checkOrder(array, true));
        StringUtils.divisionLine();
        // 希尔排序
        array = StringUtils.randomIntArray(999, 0, 100);
        shellSort(array);
        log.info("shell sort result {} \n{}", StringUtils.printArray(array),
                StringUtils.checkOrder(array, true));
        StringUtils.divisionLine();
    }





    // 计数排序

    private static void countingSort(Integer[] data){
        if (Objects.isNull(data) || data.length < 2){
            return;
        }
        Integer max = Arrays.stream(data).max(Integer::compare).get();
        Integer min = Arrays.stream(data).min(Integer::compare).get();
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

    private static void bucketSort(Integer[] data){

    }

    // 基数排序

    private static void radixSort(Integer[] data){

    }

    // 堆排序

    private static void heapSort(Integer[] data){

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


    //=============  归并排序  链表

    private static LinkedListNode linkedListMergeSort(LinkedListNode head){
        LinkedListNode pos = getMid(head);
        if (pos == head){
            return head;
        }
        LinkedListNode next = pos.next;
        pos.next = null;
        LinkedListNode left = linkedListMergeSort(head);
        LinkedListNode right = linkedListMergeSort(next);
        return merge(left, right);
    }


    private static LinkedListNode merge(LinkedListNode left, LinkedListNode right){
        if (left == null){
            return right;
        }
        if (right == null){
            return left;
        }
        LinkedListNode head = new LinkedListNode();
        LinkedListNode pos = head;
        LinkedListNode p = left, q = right;
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


    private static LinkedListNode getMid(LinkedListNode root){
        if (root == null || root.next == null){
            return root;
        }
        LinkedListNode p = root, q = root;
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


    private static ChainExec buildChainExec(int times){
        ChainExec chainExec = new ChainExec(times);
        chainExec.initRoot(ChainItem.builder().desc("quick sort").consumer(SortTest::quickSort).build());
        // 归并忽略   不同的demo
        chainExec.appendTail(ChainItem.builder().desc("insert sort").consumer(SortTest::insertionSort).build())
                .appendTail(ChainItem.builder().desc("simple select sort").consumer(SortTest::simpleSelectionSort).build())
                .appendTail(ChainItem.builder().desc("shell sort").consumer(SortTest::shellSort).build())
                .appendTail(ChainItem.builder().desc("count sort").consumer(SortTest::countingSort).build())
                .appendTail(ChainItem.builder().desc("bubble sort").consumer(SortTest::bubbleSort).build())
//                .appendTail(ChainItem.builder().desc("heap sort").consumer(SortTest::heapSort).build())
//                .appendTail(ChainItem.builder().desc("bucket sort").consumer(SortTest::bucketSort).build())
//                .appendTail(ChainItem.builder().desc("radix sort").consumer(SortTest::radixSort).build())
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
        private int times;


        public ChainExec(int times) {
            this.times = times;
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
            while (pos != null){
                Integer[] newItem = Arrays.copyOf(array, array.length);
                start = System.nanoTime();
                pos.getConsumer().accept(newItem);
                log.info("执行耗时 {} => {}",System.nanoTime() - start, pos.getDesc());
                boolean flag = StringUtils.checkOrder(newItem, true);
                log.info("是否有序 {}", flag);
                result += flag ? 0 : 1;
                pos = pos.next;
            }
            return result;
        }

        public boolean exec(){
            for (int i=0; i<times; i++){
                // 一次轮换变更数据源
                this.array = StringUtils.randomIntArray(999, 0, 100);
                int failTimes = this.execSingle();
                if (failTimes > 0){
                    return true;
                }
            }
            return false;
        }


    }


}
