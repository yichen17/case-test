package com.yichen.casetest.service.Impl;

import com.alibaba.fastjson.JSON;
import com.yichen.casetest.dao.UserMapper;
import com.yichen.casetest.model.User;
import com.yichen.casetest.model.UserExample;
import com.yichen.casetest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/25 11:30
 * @describe
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> getUserByName(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> users = mapper.selectByExample(example);
        log.info("查询结果 {}", JSON.toJSONString(users));
        return users;
    }

    @Override
    @Transactional
    public int save(User user) {
        if (Objects.isNull(user)){
            return 0;
        }
//        return mapper.insert(user);

        mapper.insert(user);
        throw new RuntimeException("执行异常");

    }
}
