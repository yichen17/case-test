package com.yichen.casetest.service;

import com.yichen.casetest.model.User;

import java.util.List;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/25 11:29
 * @describe
 */
public interface UserService {

    List<User> getUserByName(String name);

}
