package com.yichen.casetest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/12 14:19
 * @describe
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonTestDTO {

//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    private String name;

    private Long id;


}
