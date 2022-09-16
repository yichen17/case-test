package com.yichen.casetest.bugtest.fastjson;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/16 11:14
 * @describe 没用工具类导致内存泄漏 https://blog.csdn.net/mall4j/article/details/116650730
 */
@Slf4j
public class FastJsonTest2 {

    public static void main(String[] args) {
//        int i = 10000;
//        while(i > 0){
//            String result = HttpUtil.get("http://localhost:8088/test/get");
//            log.info("结果 {}", result);
//            i--;
//        }

        Map<String,String> map = new HashMap<>(4);
        map.put("name", "yichen");
        map.put("age", "18");
        for (int i=0; i<10; i++){
            System.out.println(JSON.toJSONString(map));
        }

    }

}
