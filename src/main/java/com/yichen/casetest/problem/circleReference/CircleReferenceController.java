package com.yichen.casetest.problem.circleReference;

import com.yichen.casetest.problem.circleReference.simpleCase.AService;
import com.yichen.casetest.problem.circleReference.simpleCase.BService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Qiuxinchao
 * @date 2023/3/15 8:50
 * @describe 循环引用测试 controller
 */
@RestController
@Slf4j
@RequestMapping("/circleReference")
@Api(tags = "循环引用测试")
public class CircleReferenceController {

    @Autowired
    Map<String, AbstractStrategy> maps;

    @Autowired
    private ToolService toolService;

    @Autowired
    private CloseService closeService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private AService aService;

    @Autowired
    private BService bService;

    @PostMapping("/test")
    public String test(){
        toolService.driverStatus();
        closeService.batchClose();
        return "ok";
    }

    @GetMapping("/aService")
    public String aService(){
        aService.exec();
        return "ok";
    }

    @GetMapping("/bService")
    public String bService(){
        bService.exec();
        return "ok";
    }

}
