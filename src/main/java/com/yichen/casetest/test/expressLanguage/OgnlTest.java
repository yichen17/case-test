package com.yichen.casetest.test.expressLanguage;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import ognl.Ognl;
import ognl.OgnlContext;

import java.math.BigDecimal;

/**
 * @author Qiuxinchao
 * @date 2023/7/29 10:04
 * @describe ognl表达式相关测试
 *      官方文档
 *          =>  https://commons.apache.org/proper/commons-ognl/language-guide.html
 *
 *      案例参考
 *          =>  https://blog.csdn.net/lisheng19870305/article/details/110087222
 *
 *      Spel表达式文档：
 *          https://docs.spring.io/spring-framework/reference/core/expressions.html
 */
@Slf4j
public class OgnlTest {
    public static void main(String[] args) throws Exception{
        simpleCase();
        StringUtils.divisionLine();
        expressCase();
        StringUtils.divisionLine();
        simpleCaseOther();
        StringUtils.divisionLine();
    }


    private static void expressCase() throws Exception{
        OgnlContext ognlContext = new OgnlContext(null, null, new CustomizeMemberAccess(true));
//        Object ognl = Ognl.parseExpression("@Math@floor(10.9)");
        Object ognl = Ognl.parseExpression("@@floor(10.9)");
        log.info("{}", Ognl.getValue(ognl, ognlContext, ognlContext.getRoot()));
    }

    private static void simpleCase() throws Exception{
        OgnlContext ognlContext = new OgnlContext(null, null, new CustomizeMemberAccess(true));
        OgnlTestDto ognlTestDto = OgnlTestDto.builder().age(18).height(new BigDecimal("60.2")).name("奕晨").phone("13626558390").build();
        ognlContext.setRoot(ognlTestDto);
        Object express = Ognl.parseExpression("name");
        Object value = Ognl.getValue(express, ognlContext, ognlContext.getRoot());
        log.info("{}", value);
        express = Ognl.parseExpression("#age < 20 ? 2*#age : #age");
        value = Ognl.getValue(express, ognlContext, ognlContext.getRoot());
        log.info("{}", value);
    }


    /**
     * 此路不通
     * @throws Exception
     */
    private static void simpleCaseOther()throws Exception{
//        OgnlTestDto ognlTestDto = OgnlTestDto.builder().age(18).height(new BigDecimal("60.2")).name("奕晨").phone("13626558390").build();
//        log.info("{}", Ognl.getValue("name", ognlTestDto));
    }

}
