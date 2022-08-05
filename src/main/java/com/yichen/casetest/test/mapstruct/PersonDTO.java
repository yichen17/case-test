package com.yichen.casetest.test.mapstruct;

import lombok.Data;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/5 16:58
 * @describe
 */
@Data
public class PersonDTO {

    String describe;

    private Long id;

    private String personName;

    private String age;

    private String source;

    private String height;

}
