package com.yichen.casetest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/7 21:41
 * @describe
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CountDownLatchDTO {

    private Long id;

    private String name;

}
