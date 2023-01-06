package com.yichen.casetest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Qiuxinchao
 * @date 2023/1/6 14:36
 * @describe jackson 展示测试
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JacksonTest implements Serializable {

    private Date createTime;

    private String name;

}
