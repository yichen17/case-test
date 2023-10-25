package com.yichen.casetest.module.event;

import com.yichen.casetest.utils.FastJsonUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/10/25 16:42
 * @describe
 */
@Component
public class EatListenerOther implements ApplicationListener<EatEvent> {
    @Override
    public void onApplicationEvent(EatEvent event) {
        System.out.println("other" + FastJsonUtils.toJson(event));
    }
}
