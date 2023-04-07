package com.yichen.casetest.mq.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qiuxinchao
 * @date 2023/4/7 14:16
 * @describe kafka测试dto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {

    private String name;
    private String age;

}
