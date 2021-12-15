package com.yichen.casetest.test;

import com.alibaba.fastjson.JSON;
import com.yichen.casetest.model.JsonDto;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/14 17:22
 * @describe 测试 fastjson 相关实现
 */
public class JsonTest {
    public static void main(String[] args) {
        // 测试对象转字符串
        JsonDto dto=new JsonDto();
        dto.setAddress("一条街");
        dto.setName("");
        System.out.println(JSON.toJSONString(dto));
    }
}
