package com.yichen.casetest.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Qiuxinchao
 * @date 2023/8/28 14:15
 * @describe main方法相关工具类
 */
public class MainUtils {

    /**
     * 设定日志级别
     * @param level
     * {@link Level}
     */
    public static void setLoggerLevel(Level level){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        List<Logger> loggerList = loggerContext.getLoggerList();
        loggerList.forEach(logger -> {
            logger.setLevel(level);
        });
    }

}
