package com.yichen.casetest.test.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qiuxinchao
 * @date 2023/2/13 14:55
 * @describe
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String name;
    private String longitude;
    private String latitude;

    public String getRunName(){
        return "run name => " + getName();
    }

}
