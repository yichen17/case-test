package com.yichen.casetest.utils.validator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/3/22 17:16
 * @describe 正则校验项信息
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegexCheckItem {

    private String pattern;
    private String desc;

}
