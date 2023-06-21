package com.yichen.casetest.test.execute.forkJoinPool;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Qiuxinchao
 * @date 2023/6/19 14:22
 * @describe jdk forkJoinPool 相关研究
 */
@Slf4j
public class ForkJoinPoolTest {

    public static void main(String[] args) {
        sumArray();
    }

    /**
     * 简单计算总和单线程处理有优势
     *      如果是重用数组的话，明显有优势，而且随着数据量的增加差异更加明显
     *      如果是同时拷贝数据，一方面是占用空间会变大，另一方面是创建对象会有开销，此时不如线性计算
     *      1000万四倍，1亿直接相加 forkJoinPool占比百分之五，很明显了。  底层逻辑串行改成并行了，加快了处理
     */
    private static void sumArray(){
        Integer[] params = StringUtils.randomIntArray(1000000000, 0, 2);
        SimpleRecursiveTask.arr = params;
        long start = System.nanoTime();
        SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(0, params.length - 1);
//        CustomRecursiveTask customRecursiveTask = new CustomRecursiveTask(params);
        Integer compute = simpleRecursiveTask.compute();
        log.info("执行耗时 {} {} => forkJoinPool", System.nanoTime() - start, compute);
        System.gc();
        start = System.nanoTime();
        int result = Stream.of(params)
//                .filter(p -> p % 15 > 7)
                .reduce(0, Integer::sum);
        log.info("执行耗时 {} {} => Stream Serial Cal", System.nanoTime() - start, result);
    }

    private static void demoTest(){
        String str = "kuangshayitiaojie123456789" ;
        CustomRecursiveAction customRecursiveAction = new CustomRecursiveAction(str);
        customRecursiveAction.compute();
        StringUtils.divisionLine();
        Integer[] params = StringUtils.randomIntArray(200, 0, 27);
        long onceCal = Arrays.stream(params).filter(p -> p > 10 && p < 27).map(a -> a * 10).count();
        CustomRecursiveTask task = new CustomRecursiveTask(params);
        log.info("task 执行结果 {} {}", task.compute(), onceCal);
        StringUtils.divisionLine();
        SortTask sortTask = new SortTask(params);
        sortTask.compute();
        log.info("排序后的数组 {}", StringUtils.printArray(params));
        StringUtils.divisionLine();
        IncrementTask incrementTask = new IncrementTask(params);
        incrementTask.compute();
        log.info("执行增加逻辑后的数组 {}", StringUtils.printArray(params));
        StringUtils.divisionLine();
        Double[] douArrays = StringUtils.randomDoubleArray(200);
        Applyer applyer = new Applyer(douArrays);
        applyer.compute();
        log.info("applicant {} {}", StringUtils.printArray(douArrays), applyer.result);
        StringUtils.divisionLine();
    }


}
