package com.yichen.casetest.test.execute.forkJoinPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveAction;



/**
 * @author Qiuxinchao
 * @date 2023/6/19 15:34
 * @describe
 */
@Slf4j
class Applyer extends RecursiveAction {

    final Double[] array;
    final int lo, hi;
    double result;
    Applyer next; // keeps track of right-hand-side tasks
    Applyer(Double[] array, int lo, int hi, Applyer next) {
        this.array = array; this.lo = lo; this.hi = hi;
        this.next = next;
    }

    Applyer(Double[] array){
        this(array, 0 ,array.length, null);
    }

    double atLeaf(int l, int h) {
        double sum = 0;
        for (int i = l; i < h; ++i) // perform leftmost base step
            sum += array[i] * array[i];
        return sum;
    }

    @Override
    protected void compute() {
        int l = lo;
        int h = hi;
        Applyer right = null;
        while (h - l > 1 && getSurplusQueuedTaskCount() <= 3) {
            int mid = (l + h) >>> 1;
            right = new Applyer(array, mid, h, right);
            right.fork();
            h = mid;
        }
        double sum = atLeaf(l, h);
        while (right != null) {
            if (right.tryUnfork()) // directly calculate if not stolen
                sum += right.atLeaf(right.lo, right.hi);
            else {
                right.join();
                sum += right.result;
            }
            right = right.next;
        }
        result = sum;
    }

}
