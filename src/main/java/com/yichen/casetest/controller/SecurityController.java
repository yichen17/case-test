//package com.yichen.casetest.controller;
//
//import com.yichen.casetest.utils.FastJsonUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @author Qiuxinchao
// * @date 2023/2/10 9:10
// * @describe 安全 controller
// */
//@RestController
//@RequestMapping("/security")
//@Slf4j
//public class SecurityController {
//
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public String login(){
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("name", "yichen");
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
//        log.info("authenticate {}", FastJsonUtils.toJson(authenticate));
//        return "ok";
//    }
//
//}
