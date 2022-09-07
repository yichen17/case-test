package com.yichen.casetest.controller;

import com.yichen.casetest.dao.JsonTestMapper;
import com.yichen.casetest.model.CountDownLatchDTO;
import com.yichen.casetest.model.JsonTestDTO;
import com.yichen.casetest.test.execute.MoreExecutorsOptimize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/4 9:04
 * @describe 线程池测试类
 */
@Slf4j
@RestController
@RequestMapping("/threadPool")
public class ThreadPoolController implements DisposableBean {

    @Autowired
    private MoreExecutorsOptimize moreExecutorsOptimize;

    @Autowired
    private JsonTestMapper jsonTestMapper;

    @GetMapping("/executeOld")
    public String executeOld(){
        log.info("execute old start");
        for (int i=0; i<100; i++){
            moreExecutorsOptimize.executeOld();
        }
        return "ok";
    }

    @GetMapping("/executeOptimize")
    public String executeOptimize(){
        log.info("execute optimize start");
        for (int i=0; i<100; i++){
            moreExecutorsOptimize.executeOptimize();
        }
        return "ok";
    }

    private final ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10,1L,
            TimeUnit.SECONDS,new ArrayBlockingQueue<>(256), new ThreadPoolExecutor.CallerRunsPolicy());

    @PostMapping("/countDownLatch")
    public List<CountDownLatchDTO> countDownLatchTest(@RequestParam String idsStr){

//        String idsStr = "11,23,45";
        String[] ids = idsStr.split(",");
        List<CountDownLatchDTO> jsonTestList = new ArrayList<>(ids.length);
        CountDownLatch countDownLatch = new CountDownLatch(ids.length);
        for (String id : ids){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.info("当前执行id {}", id);
                        JsonTestDTO result = jsonTestMapper.getById(Long.parseLong(id));
                        CountDownLatchDTO dto = CountDownLatchDTO.builder().id(result.getId()).name(result.getName()).build();
                        jsonTestList.add(dto);
                    }
                    catch (Exception e){
                        log.error("出現錯誤 {}", e.getMessage(), e);
                    }
                    finally {
                        log.info("当前线程执行完毕 {} {}", Thread.currentThread().getName(),  countDownLatch.getCount());
                        countDownLatch.countDown();
                    }
                }
            });
            try {
                log.info("当前等待数量 {}", countDownLatch.getCount());
                countDownLatch.await();
                log.info("结束后等待数量 {}", countDownLatch.getCount());
            }
            catch (Exception e){
                log.error("出现错误 {}", e.getMessage(), e);
                log.info("异常中断等待数量 {}", countDownLatch.getCount());
            }
        }
//        try {
//            log.info("当前等待数量 {}", countDownLatch.getCount());
//            countDownLatch.await();
//            log.info("结束后等待数量 {}", countDownLatch.getCount());
//        }
//        catch (Exception e){
//            log.error("出现错误 {}", e.getMessage(), e);
//            log.info("异常中断等待数量 {}", countDownLatch.getCount());
//        }
        log.info("6666");
        return jsonTestList;
    }


    @Override
    public void destroy() throws Exception {
        pool.shutdown();
    }
}
