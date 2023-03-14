package com.yichen.casetest.test.jvm;

import lombok.Data;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/3/14 21:52
 * @describe jvm相关测试  => 栈上创建对象
 * https://mp.weixin.qq.com/s/YlpsH74uxamXv5BmbJ_5hA
 */
public class AllotOnStackTest {

    /**
     * 栈上分配，标量替换
     * 代码调用了1亿次alloc()，如果是分配到堆上，
     * 大概需要1GB以上堆空间，如果堆空间小于该值，必然会触发GC。
     * 使用如下参数不会发生GC
     * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
     * 使用如下参数都会发生大量GC
     * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
     * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void alloc() {
        User user = new User();
        user.setId(1);
        user.setName("test");
    }

    @Data
    private static class User{
        private Integer id;
        private String name;
    }

}
