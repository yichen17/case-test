package com.yichen.casetest.test.fastjon;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/21 16:42
 * @describe fastjson 序列化会 判断方法可用性
 */
public class StringSerializerTest {

    public static void main(String[] args) {
        UserString userString = new UserString();
        userString.setAddress("海底两万里");
        System.out.println(JSONObject.toJSONString(userString));
    }

}
