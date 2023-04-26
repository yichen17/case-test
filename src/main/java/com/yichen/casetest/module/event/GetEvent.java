package com.yichen.casetest.module.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qiuxinchao
 * @date 2023/4/26 16:02
 * @describe
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetEvent {
    private String desc;
}
