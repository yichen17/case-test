package com.yichen.casetest.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/29 10:37
 * @describe 异步 service
 */
@Service
@Slf4j
public class AsyncService {

    private static Logger logger= LoggerFactory.getLogger(AsyncService.class);

    @Async("async-1")
    public void printTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        logger.info("调用时间 {}",sdf.format(new Date()));
    }

    @Async("async-2")
    public void printMessage(){
        logger.info("hello,welcome to my application");
    }

    @Async("async-3")
    public void overSize() throws Exception{
        log.info("oversize start");
        Thread.sleep(2000);
    }



}
