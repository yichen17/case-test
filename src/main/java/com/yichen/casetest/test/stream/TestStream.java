package com.yichen.casetest.test.stream;

import com.yichen.casetest.utils.FastJsonUtils;
import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/5/17 12:34
 * @describe 测试流相关
 */
@Slf4j
public class TestStream {

    public static void main(String[] args) {
//        ioTest();
        StringUtils.divisionLine();
        streamGroupBy();
        StringUtils.divisionLine();
        typeTransfer();
    }

    private static void streamGroupBy(){
        List<String> list = new ArrayList<>();
        list.add("shanliang");
        list.add("yichen");;
        list.add("yichen");
        Map<String, List<String>> groupByMap = list.stream().collect(Collectors.groupingBy(p -> p));
        groupByMap.forEach((key, value) -> log.info("key => {}, value => {}", key, FastJsonUtils.toJson(value)));
    }

    private static void typeTransfer(){
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(13);
        int[] result = list.stream().mapToInt(Integer::intValue).toArray();
        log.info(Stream.of(result).map(String::valueOf).collect(Collectors.joining(",")));
    }


    private static void ioTest(){
        try {
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream();
            out.connect(in);
            out.write(74);
            System.out.println("using read() : " + (char)in.read());
            out.write(75);
            System.out.println("using read() : " + (char)in.read());
            out.write(79);
            System.out.println("using read() : " + (char)in.read());
        }
        catch (Exception e){
            log.error("{}", e.getMessage(), e);
        }
    }

}
