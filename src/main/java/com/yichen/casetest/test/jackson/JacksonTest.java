package com.yichen.casetest.test.jackson;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yichen.casetest.model.dto.JacksonDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/27 14:46
 * @describe jackson 测试
 */
@Slf4j
public class JacksonTest {

    public static void main(String[] args) {
        String json = "{\"name\":\"yichen\",\"age\":18,\"sex\":\"boy\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        //  禁用未知属性不匹配异常
//        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            JacksonDTO jacksonDTO = objectMapper.readValue(json, JacksonDTO.class);
            System.out.println(JSON.toJSONString(jacksonDTO));
        }
        catch (Exception e){
            log.error("错误信息 {}", e.getMessage() ,e);
        }

        String s = "-ss";
        String[] split = s.split("-");
        System.out.println(split[1]);

    }

}
