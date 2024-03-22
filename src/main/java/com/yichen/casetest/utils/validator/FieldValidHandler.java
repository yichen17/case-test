package com.yichen.casetest.utils.validator;

import com.alibaba.fastjson.JSON;
import com.yichen.casetest.utils.validator.annotation.FieldVaild;
import com.yichen.casetest.utils.validator.model.CheckInfo;
import com.yichen.casetest.utils.validator.model.RegexCheckItem;
import com.yichen.casetest.utils.validator.test.TestModel;
import com.yichen.casetest.utils.validator.utils.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/3/21 14:38
 * @describe 字段校验处理器
 *
 * 已实现：1、单层校验  2、枚举或者自定义校验值 3、判空或者校验长度 4、正则校验
 *
 *
 *
 * 待考虑问题：
 * 1、实体嵌套问题
 * 2、指定值set范围校验，有没有通用的解决方案
 * 3、同字段多个规则校验
 * 4、校验边界：应该是单字段范围，多字段之间的关系应该忽略，或者不应该在这里做
 * 5、
 */
@Slf4j
public class FieldValidHandler {

    private static final Map<String, CheckInfo> notCheckInfo = new HashMap<>();

    private static Map<Class, Map<String, CheckInfo>> cache = new HashMap<>();

    public static Map<String, RegexCheckItem> getRegexItem(Object validClass){
        doInitClassCheckInfo(validClass);
        Map<String, CheckInfo> stringFieldVaildMap = cache.get(validClass.getClass());
        Map<String, RegexCheckItem> result = new HashMap<>();
        for (Map.Entry<String, CheckInfo> entry : stringFieldVaildMap.entrySet()) {
            String pattern = entry.getValue().getRegexPattern();
            if (StringUtils.isBlank(pattern)){
                continue;
            }
            result.put(entry.getKey(), RegexCheckItem.builder().pattern(pattern)
                    .desc(entry.getValue().getFieldVaild().desc()).build());
        }
        return result;
    }

    public static boolean valid(Object validClass){
        Map<String, CheckInfo> stringFieldVaildMap = cache.get(validClass.getClass());
        // 这个类没有配置校验信息，默认校验通过
        if (notCheckInfo.equals(stringFieldVaildMap)){
            return true;
        }
        // 空，构造校验项
        if (stringFieldVaildMap == null){
            stringFieldVaildMap = buildValidItem(validClass);
            cache.put(validClass.getClass(), stringFieldVaildMap);
        }
        // 对每个字段做校验
        return doFieldValid(validClass, stringFieldVaildMap);
    }

    private static void doInitClassCheckInfo(Object validClass){
        Map<String, CheckInfo> stringFieldVaildMap = cache.get(validClass.getClass());
        if (stringFieldVaildMap != null){
            return;
        }
        stringFieldVaildMap = buildValidItem(validClass);
        if (stringFieldVaildMap.isEmpty()){
            stringFieldVaildMap = notCheckInfo;
        }
        cache.put(validClass.getClass(), stringFieldVaildMap);
    }

    private static boolean doFieldValid(Object validClass, Map<String, CheckInfo> stringFieldVaildMap){
        for (Map.Entry<String, CheckInfo> stringFieldVaildEntry : stringFieldVaildMap.entrySet()) {
            Object o;
            try {
                Field declaredField = validClass.getClass().getDeclaredField(stringFieldVaildEntry.getKey());
                declaredField.setAccessible(true);
                o = declaredField.get(validClass);
            }
            catch (Throwable t){
                continue;
            }
            switch (stringFieldVaildEntry.getValue().getFieldVaild().type()){
                case notNull:
                    if (o == null){
                        printInfo(stringFieldVaildEntry.getValue().getFieldVaild().desc());
                        return false;
                    }
                    break;
                case setEnum:
                case setSpecify:
                    if (!stringFieldVaildEntry.getValue().getSet().contains(o)){
                        printInfo(stringFieldVaildEntry.getValue().getFieldVaild().desc());
                        return false;
                    }
                    break;
                case regex:
                    if (!stringFieldVaildEntry.getValue().getPattern().matcher(o.toString()).matches()){
                        printInfo(stringFieldVaildEntry.getValue().getFieldVaild().desc());
                        return false;
                    }
                    break;
                case length:
                    if (o instanceof String && ((String)o).length() < stringFieldVaildEntry.getValue().getLen()){
                        printInfo(stringFieldVaildEntry.getValue().getFieldVaild().desc());
                        return false;
                    }
                    if (o instanceof Collection && ((Collection)o).size() < stringFieldVaildEntry.getValue().getLen()){
                        printInfo(stringFieldVaildEntry.getValue().getFieldVaild().desc());
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    private static void printInfo(String msg){
        System.out.println(msg);
    }

    private static Map<String, CheckInfo> buildValidItem(Object validClass) {
        Map<String, CheckInfo> result = new HashMap<>();
        Field[] declaredFields = validClass.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            FieldVaild annotation = declaredField.getAnnotation(FieldVaild.class);
            if (annotation == null) {
                continue;
            }
            CheckInfo checkInfo;
            switch (annotation.type()){
                case length:
                    checkInfo = CheckInfo.builder().fieldVaild(annotation)
                            .len(Integer.parseInt(annotation.data())).build();
                    break;
                case setSpecify:
                    checkInfo = CheckInfo.builder().fieldVaild(annotation)
                            .set(ConvertUtils.baseType2Set(annotation.data(), annotation.dataClass()))
                            .build();
                    break;
                case regex:
                    checkInfo = CheckInfo.builder().fieldVaild(annotation)
                            .pattern(Pattern.compile(annotation.data()))
                            .build();
                    break;
                case setEnum:
                    try {
                        checkInfo = CheckInfo.builder().fieldVaild(annotation)
                                .set(ConvertUtils.enum2Set(annotation.data(), annotation.dataClass()))
                                .build();
                    }
                    catch (Exception e){
                        log.error("类{}字段{}解析规则异常{}", validClass.getClass().getName(), declaredField.getName(), e.getMessage(), e);
                        continue;
                    }
                    break;
                case notNull:
                default:
                    checkInfo = CheckInfo.builder().fieldVaild(annotation)
                            .build();
                    break;
            }
            result.put(declaredField.getName(), checkInfo);
        }
        return result;
    }

    public static void main(String[] args) {
        TestModel ksyt = TestModel.builder().age(18)
                .name("banyu")
                .no("12321323")

                .address("ksssssyt").build();
        System.out.println(FieldValidHandler.valid(ksyt));

        System.out.println(JSON.toJSONString(getRegexItem(ksyt)));


    }





}
