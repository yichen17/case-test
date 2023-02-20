package com.yichen.casetest.bugtest.mybatis;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Qiuxinchao
 * @date 2023/2/20 17:23
 * @describe
 */
@AllArgsConstructor
@Getter
public enum TimeDimensionEnum {

    RECENTLY_SEVEN_DAY("recentlySevenDay","create_time >= DATE_SUB(now(), INTERVAL 7 DAY)", "group by DATE_FORMAT(create_time,'%Y-%m-%d')",  "最近7天", "'%Y-%m-%d'"),
    RECENTLY_TWO_WEEKS("recentlyTwoWeeks","create_time >= DATE_SUB(now(), INTERVAL 14 DAY) ", "group by DATE_FORMAT(create_time,'%Y-%m-%d')", "最近2周", "'%Y-%m-%d'"),
    RECENTLY_THIRTY_DAY("recentlyThirtyDay", "create_time >= DATE_SUB(now(), INTERVAL 30 DAY) ","group by DATE_FORMAT(create_time,'%Y-%m-%d')", "最近30天", "'%Y-%m-%d'"),
    LAST_MONTH("lastMonth","create_time >= DATE_FORMAT( DATE_SUB(CURDATE(), INTERVAL 1 MONTH), '%Y-%m-01 00:00:00') and create_time < DATE_FORMAT( LAST_DAY(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)), '%Y-%m-%d 23:59:59' ",
            "group by DATE_FORMAT(create_time,'%Y-%m-%d')", "上个月", "'%Y-%m-%d'"),
    RECENTLY_THREE_MONTH("recentlyThreeMonth","create_time >= DATE_SUB(now(), INTERVAL 3 MONTH) ", "group by DATE_FORMAT(create_time,'%Y-%m')", "最近3个月", "'%Y-%m'"),
    RECENTLY_TWELVE_MONTH("recentlyTwelveMonth", "create_time >= DATE_SUB(now(), INTERVAL 12 MONTH) ","group by DATE_FORMAT(create_time,'%Y-%m')", "最近12个月", "'%Y-%m'"),
    THIS_WEEK("thisWeek", "create_time >= DATE_FORMAT( DATE_SUB( CURDATE(), INTERVAL WEEKDAY( CURDATE()) DAY ), '%Y-%m-%d %H:%i:%s')", "", "本周", ""),
    ;
    /**
     * 前端交互 code
     */
    private String code;
    /**
     * 时间限制
     */
    private String timeLimit;
    /**
     * 分组限制
     */
    private String groupByStr;
    /**
     * 描述
     */
    private String desc;
    /**
     * 日期格式
     */
    private String dateFormat;

    public static TimeDimensionEnum getEnum(String code){
        for (TimeDimensionEnum item : TimeDimensionEnum.values()){
            if (item.getCode().equals(code)){
                return item;
            }
        }
        throw new RuntimeException("时间维度枚举转换失败，未匹配到");
    }

}
