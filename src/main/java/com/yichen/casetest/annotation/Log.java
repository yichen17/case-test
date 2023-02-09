package com.yichen.casetest.annotation;

import java.lang.annotation.*;

/**
 * @author Qiuxinchao
 * @date 2023/2/9 14:13
 * @describe
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    public String title() default "";

}
