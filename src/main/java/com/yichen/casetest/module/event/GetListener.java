package com.yichen.casetest.module.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Qiuxinchao
 * @date 2023/4/26 16:04
 * @describe
 */
@Slf4j
@Component
public class GetListener {

    @EventListener
    public void handleNotifyEvent(GetEvent event) {
        log.info("get listener {}", event.getDesc());
    }

}
