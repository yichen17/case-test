package com.yichen.casetest.test;

import com.alibaba.fastjson.JSON;
import com.yichen.casetest.model.JsonDto;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        List<String> food=new LinkedList<>();
        food.add("红烧肉");
        food.add("白菜豆腐");
        dto.setFoods(food);
        System.out.println(JSON.toJSONString(dto));
    }
}
