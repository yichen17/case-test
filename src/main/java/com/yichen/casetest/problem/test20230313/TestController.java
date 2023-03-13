package com.yichen.casetest.problem.test20230313;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/3/13 20:25
 * @describe
 */
@RestController(value = "testController20230313")
@Api(tags = "jackson 和 @EnableWebMvc 测试")
@RequestMapping("/test20230313")
public class TestController {

    @PostMapping("/jacksonTest")
    public Result jacksonTest(){
        return Result.builder().date(new Date()).build();
    }

}
