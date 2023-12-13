package com.yichen.casetest.test.ognl;

import com.yichen.casetest.test.ognl.base.CustomizeMemberAccess;
import com.yichen.casetest.utils.StringUtils;
import ognl.Ognl;
import ognl.OgnlContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/12/12 20:53
 * @describe
 */
public class SimpleTest {

    public static void main(String[] args) throws Exception{
        simpleCase();
        StringUtils.divisionLine();
        listCase();
        StringUtils.divisionLine();
        mapCase();
        StringUtils.divisionLine();
        listFlattenCase();
    }

    private static void simpleCase() throws Exception{
//        String[] items = new String[]{"shanliang", "banyu", "yichen"};
        int[] items = new int[]{1,2,3};
        OgnlContext context = new OgnlContext(null, null, new CustomizeMemberAccess(true));
        context.setRoot(items);
        Object ognl = Ognl.parseExpression("#this.{? 1 < #this}");
        System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
    }

    /**
     * 集合投影，类似stream的map操作
     * @throws Exception
     */
    private static void listCase() throws Exception {
        OgnlContext context = new OgnlContext(null, null, new CustomizeMemberAccess(true));
        List<Person> persons = new ArrayList<>();
        persons.add(Person.builder().address("浙江").name("shanliang").age(18).build());
        persons.add(Person.builder().address("北京").name("yichen").age(24).build());
        persons.add(Person.builder().address("山东").name("banyu").age(30).build());
        context.setRoot(persons);
        Object ognl = Ognl.parseExpression("#this.{name}");
        System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
        System.out.println(persons.stream().map(Person::getName).collect(Collectors.toList()));
    }

    /**
     * 对map结果处理，keys和values是转换成了数组输出
     * @throws Exception
     */
    private static void mapCase() throws Exception{
        OgnlContext context = new OgnlContext(null, null, new CustomizeMemberAccess(true));
        Map<Integer, Person> map = new HashMap<>();
        map.put(1, Person.builder().address("浙江").name("shanliang").age(18).build());
        map.put(2, Person.builder().address("北京").name("yichen").age(24).build());
        map.put(3, Person.builder().address("山东").name("banyu").age(30).build());
        context.setRoot(map);
        Object ognl = Ognl.parseExpression("#this.keys");
        System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
        ognl = Ognl.parseExpression("#this.values");
        System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
        ognl = Ognl.parseExpression("#this.values()");
        System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
        ognl = Ognl.parseExpression("#this.values().{name}");
        System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
        ognl = Ognl.parseExpression("#this.values().{name}.{? #this == 'shanliang'}");
        System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
        // 为什么这么写不行呢？
        ognl = Ognl.parseExpression("#this.values().{name}.{? 'shanliang'.equals(#this)}");
        System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
        ognl = Ognl.parseExpression("#this.values().{? 'shanliang'.equals(#this.name)}");
        System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
    }

    private static void mapListCase(){

    }

    private static void propertyCase(){

    }

    private static void listFlattenCase() throws Exception{
        OgnlContext context = new OgnlContext(null, null, new CustomizeMemberAccess(true));
        Map<Integer, List<Person>> map = new HashMap<>();
        List<Person> list = new ArrayList<>();
        list.add(Person.builder().address("浙江").name("shanliang").age(18).build());
        map.put(1, list);
        list = new ArrayList<>();
        list.add(Person.builder().address("北京").name("yichen").age(24).build());
        map.put(2, list);
        list = new ArrayList<>();
        list.add(Person.builder().address("山东").name("banyu").age(30).build());
        map.put(3, list);
        context.setRoot(map);
        Object ognl = Ognl.parseExpression("#all = new java.util.ArrayList(), #this.values.{#all.addAll(#this)}");
        System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
//        ognl = Ognl.parseExpression("#this.values().flatten()");
//        System.out.println(Ognl.getValue(ognl, context, context.getRoot()));
    }



}
