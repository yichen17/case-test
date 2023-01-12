package com.yichen.casetest.service;

import brave.internal.Platform;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Qiuxinchao
 * @date 2023/1/12 13:40
 * @describe
 */
@Service
@Slf4j
public class TimingService {

//    @Scheduled(cron = "0/1 * * * * ?")
    public void traceIdTest(){
        if (Platform.get().randomLong() % 2 ==1){
            log.warn("======> 当前traceId {}", MDC.get("traceId"));
            MDC.put("traceId", DigestUtils.md5Hex(UUID.randomUUID().toString()).substring(8, 24));
            log.warn("======> 复制后 {}", MDC.get("traceId"));
            MDC.clear();
        }
        else {
            log.warn("当前traceId {}", MDC.get("traceId"));
        }
    }




}
