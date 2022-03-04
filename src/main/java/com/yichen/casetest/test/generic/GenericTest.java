//package com.yichen.casetest.test.generic;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author Qiuxinchao
// * @version 1.0
// * @date 2022/2/23 15:51
// * @describe 泛型测试   目标 一个接口既可以返回 String 也可以返回 List<String>
// */
//public class GenericTest {
//
//    private static List<String> names= Arrays.asList("shanliang","yichen","banyu");
//
//    public static void main(String[] args) {
//        System.out.println((String)getData(true));
//        System.out.println((List<String>)getData(false));
//    }
//
//    public static  <T> T getData(Boolean isOne){
//        if(isOne){
//            if(T instanceof  String){
//                return (T)names.get(0);
//            }
//
//        }
//        return (T)names;
//    }
//
//
//}
