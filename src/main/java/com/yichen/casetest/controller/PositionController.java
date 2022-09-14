package com.yichen.casetest.controller;

import com.yichen.casetest.config.position.LocationPropertiesListenerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/13 9:26
 * @describe 地址归属码测试入口
 */
@RestController
@Slf4j
@RequestMapping("/position")
public class PositionController {

    @GetMapping("/getByCertId")
    public String getPosition(@RequestParam String certId){
        return LocationPropertiesListenerConfig.getProperty(certId.substring(0,2) + "0000") +
                LocationPropertiesListenerConfig.getProperty(certId.substring(0,4) + "00") +
                LocationPropertiesListenerConfig.getProperty(certId.substring(0,6)) ;
    }


}
