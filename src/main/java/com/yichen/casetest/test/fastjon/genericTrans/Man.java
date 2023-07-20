package com.yichen.casetest.test.fastjon.genericTrans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Qiuxinchao
 * @date 2023/7/20 14:25
 * @describe
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Man<T> {

    private String name;
    private String age;
    private T goods;

    @Data
    public static class GoodsInfo{
        private String goodsName;
        private BigDecimal goodsPrice;
        private String goodsWeight;
    }

}
