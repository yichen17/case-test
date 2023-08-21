package com.yichen.casetest.test.execute.forkJoinPool;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

/**
 * @author Qiuxinchao
 * @date 2023/6/19 14:22
 * @describe jdk forkJoinPool 相关研究
 */
@Slf4j
public class ForkJoinPoolTest {

    public static void main(String[] args) throws Exception {
        sumArray();
    }

    /**
     * 简单计算总和单线程处理有优势
     *      如果是重用数组的话，明显有优势，而且随着数据量的增加差异更加明显
     *      如果是同时拷贝数据，一方面是占用空间会变大，另一方面是创建对象会有开销，此时不如线性计算
     *      对比使用线程池，快了一些  但是走ForkJoinPool貌似没有使用common线程池
     *      1000万四倍，1亿直接相加 forkJoinPool占比百分之五，很明显了。  底层逻辑串行改成并行了，加快了处理
     */
    private static void sumArray() throws Exception{

        log.info("{}", Runtime.getRuntime().availableProcessors());
        //  位运算和加减优先级
        int left = 0, right = 103;
        log.info("{} {} {}", (left + right) >> 1, ((left + right) >> 1)+ 1, (left + right) >> 1 + 1);
        System.gc();

        Integer[] params = StringUtils.randomIntArrayWrapper(1300000000, 0, 2);
//        Integer[] params = StringUtils.randomIntArray(100000, 0, 1000);

//        forkJoinPool(params);
        serial(params);
//        threadPool(params);
        threadPool2(params);

        log.info("shutdown destroy");
        ForkJoinPool.commonPool().shutdown();
        ThreadPoolExec.executorService.shutdown();
    }

    private static void forkJoinPool(Integer[] params)throws Exception{
        SimpleRecursiveTask.arr = params;
        long start = System.nanoTime();
        SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(0, params.length - 1);
        Integer result = simpleRecursiveTask.compute();
        log.info("执行耗时 {} {} => forkJoinPool", System.nanoTime() - start, result);
    }

    private static void serial(Integer[] params) throws Exception{
        System.gc();
        long start = System.nanoTime();
        int result = Stream.of(params)
//                .filter(p -> p % 15 > 7)
                .reduce(0, Integer::sum);
        log.info("执行耗时 {} {} => Stream Serial Cal", System.nanoTime() - start, result);
    }

    private static void threadPool(Integer[] params) throws Exception{
        ThreadPoolExec.arr = params;
        System.gc();
        long start = System.nanoTime();
        int result = new ThreadPoolExec(0, params.length - 1).cal();
        log.info("执行耗时 {} {} => thread pool", System.nanoTime() - start, result);
    }


    /**
     * 自旋等待处理
     * @param params
     * @throws Exception
     */
    private static void threadPool2(Integer[] params) throws Exception{
        ThreadPoolExec.arr = params;
        System.gc();
        long start = System.nanoTime();
        new ThreadPoolExec(0, params.length - 1).cal2();
        Thread.sleep(300);
        while (ThreadPoolExec.times.get() != 0){
//            log.info("please wait ...");
//            Thread.sleep(1000);
        }
        log.info("执行耗时 {} {} => thread pool", System.nanoTime() - start, ThreadPoolExec.result.get());
    }

    private static void demoTest(){
        String str = "kuangshayitiaojie123456789" ;
        CustomRecursiveAction customRecursiveAction = new CustomRecursiveAction(str);
        customRecursiveAction.compute();
        StringUtils.divisionLine();
        Integer[] params = StringUtils.randomIntArrayWrapper(200, 0, 27);
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
