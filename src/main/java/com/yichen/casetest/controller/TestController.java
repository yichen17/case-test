package com.yichen.casetest.controller;

import com.alibaba.fastjson.JSON;
import com.yichen.casetest.model.DataTest;
import com.yichen.casetest.model.JsonDto;
import com.yichen.casetest.service.TestService;
import com.yichen.casetest.test.mapstruct.Person;
import com.yichen.casetest.test.mapstruct.PersonDTO;
import com.yichen.casetest.test.mapstruct.PersonMapper;
import com.yichen.casetest.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/1/10 15:48
 * @describe 研究原理的测试controller
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/get")
    @ResponseBody
    public String get(){
        return "test-get";
    }

    /**
     * 测试是否能从数据库中读取 decimal 类型的null 值  =>  可以
     * @param id
     * @return
     */
    @GetMapping("/getById")
    @ResponseBody
    public String getDataById(Long id){
        return testService.getByid(id).toString();
    }

    @GetMapping("/")
    @ResponseBody
    public String nginxTestA(){
        return "nginx a";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String nginxTestB(){
        return "nginx b";
    }


    @GetMapping("/date")
    @ResponseBody
    public JsonDto date(){
        return new JsonDto(null,null,null,new Date(),0,null);
    }

    @PostMapping("/nginxTest")
    @ResponseBody
    public String nginxTest(HttpServletRequest request){
        Map<String,String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            headers.put(headerName,request.getHeader(headerName));
        }
        return JSON.toJSONString(headers);
    }


    @RequestMapping("/errorTest")
    @ResponseBody
    public String errorTest(){
        // 如果捕获的异常总仍然有错误，则不会打印错误日志，继续抛出错误
        DataTest dataTest = null;
        try {
            int s = 1/0;
        }
        catch (Exception e){
            log.error("出现错误 {}", dataTest.getMoney());
        }
        return "error";
    }

    @RequestMapping("/urlTest")
    @ResponseBody
    public String urlTest(HttpServletRequest request){
        // get 为查询入参  ? 后面的内容
        return request.getQueryString();
    }

    @RequestMapping("/mapStruct")
    @ResponseBody
    public String mapStruct(){
        Person person = new Person();
        person.setDescribe("测试");
        person.setAge(18);
        person.setName("张三");
        person.setHeight(170.5);
        person.setSource(new BigDecimal("100"));
        PersonDTO dto = PersonMapper.INSTANCT.convert(person);
        return FastJsonUtils.toJson(dto);
    }

    @RequestMapping("testLong")
    @ResponseBody
    public long longTest(){
        return 11147369423569007L;
    }



}
