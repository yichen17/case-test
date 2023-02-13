package com.yichen.casetest.controller;

import com.yichen.casetest.dao.JsonTestMapper;
import com.yichen.casetest.model.JsonTestDTO;
import com.yichen.casetest.test.mapstruct.SexEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

    @PostMapping("/get")
    public JsonTestDTO get(){
        JsonTestDTO dto = mapper.getById(10000000001L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("===> {}",sdf.format(dto.getCreateTime()));
        return dto;
    }

    /**
     * 时区测试  和 JDBC后的时区以及服务运行时区有关，和jackson无关
     * @return
     */
    @PostMapping("/insert")
    public String insert(){
        Date date = new Date();
        log.info("date默认时间CST {}", date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formatDate = sdf.format(date);
        log.info("GMT时间{}", formatDate);
//        JsonTestDTO jsonTestDTO = JsonTestDTO.builder()
//                .createTime(new Date()).name("yichen").build();
        JsonTestDTO jsonTestDTO = JsonTestDTO.builder()
                .createTime(new Timestamp(System.currentTimeMillis())).name("yichen").sex(SexEnum.BOY).build();
        mapper.insert(jsonTestDTO);
        return "ok";
    }



}
