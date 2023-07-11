package com.yichen.casetest.service.db.impl;

import com.yichen.casetest.dao.DbTestMapper;
import com.yichen.casetest.model.db.DbTestDo;
import com.yichen.casetest.service.db.DbTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Qiuxinchao
 * @date 2023/7/11 17:57
 * @describe
 */
@Service
@Slf4j
public class DbTestServiceImpl implements DbTestService {

    @Autowired
    private DbTestMapper dbTestMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void consistentFail(String key) {
        dbTestMapper.save(DbTestDo.builder().val("11").key(key).build());
    }

    @Override
    public void consistentFailTrans(String key) {
        ((DbTestServiceImpl)AopContext.currentProxy()).consistentFail(key);
    }
}
