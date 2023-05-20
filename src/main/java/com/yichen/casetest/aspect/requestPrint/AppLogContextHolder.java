package com.yichen.casetest.aspect.requestPrint;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/5/20 16:07
 * @describe
 */
public class AppLogContextHolder {
    private static final ThreadLocal<AppLog> TL = ThreadLocal.withInitial(AppLog::new);

    public static AppLog get() {
        // 获取线程上下文的日志对象
        return TL.get();
    }

    public static void remove() {
        // 移除线程上下文的日志对象
        TL.remove();
    }

}
