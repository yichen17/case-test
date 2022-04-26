package com.yichen.casetest.controller;

import com.yichen.casetest.model.User;
import com.yichen.casetest.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/4/25 11:42
 * @describe
 */
@SpringBootTest
@TestPropertySource(value = "classpath:application-test.properties")
public class UserController {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        List<User> users = userService.getUserByName("shanliang");
        System.out.println(users);
    }

}
