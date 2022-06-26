package com.yichen.casetest.problem.bot;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/14 14:02
 * @describe 解密常量
 */
public class DecryptConstants {

    public static final String IOS = "ios";

    public static final String ANDROID = "android";

    public static final String H5 = "h5";

    public static final String JSON = "json";

    public static final String FORM = "form";

    public static final String DEVICE_TYPE = "deviceType";

    /**
     * 默认加密字段
     */
    public static final String[] DEFAULT_DEVICE_TYPE = new String[] {"encryptedData", "encryptedInfo", "encryptedInfoForH5"};

    public static final String BAR = "-";

    /**
     * form 数据拆分 逗号
     */
    public static final String COMMA = ",";

    /**
     * 用户在 请求头中没传  deviceType 时 兜底
     */
    public static Map<String, String> DEFAULT_DEVICE_FIELD_MAP = new HashMap<>(16);

    static {
        DEFAULT_DEVICE_FIELD_MAP.put("h5", "encryptedInfoForH5,encryptedData");
        DEFAULT_DEVICE_FIELD_MAP.put("ios", "encryptedInfo");
        DEFAULT_DEVICE_FIELD_MAP.put("android", "encryptedInfo");
    }

}
