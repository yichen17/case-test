package com.yichen.casetest.utils.validator.annotation;

import java.lang.annotation.*;

/**
 * 字段校验
 * @author banyu
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldVaild {
    /**
     * 校验未通过的描述
     */
    String desc();

    /**
     * 校验方式
     */
    Type type();

    /**
     * 字段重用，
     * 当 {@code regex} 指代正则表达式样式
     * 当 {@code length} 指代数组或者集合的长度
     * 当 {@code setSpecify} 指代具体数据集内容
     * 当 {@code setEnum} 指代枚举的具体字段get方法名称
     * @return
     */
    String data() default "";

    /**
     * 类型西
     *
     * @return
     */
    Class dataClass() default String.class;

    enum Type{
        regex, setSpecify, length, notNull, setEnum;
    }

}
