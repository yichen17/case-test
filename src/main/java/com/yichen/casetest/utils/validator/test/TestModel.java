package com.yichen.casetest.utils.validator.test;

import com.yichen.casetest.utils.validator.annotation.FieldVaild;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/3/21 15:22
 * @describe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestModel {

    @FieldVaild(desc = "年纪不是指定值", type = FieldVaild.Type.setSpecify, data = "15,16,18", dataClass = Integer.class)
    private int age;

    @FieldVaild(desc = "地址长度最短为5", type = FieldVaild.Type.length, data = "5")
    private String address;

    @FieldVaild(desc = "姓名不能为空", type = FieldVaild.Type.notNull)
    private String name;

    @FieldVaild(desc = "学号只能是数字", type = FieldVaild.Type.regex, data = "^\\d*$")
    private String no;

    @FieldVaild(desc = "枚举测试未通过", type = FieldVaild.Type.setEnum, data = "getTttttt", dataClass = TestEnum.class)
    private String testEnum;


}
