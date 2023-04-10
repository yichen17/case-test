package com.yichen.casetest.test.jvm;

import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.omg.SendingContext.RunTime;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/4/10 22:35
 * @describe 运行耗费空间测试
 *    疑问:
 *    1、Stack 和 ArrayDeque 在顺序栈空间大小问题
 *    2、List.Stream.mapToInt(p ->p).toArray()  和 new int[] 然后逐项填充差异，为什么后面的快呢
 */
@Slf4j
public class RunSpaceTest {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long startMem = runtime.freeMemory();
        exec();
        long endMem = runtime.freeMemory();
        log.info("开始=>{},结束=>{},耗费空间{}", startMem, endMem, startMem - endMem);
    }

    private static void exec(){
//        for(int i=0; i<5; i++){
//            multiplyExec();
//        }
        multiplyExec();
    }

    private static void multiplyExec(){
                Stack<int[]> stack = new Stack<>();
//        Deque<int[]> stack = new ArrayDeque<>();
        for(int i=0; i<10000; i++){
            if (stack.isEmpty() || Math.random() > 0.5){
                stack.push(new int[]{i, i});
            }
            else {
                stack.pop();
            }
        }
    }

}
