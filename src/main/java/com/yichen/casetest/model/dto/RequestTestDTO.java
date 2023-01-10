package com.yichen.casetest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Qiuxinchao
 * @date 2023/1/10 9:54
 * @describe 请求入参测试 dto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestTestDTO implements Serializable {
    private String name;
    private String realName;
}
