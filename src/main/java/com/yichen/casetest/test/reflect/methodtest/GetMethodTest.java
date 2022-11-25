package com.yichen.casetest.test.reflect.methodtest;

import com.yichen.casetest.utils.ReflectUtils;
import com.yichen.casetest.utils.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/24 21:47
 * @describe
 */
public class GetMethodTest {

    public static void main(String[] args) {
        StringUtils.print(ReflectUtils.getAllPublicDistinctDeclareMethod(Things.class));
        StringUtils.divisionLine();
        StringUtils.print(ReflectUtils.getAllPublicDistinctDeclareMethod(Fruits.class));
        StringUtils.divisionLine();
        StringUtils.print(ReflectUtils.getAllPublicDistinctDeclareMethod(Apple.class));
        StringUtils.divisionLine();
        StringUtils.print(ReflectUtils.getAllPublicDistinctDeclareMethod(ArtificialApple.class));
        StringUtils.divisionLine();

        StringUtils.print(ReflectUtils.getAllDistinctDeclareMethod(Things.class));
        StringUtils.divisionLine();
        StringUtils.print(ReflectUtils.getAllDistinctDeclareMethod(Fruits.class));
        StringUtils.divisionLine();
        StringUtils.print(ReflectUtils.getAllDistinctDeclareMethod(Apple.class));
        StringUtils.divisionLine();
        StringUtils.print(ReflectUtils.getAllDistinctDeclareMethod(ArtificialApple.class));
        StringUtils.divisionLine();

        StringUtils.print(Arrays.stream(Things.class.getMethods()).map(Method::getName).collect(Collectors.joining(",")));
        StringUtils.divisionLine();
        StringUtils.print(Arrays.stream(Fruits.class.getMethods()).map(Method::getName).collect(Collectors.joining(",")));
        StringUtils.divisionLine();
        StringUtils.print(Arrays.stream(Apple.class.getMethods()).map(Method::getName).collect(Collectors.joining(",")));
        StringUtils.divisionLine();
        StringUtils.print(Arrays.stream(ArtificialApple.class.getMethods()).map(Method::getName).collect(Collectors.joining(",")));
        StringUtils.divisionLine();

        StringUtils.print(Arrays.stream(Things.class.getDeclaredMethods()).map(Method::getName).collect(Collectors.joining(",")));
        StringUtils.divisionLine();
        StringUtils.print(Arrays.stream(Fruits.class.getDeclaredMethods()).map(Method::getName).collect(Collectors.joining(",")));
        StringUtils.divisionLine();
        StringUtils.print(Arrays.stream(Apple.class.getDeclaredMethods()).map(Method::getName).collect(Collectors.joining(",")));
        StringUtils.divisionLine();
        StringUtils.print(Arrays.stream(ArtificialApple.class.getDeclaredMethods()).map(Method::getName).collect(Collectors.joining(",")));
        StringUtils.divisionLine();


    }

}
