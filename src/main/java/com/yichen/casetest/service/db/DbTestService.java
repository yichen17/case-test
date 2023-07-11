package com.yichen.casetest.service.db;

/**
 * @author Qiuxinchao
 * @date 2023/7/11 17:56
 * @describe
 */
public interface DbTestService {

    void consistentFail(String key);

    void consistentFailTrans(String key);

}
