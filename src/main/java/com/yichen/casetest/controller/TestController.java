package com.yichen.casetest.controller;

import com.alibaba.fastjson.JSON;
import com.yichen.casetest.model.DataTest;
import com.yichen.casetest.model.JacksonTest;
import com.yichen.casetest.model.JsonDto;
import com.yichen.casetest.service.TestService;
import com.yichen.casetest.test.mapstruct.Person;
import com.yichen.casetest.test.mapstruct.PersonDTO;
import com.yichen.casetest.test.mapstruct.PersonMapper;
import com.yichen.casetest.test.service.Teacher;
import com.yichen.casetest.test.service.reflect.impl.ReflectServiceImpl;
import com.yichen.casetest.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
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

    /**
     * 测试fastjson 漏洞代码远程执行    入参   http://localhost:8088/test/remoteExec?str=mkdir%20/test/bug/11
     */
    @RequestMapping("/remoteExec")
    @ResponseBody
    public String remoteExec(@RequestParam String str){
        try {
            String json = "{\"@type\":\"java.lang.Exception\",\"@type\":\"com.yichen.casetest.bugtest.fastjson.Poc\",\"name\":\""+ str +"\"}";
            log.info("json {}", json);
            JSON.parse(json);
        }
        catch (Exception e){
            log.error("remoteExec 出现错误 {}", e.getMessage(), e);
        }
        return "ok";
    }

    @Autowired
    private Teacher teacher;


    @Autowired
    private ReflectServiceImpl reflectService;

    /**
     * 反射测试     通过反射调用一个bean的私有方法，公司的代码里中反射类中的属性为空，然后又代理(cglib)，但是这里我自己模拟的话没有代理。。。
     * https://www.freesion.com/article/65211179151/  略有启发  => 但是我这里用了事务也还是jdk proxy。。。
     * ==> 什么情况下会 cglib proxy 接管 ？？？    => 触发场景是有切面 即 @Aspect   => 原因解读 cglib生成代理子类，子类不包含私有方法
     *   https://blog.csdn.net/J080624/article/details/69485899
     * @param name
     * @param age
     * @return
     */
    @PostMapping("/reflectTest")
    @ResponseBody
    public Object reflectTest(@RequestParam String name, @RequestParam String age){
        try {


            Method method = ReflectServiceImpl.class.getDeclaredMethod("reflectTest", String.class, String.class);
//            method.setAccessible(true);
            return method.invoke(reflectService, name, age);

        }
        catch (Exception e){
            log.error("reflectTest出现错误{}", e.getMessage(), e);
        }
        return "error";
    }

    @PostMapping("/reflectTest1")
    @ResponseBody
    public Object reflectTest1(@RequestParam String name, @RequestParam String age){
        try {


            Method method = ReflectServiceImpl.class.getDeclaredMethod("reflectTest1", String.class, String.class);
            method.setAccessible(true);
            return method.invoke(reflectService, name, age);

        }
        catch (Exception e){
            log.error("reflectTest出现错误{}", e.getMessage(), e);
        }
        return "error";
    }

    @GetMapping("/jacksonPrint")
    @ResponseBody
    public JacksonTest jacksonPrint(){
        return JacksonTest.builder().name("yichen").createTime(new Date()).build();
    }

}
