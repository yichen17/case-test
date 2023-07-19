package com.yichen.casetest.test.pointcuts;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Qiuxinchao
 * @date 2023/7/19 14:31
 * @describe
 */
@Retention(RetentionPolicy.RUNTIME)
 @interface CB {

    String address() default "";

    float distance() default 0;

}
