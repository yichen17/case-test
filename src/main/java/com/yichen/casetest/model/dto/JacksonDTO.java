package com.yichen.casetest.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/27 14:47
 * @describe
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonDTO {
    private String name;
    private Integer age;
}
