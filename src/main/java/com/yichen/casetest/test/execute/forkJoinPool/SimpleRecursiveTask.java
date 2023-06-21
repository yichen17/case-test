package com.yichen.casetest.test.execute.forkJoinPool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Qiuxinchao
 * @date 2023/6/21 9:51
 * @describe 简单任务
 */
@Slf4j
 class SimpleRecursiveTask extends RecursiveTask<Integer> {

    public static Integer arr[];

    private static final int THRESHOLD = 20;
    private  int left;
    private  int right;

    public SimpleRecursiveTask(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    protected Integer compute() {
        if (left - right + 1 > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .reduce(0, Integer::sum);
        } else {
            return processing();
        }
    }

    private Collection<SimpleRecursiveTask> createSubtasks() {
        List<SimpleRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new SimpleRecursiveTask(0, (left + right) >> 1));
        dividedTasks.add(new SimpleRecursiveTask((left + right) >> 1 + 1, right));
        return dividedTasks;
    }

    private Integer processing() {
        if (left > right){
            return 0;
        }
        int result = 0;
        for (int i=left; i<=right; i++){
//            if (arr[i] % 15 > 7){
//                result += arr[i];
//            }
            result += arr[i];
        }
        return result;
    }

}
