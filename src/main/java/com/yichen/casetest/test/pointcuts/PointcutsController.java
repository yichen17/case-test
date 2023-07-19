package com.yichen.casetest.test.pointcuts;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Qiuxinchao
 * @date 2023/7/19 13:49
 * @describe
 */
@RequestMapping("/pointcuts")
@Api(tags = "切面相关测试")
@RestController
@Slf4j
 class PointcutsController {

    @Autowired
    private A a;

    @Autowired
    private B b;

    @PostMapping("/sayHello")
    @CA(name = "sayHello")
    public String sayHello(){
        b.sayHello();
        return "ok";
    }

    @PostMapping("/oneParam")
    public String oneParam(@CA @RequestParam @ApiParam(defaultValue = "11") String ss){
        a.oneParam(ss);
        return "ok";
    }

    @PostMapping("twoParam")
    public String twoParam(@CA @RequestParam @ApiParam(defaultValue = "11") String s, @ApiParam(defaultValue = "11") @RequestParam Integer t){
        a.twoParam(s, t);
        return "ok";
    }

    // annoTarget touch 、  annoWithin touch 可以触发

    @PostMapping("/notice")
    public String notice(){
        b.notice("666");
        return "ok";
    }

    // annoArgs touch  方法接收的入参需要携带对应的注释、必须是实体类，类上加注解  每个参数必须都带有注解

    @PostMapping("/print")
    public String print(@RequestBody TT tt){
//        a.print(tt);
        a.print(tt, tt.fork());
        return "ok";
    }




}
