package com.yichen.casetest.test.al;

import com.yichen.casetest.utils.StringUtils;
import org.apache.commons.collections.map.HashedMap;

import java.util.*;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/9/16 18:04
 * @describe 代码规约相关测试
 */
public class CodeRegulation {

    public static void main(String[] args) {
        byteMove();
        StringUtils.divisionLine();
        collectionIncompatibleType();
        StringUtils.divisionLine();
        conditionalExpressionNumericPromotion();
        StringUtils.divisionLine();
        containsWrongUse();
        StringUtils.divisionLine();
//        identityHashMapBoxing();
        StringUtils.divisionLine();
        collectionsTest();
    }

    /**
     * random.nextInt() 不是随机分布的，0的概率特殊
     */
    private static void randomTest(){
        Random random = new Random();
        for (int i=0; i<1000; i++){
            if (random.nextInt() < 0){
                System.out.println("touch negative");
            }
        }
        System.out.println("all positive");
    }


    private static void collectionsTest(){
        System.out.println(Collections.nCopies(10, 'a'));
        System.out.println(Collections.nCopies('a', 10));
    }

    /**
     * todo 卧槽，为啥呢？？
     */
    private static void identityHashMapBoxing(){
        Random random = new Random();
        // 不要使用IdentityHashMap
        Map<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i < 100; ++i) {
            int randomInt = random.nextInt();
            hashMap.put(randomInt, String.valueOf(randomInt));
            String value = hashMap.get(randomInt);
            // HashMap使用equals方法进行值比较，不会出现问题
            if (value == null) {
                throw new RuntimeException("Value is null");
            }
        }

        Map<Integer, String> map = new IdentityHashMap<>();

        map.put(1111, "22");
        System.out.println(map.get(1111));
        map.put(1111, "23");
        System.out.println(map.get(1111));


        for (int i = 0; i < 100; ++i) {
            int randomInt = random.nextInt();
            map.put(randomInt, String.valueOf(randomInt));
            String value = map.get(randomInt);
            if (value == null) {
                // 由于包装类的缓存机制，在缓存内的值将返回同一实例，而缓存外的值则会创建一个新的实例之后返回
                throw new RuntimeException("Value is null");
            }
        }

    }

    /**
     * hashTable 或者 ConcurrentHashMap contains() 方法错误使用
     */
    private static void containsWrongUse(){
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("1", "01");
        hashtable.put("2", "02");
        hashtable.put("3", "03");
        System.out.println(hashtable.containsKey("1"));;
        System.out.println(hashtable.containsValue("01"));;
        System.out.println(hashtable.contains("1"));;
        System.out.println(hashtable.contains("01"));;
    }

    private static void byteMove(){
        int a = 111;
        // 为啥等价于没有操作呢。   => 编译器在优化处理，取模操作
        System.out.println(a << 32);
    }

    /**
     * 集合类型问题，例如从map中获取元素，不会检测类型匹配性
     */
    private static void collectionIncompatibleType(){
        Map<Long, String> idToValueMap = new HashedMap(){{
            put(1L, "1");
            put(2L, "2");
            put(3L, "3");
        }};
        System.out.println(idToValueMap.get(1L));
        System.out.println(idToValueMap.get(1));
    }

    /**
     * 条件表达式类型提升  todo 研究
     * https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.25
     */
    private static void conditionalExpressionNumericPromotion(){
        System.out.println(getZeroOnCondition(true).getClass());
        System.out.println(getZeroOnCondition(false).getClass());
    }

    private static Object getZeroOnCondition(boolean flag) {
        return flag ?
                Double.valueOf(0) :
                Integer.valueOf(0);
    }

}
