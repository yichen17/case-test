package com.yichen.casetest.test.execute.forkJoinPool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Qiuxinchao
 * @date 2023/6/19 14:36
 * @describe 执行计算有结果值
 */
@Slf4j
 class CustomRecursiveTask extends RecursiveTask<Long> {
    private Integer[] arr;

    private static final int THRESHOLD = 20;

    public CustomRecursiveTask(Integer[] arr) {
        this.arr = arr;
    }

    @Override
    protected Long compute() {
        if (arr.length > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToLong(ForkJoinTask::join)
                    .count();
        } else {
            return processing(arr);
        }
    }

    private Collection<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CustomRecursiveTask(
                Arrays.copyOfRange(arr, 0, arr.length / 2)));
        dividedTasks.add(new CustomRecursiveTask(
                Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        return dividedTasks;
    }

    private Long processing(Integer[] arr) {
        long val = Arrays.stream(arr)
                .filter(a -> a > 10 && a < 27)
                .map(a -> a * 10)
                .count();
        log.info("{}执行结果{}", Thread.currentThread().getName(), val);
        return val;
    }
}
