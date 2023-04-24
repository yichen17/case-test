package com.yichen.casetest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/14 17:22
 * @describe
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonDto {
    private String name;
    private Integer age;
    private String address;
    private Date date;

    /**
     * 类型 0-date  1-时间戳 long
     */
    private Integer flag;

    List<String> foods;

    private BigDecimal price;

}
