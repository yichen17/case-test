package com.yichen.casetest.test.jvm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qiuxinchao
 * @date 2023/5/16 15:48
 * @describe oom相关测试
 */@Slf4j
public class OomTest {

    public static void main(String[] args) {
        oomPrintInfo();
    }

    /**
     * 测试oom后是否会生成 dump 文件
     * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=f:/oom.hprof -Xms10m -Xmx10m
     */
    private static void oomPrintInfo(){
        List<Integer> list = new ArrayList<>();
        int i = 1;
        while(true){
            list.add(i);
        }
    }

}
