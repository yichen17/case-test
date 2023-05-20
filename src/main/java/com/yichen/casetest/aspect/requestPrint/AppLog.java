package com.yichen.casetest.aspect.requestPrint;

import lombok.Data;

import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/5/20 16:06
 * @describe
 */
@Data
public class AppLog {
    /** 请求路径 */
    private String path;
    /** 请求param */
    private Map<String, String[]> parameters;
    /** 请求体 */
    private Object reqBody;
    /** 返回体 */
    private Object respBody;
}
