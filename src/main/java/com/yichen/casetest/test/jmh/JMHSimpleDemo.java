package com.yichen.casetest.test.jmh;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Qiuxinchao
 * @date 2023/8/28 10:08
 * @describe 基准测试简单demo
 *      参考文档：https://blog.51cto.com/u_9869701/3697767
 *
 */
@Slf4j
public class JMHSimpleDemo {

    private static final Random random = new Random();



    private static void compareMaxSizeSlices(JMHSimpleDemo dq){
        // 执行时长测试
        long totalCostA = 0, costA, totalCostB=0, costB, lessA=0, lessB=0, same=0;
        for (int i=0; i<10000; i++){
            int[] slices = StringUtils.randomIntArray(3 + 3 * random.nextInt(187), 1, 1000);
            System.gc();
            costA = System.currentTimeMillis();
            dq.maxSizeSlicesOther(slices);
            costA = System.currentTimeMillis() - costA;
            System.gc();
            costB = System.currentTimeMillis();
            dq.maxSizeSlices(slices);
            costB = System.currentTimeMillis() - costB;
            if (costA>costB){
                lessB++;
            }
            else if (costA<costB){
                lessA++;
            }
            else {
                same++;
            }
            totalCostB += costB;
            totalCostA += costA;
        }
        log.info("10000次，A耗时短{}次，B耗时短{}次,相同耗时{}，A总耗时{},B总耗时{}", lessA, lessB, same, totalCostA, totalCostB);
    }

    public int maxSizeSlices(int[] slices) {
        return Math.max(this.maxSizeSlices(slices, 0, slices.length-2), this.maxSizeSlices(slices, 1, slices.length-1));
    }

    // 数据比对，100次预热，other耗时10⁻¹⁰   normal耗时10⁻⁹   的确是other快一些

    @Benchmark
    public void otherTest(){
        for (int i=0; i<10000; i++){
            int[] slices = StringUtils.randomIntArray(3 + 3 * random.nextInt(187), 1, 1000);
            this.maxSizeSlicesOther(slices);
        }
    }

//    @Benchmark
    public void normalTest(){
        for (int i=0; i<10000; i++){
            int[] slices = StringUtils.randomIntArray(3 + 3 * random.nextInt(187), 1, 1000);
            this.maxSizeSlices(slices);
        }
    }

    public int maxSizeSlicesOther(int[] slices) {
        int len = slices.length, times = len/3;
        int[][][] dp = new int[len][times+1][2];
        int from = 0, to = len-2;
        // 节点初始化
        dp[1][1][0] = slices[from];
        dp[2][1][0] = Math.max(slices[from], slices[from+1]);
        dp[1][1][1] = slices[from+1];
        dp[2][1][1] = Math.max(slices[from+1], slices[from+2]);
        for (int i=3; i<=to-from+1; i++){
            for(int k=1; k<i&&k<=times; k++){
                dp[i][k][0] = Math.max(dp[i-1][k][0], dp[i-2][k-1][0] + slices[from-1+i]);
                dp[i][k][1] = Math.max(dp[i-1][k][1], dp[i-2][k-1][1] + slices[from+i]);
            }
        }
        return Math.max(dp[to-from+1][times][0], dp[to-from+1][times][1]);
    }

    private int maxSizeSlices(int[] slices, int from, int to){
        int len = slices.length, times = len/3;
        int[][] dp = new int[len][times+1];
        // 节点初始化
        dp[1][1] = slices[from];
        dp[2][1] = Math.max(slices[from], slices[from+1]);
        for (int i=3; i<=to-from+1; i++){
            for(int k=1; k<i&&k<=times; k++){
                dp[i][k] = Math.max(dp[i-1][k], dp[i-2][k-1] + slices[from-1+i]);
            }
        }
        return dp[to-from+1][times];
    }

//    @Benchmark
    public void simpleTest(){
        for (int i=0; i<10000; i++){

        }
    }

    public static void main(String[] args) throws Exception{

        Options opt = new OptionsBuilder().include(JMHSimpleDemo.class.getSimpleName())
                .forks(1)
                .timeUnit(TimeUnit.NANOSECONDS)
//                .warmupIterations(100)
                .build();
        new Runner(opt).run();
    }

}
