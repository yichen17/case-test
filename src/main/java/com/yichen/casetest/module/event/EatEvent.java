package com.yichen.casetest.module.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/10/25 16:39
 * @describe
 */
@Getter
public class EatEvent extends ApplicationEvent {
    private final EatMsg eatMsg;

    public EatEvent(Object source, EatMsg eatMsg) {
        super(source);
        this.eatMsg = eatMsg;
    }
}
