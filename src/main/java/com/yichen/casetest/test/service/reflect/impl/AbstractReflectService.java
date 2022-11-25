package com.yichen.casetest.test.service.reflect.impl;

import com.yichen.casetest.test.service.reflect.ReflectService;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/24 17:31
 * @describe
 */
public abstract class AbstractReflectService implements ReflectService {



    public abstract String addressFrom();

    boolean rainNow(){
        return false;
    }

}
