package com.yichen.casetest.service;

import com.yichen.casetest.dao.DataTestMapper;
import com.yichen.casetest.model.DataTest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/2/7 14:46
 * @describe 测试 service
 */
@Service
public class TestService {

    @Resource
    private DataTestMapper mapper;

    public DataTest getByid(Long id){
        return mapper.selectByPrimaryKey(id);
    }



}
