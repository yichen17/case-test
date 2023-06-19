package com.yichen.casetest.test.execute.forkJoinPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * @author Qiuxinchao
 * @date 2023/6/19 14:59
 * @describe 数组排序实现
 */
@Slf4j
 class SortTask extends RecursiveAction {
    final Integer[] array; final int lo, hi;
    public SortTask(Integer[] array, int lo, int hi) {
        this.array = array; this.lo = lo; this.hi = hi;
    }
    public SortTask(Integer[] array) { this(array, 0, array.length); }

    @Override
    protected void compute() {
        if (hi - lo < THRESHOLD)
            sortSequentially(lo, hi);
        else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new SortTask(array, lo, mid),
                    new SortTask(array, mid, hi));
            merge(lo, mid, hi);
        }
    }
    static final int THRESHOLD = 1000;
    void sortSequentially(int lo, int hi) {
        Arrays.sort(array, lo, hi);
    }


    /**
     * 数组归并
     * @param lo
     * @param mid
     * @param hi
     */
    void merge(int lo, int mid, int hi) {
        Integer[] buf = Arrays.copyOfRange(array, lo, mid);
        for (int i = 0, j = lo, k = mid; i < buf.length; j++)
            array[j] = (k == hi || buf[i] < array[k]) ?
                    buf[i++] : array[k++];
    }
}
