package com.yichen.casetest.test.execute.forkJoinPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveAction;


/**
 * @author Qiuxinchao
 * @date 2023/6/19 15:01
 * @describe 递增实现
 */
@Slf4j
 class IncrementTask extends RecursiveAction {

    final Integer[] array; final int lo, hi;
    IncrementTask(Integer[] array, int lo, int hi) {
        this.array = array; this.lo = lo; this.hi = hi;
    }

    IncrementTask(Integer[] array) {
        this(array, 0, array.length);
    }

    static final int THRESHOLD = 5;

    @Override
    protected void compute() {
        if (hi - lo < THRESHOLD) {
            for (int i = lo; i < hi; ++i)
                array[i]++;
        }
        else {
            int mid = (lo + hi) >>> 1;
            invokeAll(new IncrementTask(array, lo, mid),
                    new IncrementTask(array, mid, hi));
        }
    }

}
