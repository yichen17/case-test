package com.yichen.casetest.config.position;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import sun.applet.AppletEvent;
import sun.applet.AppletListener;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/13 9:14
 * @describe 位置配置监听器
 *  参考  https://www.cnblogs.com/liuliuyan/p/10757971.html
 */
public class LocationPropertiesListener implements ApplicationListener<ApplicationStartedEvent> {

    private String propertyFileName;

    public LocationPropertiesListener(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        LocationPropertiesListenerConfig.loadAllProperties(propertyFileName);
    }
}
