package com.yichen.casetest.bugtest.mybatis;

import com.yichen.casetest.dao.JsonTestMapper;
import com.yichen.casetest.utils.FastJsonUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @date 2023/2/20 17:24
 * @describe mybatis 测试入口
 */
@RestController
@Slf4j
@RequestMapping("/mybatis")
@Api(tags = "mybatis测试")
public class MybatisTestController {

    @Autowired
    private JsonTestMapper jsonTestMapper;


    @PostMapping("/test")
    public String test(){
        return FastJsonUtils.toJson(jsonTestMapper.getByEnum(TimeDimensionEnum.THIS_WEEK, 10L));
    }

    @PostMapping("/test1")
    public String test1(){
        return FastJsonUtils.toJson(jsonTestMapper.getByEnum1(TimeDimensionEnum.THIS_WEEK));
    }

    @PostMapping("/test2")
    public String test2(){
        return FastJsonUtils.toJson(jsonTestMapper.getByEnum2(TimeDimensionEnum.THIS_WEEK));
    }

    @PostMapping("/test3")
    public String test3(){
        return FastJsonUtils.toJson(jsonTestMapper.getByEnum3(TimeDimensionEnum.THIS_WEEK));
    }

    @PostMapping("/test4")
    public String test4(){
        return FastJsonUtils.toJson(jsonTestMapper.getByEnum4(TimeDimensionEnum.THIS_WEEK));
    }

}
