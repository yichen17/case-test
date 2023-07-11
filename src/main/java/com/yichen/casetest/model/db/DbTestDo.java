package com.yichen.casetest.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qiuxinchao
 * @date 2023/7/11 18:01
 * @describe
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DbTestDo {


    private Long id;
    private String key;
    private String val;

}
