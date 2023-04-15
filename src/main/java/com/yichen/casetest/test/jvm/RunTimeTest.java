package com.yichen.casetest.test.jvm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/4/15 15:52
 * @describe 运行时长测试
 */
@Slf4j
public class RunTimeTest {

    public static void main(String[] args) {
//        stackTest();
//        test();
        arrayDequeTest();
    }

    /**
     * 倒插入 6
     */
    private static void arrayDequeTest(){
        Deque<Integer> stack = new ArrayDeque<>(5);
        stack.push(1);stack.push(6);
        stack.push(2);stack.push(7);
        stack.push(3);stack.push(8);
        stack.push(4);stack.push(9);
        stack.pop();
        stack.push(5);
        stack.push(10);stack.push(11);stack.push(12);stack.push(13);
        stack.push(20);
    }

    /**
     * 默认容量10，大了会扩容
     */
    private static void stackTest(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);stack.push(6);
        stack.push(2);stack.push(7);
        stack.push(3);stack.push(8);
        stack.push(4);stack.push(9);
        stack.push(14);stack.push(19);
        stack.insertElementAt(7421, 10);
        stack.push(15);stack.push(18);
        stack.pop();stack.pop();stack.pop();stack.pop();stack.pop();
        stack.push(5);stack.push(10);

    }


    private static void test(){
        int[] array = new int[]{1,2,3,4};
        System.out.println(array);
        System.out.println(array.length);
        array = Arrays.copyOf(array, 8);
        System.out.println(array);
        System.out.println(array.length);
        array[4] = 5;
        System.arraycopy(array, 0, array, 4, 4);
        System.out.println("666");
    }



}
