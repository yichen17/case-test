package com.yichen.casetest.utils;

import com.yichen.casetest.config.position.LocationPropertiesListenerConfig;
import com.yichen.casetest.model.utils.AddressDto;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/22 10:16
 * @describe 地址工具
 */
public class AddressUtils {


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
     * 获取身份证地址信息
     * @param certId 身份证号
     * @param address 身份证上居住地址，一般ocr识别
     * @return
     */
    public static AddressDto getAddressInfo(String certId, String address){
        // 问题点 1、户籍地址变了身份证切出来的和实际的不一致 2、orc识别出来的初始数据有问题(1-异常字符% 2-识别字符丢失 原浙江省 识别后江省)  3、身份证上的地址有挺多以市开头的，没有省字段
        // 切分户籍地址 存放省市区
        Map<String, String> maps = StringUtils.getAddress(address);
        // 1、先尝试切分获取数据
        String province = maps.get("province");
        String city = maps.get("city");
        String county = maps.get("district");
        // 2、切分后没数据 从身份证号上获取
        province = org.apache.commons.lang3.StringUtils.isEmpty(province) ?
                LocationPropertiesListenerConfig.getProperty(certId.substring(0,2) + "0000") : province;
        city = org.apache.commons.lang3.StringUtils.isEmpty(city) ?
                LocationPropertiesListenerConfig.getProperty(certId.substring(0,4) + "00") : city;
        county = org.apache.commons.lang3.StringUtils.isEmpty(county) ?
                LocationPropertiesListenerConfig.getProperty(certId.substring(0,6)) : county;
        // 3、省强校验
        if (!isProvince(county)){
            throw new RuntimeException("不是正规的省");
        }
        // 4、还没有，低位高补
        city = org.apache.commons.lang3.StringUtils.isNotEmpty(city) ? city : province;
        county = org.apache.commons.lang3.StringUtils.isNotEmpty(county) ? county : city;
        return AddressDto.builder().province(province).city(city).county(county).build();
    }

}
