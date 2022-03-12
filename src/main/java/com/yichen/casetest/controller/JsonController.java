package com.yichen.casetest.controller;

import com.yichen.casetest.dao.JsonTestMapper;
import com.yichen.casetest.model.JsonTestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/12 14:24
 * @describe  json 相关测试 controller
 */
@RestController
@RequestMapping("/json")
@Slf4j
public class JsonController {

    @Autowired
    private JsonTestMapper mapper;

    @RequestMapping("/get")
    public JsonTestDTO get(){
        JsonTestDTO dto = mapper.getById(10000000001L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("===> {}",sdf.format(dto.getCreateTime()));
        return dto;
    }


}
