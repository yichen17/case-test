package com.yichen.casetest.test.pointcuts;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Qiuxinchao
 * @date 2023/7/19 14:30
 * @describe
 */
//@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
 @interface CA {

    String name() default "yichen";

    int age() default 18;

    double price() default 0.0;

}
