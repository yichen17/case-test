package com.yichen.casetest.test.execute.forkJoinPool.compare;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @date 2023/6/20 16:58
 * @describe
 */
@Slf4j
@RestController("TPFK")
@RequestMapping("/TPFK")
@Api(tags = "TP和FK比对")
public class TestController {

    @GetMapping("/forkJoinPoolTest")
    @ApiOperation(value = "forkJoin测试")
    public String forkJoinPoolTest() throws Exception{
        LinkFinderAction.t0 = System.nanoTime();
        WebCrawler7 webCrawler7 = new WebCrawler7("https://www.infoworld.com/category/java/", 64);
        webCrawler7.startCrawling();
        return webCrawler7.getDesc();
    }


    @GetMapping("/gc")
    @ApiOperation(value = "手动执行gc")
    public String gc(){
        System.gc();
        return "ok";
    }

    @GetMapping("/threadPoolTest")
    @ApiOperation(value = "线程池测试")
    public String threadPoolTest() throws Exception{
        LinkFinder.t0 = System.nanoTime();
        new WebCrawler6("https://www.infoworld.com/category/java/", 64).startCrawling();
        return "ok";
    }

}
