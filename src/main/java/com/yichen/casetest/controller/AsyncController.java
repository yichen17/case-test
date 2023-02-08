package com.yichen.casetest.controller;

import com.yichen.casetest.service.AsyncService;
import com.yichen.casetest.test.async.AsyncExecService;
import com.yichen.casetest.test.async.AsyncExecServiceImpl2;
import com.yichen.casetest.test.async.AsyncExecServiceImpl3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/29 10:35
 * @describe 异步 测试
 */
@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {


    @Autowired
    private AsyncService asyncService;

    @Autowired
    private AsyncExecService asyncExecService;

    @Resource
    private AsyncExecServiceImpl2 asyncExecServiceImpl2;

    @Resource
    private AsyncExecServiceImpl3 asyncExecServiceImpl3;


    @GetMapping("/execute")
    public String execute(){
        asyncService.printTime();
        asyncService.printMessage();
        return "执行完毕";
    }

    @GetMapping("/getCoreSize")
    public String getCoreSize(){
        return "cpu核心数 "+Runtime.getRuntime().availableProcessors();
    }

    /**
     * 测试是否是异步线程池执行
     * 结论：同一个类中调用不会给异步线程池，其他都可以
     * @param type
     */
    @GetMapping("/testExec")
    public void testExec(@RequestParam int type){
        switch (type){
            case 1:
                asyncExecService.printExec();
                break;
            case 2:
                asyncExecServiceImpl2.printExec();
                break;
             // 不会给异步线程池
            case 3:
                printExec();
                break;
            //  类级别修饰也可以触发
            case 4:
                asyncExecServiceImpl3.printExec();
                break;
            default:
                break;
        }
    }

    @Async("async-2")
    public void printExec() {
        log.info("async exec");
    }


    /**
     * 测试异步线程池执行是否有traceId   =>  如果有了sleuth-core依赖，它里面有个aop会添加
     */
    @GetMapping("/asyncExecTraceId")
    public void asyncExecTraceId(){
        log.warn("asyncExecTraceId");
        asyncService.printMessage();
    }


    /**
     * 异步线程池满了后的拒绝策略测试
     * @return
     * @throws Exception
     */
    @GetMapping("/overSize")
//    @Async("async-3")
    public String overSize() throws Exception{
        asyncService.overSize();
        return "ok";
    }

}
