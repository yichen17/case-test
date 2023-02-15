package com.yichen.casetest.service.mapStruct;

import lombok.Data;

import java.util.Date;

/**
 * @author Qiuxinchao
 * @date 2023/2/15 13:35
 * @describe
 */
@Data
public class JsonTestDto {

    private Date createTime;


    private String name;

    private JsonTestId id;

    private String sex;

    private String desc;

}
