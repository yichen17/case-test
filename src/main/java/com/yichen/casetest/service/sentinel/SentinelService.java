package com.yichen.casetest.service.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @date 2023/4/28 11:00
 * @describe
 */
@Slf4j
@Service
public class SentinelService {

    @SentinelResource(value = "message")
    public void message(){
        log.info("sentinelService message");
    }

}
