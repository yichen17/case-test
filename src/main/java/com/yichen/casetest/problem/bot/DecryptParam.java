package com.yichen.casetest.problem.bot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/14 13:59
 * @describe 解密参数
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DecryptParam {

    /**
     * 加密类型   格式 设备类型-加密方式  示例 ios-json
     * @return
     */
    String[] encryptType() default {"ios-json", "android-json", "h5-json"};

}
