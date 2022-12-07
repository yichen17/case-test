package com.yichen.casetest.test.service.reflect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/24 18:04
 * @describe
 */
@Service
@Slf4j
public class TransactionService {

    public void save(){
        log.info("save");
    }

}
