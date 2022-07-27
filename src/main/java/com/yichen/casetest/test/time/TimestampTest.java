package com.yichen.casetest.test.time;

import com.alibaba.fastjson.JSON;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/21 11:47
 * @describe
 */
public class TimestampTest {

    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        System.out.println(timestamp.getTime());
        Map<String,Object>  map = new HashMap<>(4);
        map.put("timestamp", timestamp);
        System.out.println(JSON.toJSONString(map));
    }

}
