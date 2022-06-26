package com.yichen.casetest.problem.bot;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.util.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/14 14:20
 * @describe 定制解密工具类
 */
@Slf4j
public class CustomDecryptUtils {

    /**
     * form 数据拆分 逗号
     */
    private static final Character COMMA = ',';
    /**
     * form 数据拆分 等号
     */
    private static final String EQUAL = "=";
    /**
     * form 数据拆分 左大括号
     */
    private static final Character LEFT_BRACKET = '{';
    /**
     * form 数据拆分  右大括号
     */
    private static final Character RIGHT_BRACKET = '}';
    /**
     * 加解密方式 aes
     */
    public static final String AES = "aes";
    /**
     * 加解密方式 rsa
     */
    public static final String RSA = "rsa";
    /**
     * 基础类型转换   将 String 转为基本类型
     * key = 基本类    value = 转换方法 => parse
     */
    public static final Map<Class<?>, String> BASIC_CONVERT  = new HashMap<>(8);

    static {
        BASIC_CONVERT.put(Double.class,"parseDouble");
        BASIC_CONVERT.put(Short.class,"parseShort");
        BASIC_CONVERT.put(Long.class,"parseLong");
        BASIC_CONVERT.put(Integer.class,"parseInt");
        BASIC_CONVERT.put(Byte.class,"parseByte");
        BASIC_CONVERT.put(Float.class,"parseFloat");
    }


    protected static boolean isJsonDecrypt(String decryptType){
        return DecryptConstants.JSON.equals(decryptType);
    }

    protected static boolean isRsaEncrypt(String key){
        return DecryptEnum.H5.getKey().equals(key);
    }

    /**
     * TODO 最好区分开  JSON格式解密 或者 FORM格式解密
     * 将加密后的数据解密，同时填充或替换原有请求入参
     * @param encryptData 请求入参
     * @param key  解密key
     * @param decryptType 解密方式 json or form
     * @return 解密成功或者不需要解密-true    其他-false
     */
    public static Map<String,Object> getDecryptMap(String encryptData, String key, String decryptType){

        if (StringUtils.isEmpty(encryptData) || StringUtils.isEmpty(key) || StringUtils.isEmpty(decryptType)){
            log.error("缺少解密参数 待解密数据 {}, 解密key {}, 加密方式 {}", encryptData, key, decryptType);
        }

        if (log.isDebugEnabled()) {
            log.debug("待解密数据 {}, 解密key {}, 加密方式 {}", encryptData, key, decryptType);
        }
        // 用解密key 解密
        String decryptData = null;
        try {
            if (isRsaEncrypt(key)){
                decryptData = decryptDataRas(encryptData, key);
                // 可能入参存在中文，统一 url 解码
                decryptData = URLDecoder.decode(decryptData, "utf-8");
            }
            else {
                decryptData = decryptDataAes(encryptData, key);
            }
            if (log.isDebugEnabled()){
                log.debug("解密后的数据 {}",decryptData);
            }
        }
        catch (Exception e){
            log.error("数据解密出错 待解密数据 {}, 解密key {}, 加密方式 {} error {}", encryptData, key, decryptType, e.getMessage(),e);
            return null;
        }
        // 将解密的数据封装成 map
        Map<String,Object> decodeData = null;
        try {
            if (isJsonDecrypt(decryptType)){
                decodeData = getDesData(decryptData);
            }
            else {
                decodeData = convertFormDataToMap(decryptData);
            }
        }
        catch (Exception e){
            log.error("将解密后的数据转为 map 异常. error {}", e.getMessage(), e);
        }
        return decodeData;
    }
    /**
     * 四要素解密数据
     * @param encryptedInfo 加密数据
     * @return 解密后的 Map 数据
     */
    public static Map<String,Object> getDesData(String encryptedInfo) throws Exception {
        return JSON.parseObject(encryptedInfo, Map.class);
    }

    /**
     * 将加密数据解密
     * @param encryptedInfo 原加密数据
     * @param key  解密key
     * @return 解密后的数据
     */
    public static String decryptDataAes(String encryptedInfo, String key)  throws Exception{
        return new String(Method.decryptecb_aes(Common.cryptoCipher(encryptedInfo),
                Common.hex2byte(key)));
    }

    /**
     * rsa 解密数据
     * @param encryptInfo 解密数据
     * @param key 解密key
     * @return 解密后的字符串
     */
    public static String decryptDataRas(String encryptInfo, String key) throws Exception{
        byte[] plaintext = RsaUtilsForH5.decryptByPrivateKey(Base64.decodeBase64(encryptInfo), key);
        return URLDecoder.decode(new String(plaintext),"utf8");
    }

    /**
     * TODO  数组形式待验证
     * 将表单格式数据转为 map形式，单层
     * @param formData 原表单数据
     * @return map转义结果
     */
    public static  Map<String,Object> convertFormDataToMap(String formData) throws Exception{
        Map<String,Object> result = new HashMap<>(16);
        List<String> items = commaSplit(formData);

        for(String item : items){
            // 前置校验  => 数据为空   => 理论上不存在
            if (StringUtils.isEmpty(item)){
                continue;
            }
            // 不能根据 = 解密   =>  原因：数据中可能就有等号 => 示例：数据通过 Base64加密
            int position = item.indexOf(EQUAL);
            // 表示没有value
            if ((position == -1) || (position == item.length() -1)){
                result.put(item.substring(0, item.length() - 1).trim(),"");
            }
            else {
                // 正常格式  A=b
                result.put(item.substring(0,position).trim(),item.substring(position+1));
            }
        }
        return result;
    }

    /**
     * 表单数据按项切分，逗号
     * @param formData 表单数据
     * @return 节分后的map数据
     */
    public static  List<String> commaSplit(String formData){
        List<String> result = new ArrayList<>(16);
        Stack<Integer> stack = new Stack<>();
        int start=0,i;
        // 栈相对偏移
        int deviation = 0;
        for(i=0; i<formData.length(); i++){
            if (i == 0 && formData.charAt(i) == LEFT_BRACKET){
                deviation++;
            }
            if (formData.charAt(i) == LEFT_BRACKET ){
                stack.push(i);
                continue;
            }
            if (formData.charAt(i) == RIGHT_BRACKET){
                stack.pop();
            }
            if (formData.charAt(i) == COMMA && stack.size() == deviation){
                if (start == 0 && deviation == 1){
                    result.add(formData.substring(start+1,i));
                }
                else {
                    result.add(formData.substring(start,i));
                }
                start = i+1;
            }
        }
        if (deviation == 1){
            // 只有一个字段的场景
            if (start == 0){
                result.add(formData.substring(start+1, formData.length()-1));
            }
            else {
                result.add(formData.substring(start,formData.length()-1));
            }
        }
        else {
            result.add(formData.substring(start));
        }
        return result;
    }

}
