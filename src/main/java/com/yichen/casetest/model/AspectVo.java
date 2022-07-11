package com.yichen.casetest.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/24 14:00
 * @describe 切面测试数据 vo
 */
@Data
public class AspectVo<T> {
//    @NotEmpty(message = "名字不能为空")
//    private String name;

    @NotEmpty(message = "地址不能为空")
    private String address;

    private Integer age;

    private String encrypt;

    private T data;

}
