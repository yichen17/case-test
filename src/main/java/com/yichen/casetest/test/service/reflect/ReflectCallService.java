package com.yichen.casetest.test.service.reflect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/24 17:04
 * @describe
 */
@Service
public class ReflectCallService {

    @Autowired
    private TransactionService transactionService;


    public String getName(){
        transactionService.save();
        return "shanliang";
    }

}
