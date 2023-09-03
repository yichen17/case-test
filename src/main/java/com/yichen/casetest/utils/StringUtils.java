package com.yichen.casetest.utils;

import com.yichen.casetest.constants.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/17 20:22
 * @describe 字符串工具类
 */
@Slf4j
public class StringUtils {

    private final  static  String ALPHA_NUMBER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static void divisionLine(){
        log.info("======================================||============================================");
    }

    public static void divisionLine(String desc){
        log.warn(
                "======================================{}============================================", desc);
    }

    public static void print(Object object){
        log.info("==> {}", FastJsonUtils.toJson(object));
    }

    /**
     * 从大小写英文字母和数字中构造随机字符串
     * @param len 随机字符串长度
     * @return 构造的随机字符串
     */
    public static String randomString(int len){
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<len; i++){
            builder.append(ALPHA_NUMBER.charAt((int)(Math.random() * ALPHA_NUMBER.length())));
        }
        return  builder.toString();
    }

    /**
     * 构造1到 {@code range} 的乱序list
     * @param range
     * @return
     */
    public static List<Integer> randomList(int range){
        List<Integer> list = new ArrayList<>();
        for (int i=1; i<=range; i++){
            list.add(random.nextInt(i), i);
        }
        return list;
    }


    public static int[] randomArray(int range){
        List<Integer> list = randomList(range);
        int[] result = new int[list.size()];
        for (int i=0; i<list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 构建随机不重复数字，
     * @return
     */
    public static int[] randomNoRepeat(int len, int start, int end){
        int[] result = new int[len];
        Set<Integer> set = new HashSet<>();
        int i=0;
        while (i<len){
            int r = random.nextInt(end-start) + start;
            if (set.add(r)){
                result[i++] = r;
            }
        }
        Arrays.sort(result);
        printIntArray(result);
        return result;
    }

    public static int randomNotIn(int[] data, int start, int end){
        Set<Integer> set = new HashSet<>();
        for (int item : data){
            set.add(item);
        }
        while (true){
            int r = random.nextInt(end-start) + start;
            if (!set.contains(r)){
                return r;
            }
        }
    }


    private static final String[] SPECIAL_CITY = {"北京市", "上海市", "天津市", "重庆市"};
    private static final Pattern PROVINCE_REGEX = Pattern.compile("(?<province>[^自治区]+自治区|[^省]+省|[^市]+市)");
    private static final Pattern CITY_REGEX = Pattern.compile("(?<city>[^辖区]+辖区|[^盟]+盟|[^自治州]+自治州|[^地区]+地区|[^市]+市|.+区划)");
    /**
     * 区正则，区只需要截取一个
     */
    private static final Pattern DISTRICT_REGEX = Pattern.compile("(?<district>[^县]+县|[^旗]+旗|[^区]+区)");

    /**
     * 参考 https://blog.csdn.net/weixin_43703157/article/details/87340629
     * @param addr 待解析地址
     *                replaceAll  避免 OCR 识别出来的数据重复
     * @return
     */
    public static Map<String,String> getAddress(String addr){
        Map<String,String> map = new HashMap(8);
        String city =null;
        String province = null;
        String district=null;
        Matcher m1 = PROVINCE_REGEX.matcher(addr);
        while (m1.find()){
            province = m1.group("province");
            // 如果有市这个关键字，且非4个直辖市，忽略
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(province) && province.contains("市") && !Arrays.asList(SPECIAL_CITY).contains(province)){
                province = "";
                break;
            }
            if(province != null){
                addr=addr.replaceAll(province,"");
                break;
            }
        }
        Matcher m2 = CITY_REGEX.matcher(addr);
        while (m2.find()){
            city = m2.group("city");
            if (city != null){
                addr=addr.replaceAll(city,"");
                break;
            }
        }
        Matcher m3 = DISTRICT_REGEX.matcher(addr);
        while (m3.find()){
            district = m3.group("district");
            if (district != null){
                addr=addr.replaceAll(district,"");
                break;
            }
        }
        map.put("province",province);
        map.put("city",city);
        map.put("district",district);
        map.put("address",addr);
        return map;
    }

    private static final String[] PROVINCES = {"安徽省","贵州省","湖南省","辽宁省","陕西省","新疆维吾尔自治区","澳门特别行政区","河北省","广东省","甘肃省","吉林省","上海市","福建省","香港特别行政区","云南省","天津市","重庆市","河南省","广西壮族自治区","黑龙江省","江西省","江苏省","青海省","北京市","台湾省","西藏自治区","内蒙古自治区","湖北省","四川省","山东省","海南省","浙江省","宁夏回族自治区","山西省"};

    /**
     * 校验  是否是省字段
     * @param province 校验的省
     * @return
     */
    public static boolean isProvince(String province){
        return Arrays.asList(PROVINCES).contains(province);
    }

    /**
     * 校验地址是否符合要求   一般而言身份证地址只包含中文和数字
     * @param address 待校验的地址
     * @return true-校验通过  false-校验不通过
     */
    private static final String ADDRESS_CHECK = "^[0-9\u4e00-\u9fa5-()a-zA-Z]*$";
    public static boolean validAddress(String address){
        return address.matches(ADDRESS_CHECK);
    }


    public static boolean containsEmpty(String ...param){
        return Arrays.stream(param).anyMatch(org.apache.commons.lang.StringUtils::isEmpty);
    }

    /**
     * 随机长度字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append((char) result);
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append((char) result);
                    break;
                case 2:
                    sb.append(new Random().nextInt(10));
                    break;
            }
        }
        return sb.toString();
    }


    private static Random random = new Random();

    public static int[] randomIntArray(int length, int low, int high){
        if (length <= 0 ){
            return new int[0];
        }
        if (high < low){
            return new int[0];
        }
        int[] result = new int[length];
        int width = high - low;
        for (int i=0; i<length; i++){
            result[i] = random.nextInt(width) + low;
        }
        if (log.isDebugEnabled()){
            log.debug("randomIntArray生成数据结果{}", Arrays.stream(result)
                    .mapToObj(String::valueOf).collect(Collectors.joining(",")));
        }
        return result;
    }

    public static Integer[] randomIntArrayWrapper(int length, int low, int high){
        if (length <= 0 ){
            return new Integer[0];
        }
        if (high < low){
            return new Integer[0];
        }
        Integer[] result = new Integer[length];
        int width = high - low;
        for (int i=0; i<length; i++){
            result[i] = random.nextInt(width) + low;
        }
        if (log.isDebugEnabled()){
            log.debug("randomIntArray生成数据结果{}", Arrays.stream(result)
                    .map(String::valueOf).collect(Collectors.joining(",")));
        }
        return result;
    }

    public static Double[]  randomDoubleArray(int length){
        if (length <= 0){
            return new Double[0];
        }
        Double[] result = new Double[length];
        for (int i=0; i<length; i++){
            result[i] = random.nextDouble();
        }
        return result;
    }

    public  static <T extends Number> boolean checkOrder(T[] nums, boolean ascendOrder){
        if (Objects.isNull(nums) || nums.length < 2){
            return true;
        }
        // 升序
        if (ascendOrder){
            for (int i=0; i<nums.length-1; i++){
                if (!checkGreaterEqualsThan(nums[i+1], nums[i])){
                    log.warn("{}不符合条件{} {}", "升序", nums[i], nums[i+1]);
                    return false;
                }
            }
            return true;
        }
        // 降序
        for (int i=0; i<nums.length-1; i++){
            if (!checkGreaterEqualsThan(nums[i], nums[i+1])){
                log.warn("{}不符合条件{} {}", "降序", nums[i], nums[i+1]);
                return false;
            }
        }
        return true;
    }

    private static boolean checkGreaterEqualsThan(Number a, Number b){
        if (a instanceof Integer){
            return (Integer)a - (Integer) b >= 0;
        }
        else if (a instanceof Long){
            return (Long)a - (Long) b >= 0;
        }

        throw  new RuntimeException("比较泛型Number未配置规则");
    }

    private static boolean checkGreaterThan(Number a, Number b){
        if (a instanceof Integer){
            return (Integer)a - (Integer) b > 0;
        }
        else if (a instanceof Long){
            return (Long)a - (Long) b > 0;
        }

        throw  new RuntimeException("比较泛型Number未配置规则");
    }

    public static void printIntArray(int[] array){
        if (Objects.isNull(array)){
            return;
        }
        if (array.length == 0){
            log.info("[]");
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int item : array){
            builder.append(item).append(CommonConstants.COMMA);
        }
        builder.replace(builder.length()-1, builder.length(), "]");
        log.info("{}", builder);
    }

    public static int[][] constructEdges(int start, int limit, int size, boolean selfCycle, boolean greater){
        int[][] result = new int[size][2];
        for (int i=0; i<size; i++){
            int from = random.nextInt(limit)+start, to=-1;
            if (selfCycle){
                to = random.nextInt(limit)+start;
            }
            else if (greater){
                while (to == -1 || to < from){
                    to = random.nextInt(limit)+start;
                }
            }
            else {
                while (to == -1 || to == from) {
                    to = random.nextInt(limit)+start;
                }
            }
            result[i][0] = from;
            result[i][1] = to;
        }
        arrayTwoDimensionPrintNormal(result);
        return result;
    }

    public static boolean compareArray(int[] aArray, int[] bArray){
        if (Objects.isNull(aArray) && Objects.isNull(bArray)){
            return true;
        }
        if (Objects.isNull(aArray) || Objects.isNull(bArray) || aArray.length != bArray.length){
            return false;
        }
        for (int i=0; i<aArray.length; i++){
            if (aArray[i] != bArray[i]){
                return false;
            }
        }
        return true;
    }


    public static <T> void rowPrintArray(T[] array){
        for(T item : array){
            log.info("{}", item);
        }
    }

    public static <T> void rowPrintList(List<T> items){
        for(T item : items){
            log.info("{}", item);
        }
    }

    public static <T> String printArray(T[] array){
        return printArray(array, ",", "", "");
    }

    public static <T> String printArray(T[] array, String delimiter, String prefix, String suffix){
        StringBuilder builder = new StringBuilder();
        builder.append(prefix);
        for (Object o  : array){
            builder.append(String.valueOf(o)).append(delimiter);
        }
        builder.replace(builder.length()-delimiter.length(), builder.length(), suffix);
        return builder.toString();
    }


    public static String[] randomArrayInSpecificCharacters(char[] chars, int vertical, int horizontal){
        String[] result = new String[vertical];
        for (int i=0; i<vertical; i++){
            result[i] = randomArrayInSpecificCharacters(chars, horizontal);
        }
        log.debug("randomArrayInSpecificCharacters => {}", printArray(result, "\",\"", "\"", "\""));
        return result;
    }

    public static String randomArrayInSpecificCharacters(char[] chars,  int horizontal){
        StringBuilder builder = new StringBuilder();
        for (int j=0; j<horizontal; j++){
            builder.append(chars[random.nextInt(chars.length)]);
        }
        return builder.toString();
    }

    /**
     * 构造指定长度字符串，内容都是字符c
     */
    public static String constructStr(int size, char c){
        StringBuilder builder = new StringBuilder();
        while (size > 0){
            size--;
            builder.append(c);
        }
        return builder.toString();
    }

    public static int getBitCount(int n){
        int result = 1;
        while (n >= 10){
            result++;
            n /= 10;
        }
        return result;
    }


    public static String batchReplaceBracket(String s, String[] oldBracket, String[] newBracket){
        if (Objects.isNull(oldBracket) || Objects.isNull(newBracket) || oldBracket.length != newBracket.length){
            log.warn("batchReplaceBracket 替换入参不符合条件");
            return s;
        }
        for (int i=0; i<oldBracket.length; i++){
            s = s.replace(oldBracket[i], newBracket[i]);
        }
        return s;
    }

    public static String batchReplaceBracket(String s){
        return batchReplaceBracket(s, new String[]{"[", "]"}, new String[]{"{", "}"});
    }


    private static final Pattern convert2ArrayPattern = Pattern.compile("\\[(.*?)\\]");
    public static int[][] convert2Array(String s){
        List<List<Integer>> list = new ArrayList<>();
        Matcher matcher = convert2ArrayPattern.matcher(s);
        while (matcher.find()){
             String item = matcher.group(1);
            item = item.replaceAll("^\\[|\\]$", "");
            if (org.apache.commons.lang3.StringUtils.isEmpty(item)){
                break;
            }
            list.add(Arrays.stream(item.split(CommonConstants.COMMA)).map(Integer::valueOf).collect(Collectors.toList()));
        }
        if (CollectionUtils.isEmpty(list) || CollectionUtils.isEmpty(list.get(0))){
            return new int[0][0];
        }
        int[][] result = new int[list.size()][list.get(0).size()];
        for (int i=0; i<list.size(); i++){
            for(int j=0; j<list.get(0).size(); j++){
                result[i][j] = list.get(i).get(j);
            }
        }
        return result;
    }

    public static void arrayTwoDimensionPrint(int[][] array){
        for (int[] item : array){
            StringBuilder s = new StringBuilder();
            s.append("[");
            for (int tt : item){
                s.append(tt).append(",");
            }
            s.insert(s.length()-1, "]");
            log.info("{}", s);
        }
        log.info("arrayTwoDimensionPrint end\n");
    }

    public static void arrayTwoDimensionPrintNormal(int[][] array){
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int[] item : array){
            s.append("[");
            for (int tt : item){
                s.append(tt).append(",");
            }
            s.insert(s.length()-1, "]");
        }
        s.replace(s.length()-1, s.length(), "]");
        log.info("leetcode version print {}\n", s);
    }

    /**
     * 随机字母异位词
     */
    public static String[] randomAnagram(String s, int size){
        String[] result = new String[size];
        char[] chars = s.toCharArray();
        int times;
        for(int i=0; i<size; i++){
            times = random.nextInt(500);
            result[i] = new String(randomSwap(chars, times));
        }
        return result;
    }

    public static String randomSwap(char[] chars, int times){
        while (times>0){
            times--;
            randomSwapCharArray(chars);
        }
        return new String(chars);
    }

    private static void randomSwapCharArray(char[] chars){
        int a,b,len=chars.length;
        char temp;
        a = random.nextInt(len);
        b = random.nextInt(len);
        temp = chars[a];
        chars[a]= chars[b];
        chars[b] = temp;
    }

    /**
     * 标准化json字符串  有时候存在内部是个json的String，前后多了双引号，实例：
     *      {"address":"{\"city\":\"上海市\",\"name\":\"上海\",\"province\":\"浙江省\"}","age":18}
     * @param str
     * @return
     */
    public static String normalizeJsonStr(String str){
        str = str.replace("\"{", "{")
                .replace("}\"", "}")
                .replace("\"[", "[")
                .replace("]\"", "]")
                .replace("\\\"", "\"");
        return str;
    }


    public static void main(String[] args) {
        Address address = Address.builder().name("上海").province("浙江省").city("上海市").build();
        TestJson testJson = TestJson.builder().age(18).address(FastJsonUtils.toJson(address)).build();
        System.out.println(FastJsonUtils.toJson(testJson));

        String s = FastJsonUtils.toJson(testJson);
        System.out.println(normalizeJsonStr(s));

        int[] array = new int[2];
        System.out.println(array.getClass());
        System.out.println(array.getClass().getComponentType());

        s = "[[1,2],[2,4],[1,3],[2,3],[2,1]]";
        convert2Array(s);

    }

    // 测试序列化

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class TestJson{
        private Integer age;
        private String address;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Address{
        private String name;
        private String province;
        private String city;
    }






















}
