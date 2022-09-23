package com.yichen.casetest.model.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/22 10:18
 * @describe 地址dto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String county;

}
