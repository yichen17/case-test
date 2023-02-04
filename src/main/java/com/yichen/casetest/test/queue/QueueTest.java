package com.yichen.casetest.test.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/2/2 21:15
 * @describe 队列测试
 */
@Slf4j
public class QueueTest {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("shanliang");
        log.info("remove {} {} {}", queue.remove(), queue.isEmpty(), Math.pow(2, 3));
    }

}
