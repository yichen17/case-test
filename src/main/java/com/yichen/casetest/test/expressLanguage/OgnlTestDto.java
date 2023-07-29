package com.yichen.casetest.test.expressLanguage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Qiuxinchao
 * @date 2023/7/29 10:41
 * @describe
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OgnlTestDto {

    private Integer age;
    private String name;
    private String phone;
    private BigDecimal height;


}
