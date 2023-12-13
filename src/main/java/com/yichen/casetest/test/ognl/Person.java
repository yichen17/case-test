package com.yichen.casetest.test.ognl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/12/12 20:54
 * @describe
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
 class Person {
    private String name;
    private String address;
    private int age;
}
