package com.yichen.casetest.module.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/10/25 16:40
 * @describe
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EatMsg {
    private BigDecimal foodPrice;
    private String foodName;
}
