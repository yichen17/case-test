package com.yichen.casetest.test.basetype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qiuxinchao
 * @date 2023/1/30 17:28
 * @describe 内部静态类测试
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InnerStaticClassTest {

    private String desc;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InnerClass{
        private String name;
    }

}
