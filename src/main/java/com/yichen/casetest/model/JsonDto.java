package com.yichen.casetest.model;

import lombok.Data;

import java.util.List;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/14 17:22
 * @describe
 */
@Data
public class JsonDto {
    private String name;
    private Integer age;
    private String address;
    List<String> foods;

    @Data
    public static class Inner{
        private String time = "2022-02-23";
    }
}
