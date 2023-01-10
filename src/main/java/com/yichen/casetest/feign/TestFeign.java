package com.yichen.casetest.feign;

import com.yichen.casetest.model.dto.RequestTestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


/**
 * @author Qiuxinchao
 * @date 2023/1/10 14:58
 * @describe
 */
@FeignClient(name = "test", url = "http://localhost:8088")
public interface TestFeign {

    @PostMapping(value = "/test/requestParamTest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String testRequestParam(RequestTestDTO requestTestDTO);


    @PostMapping(value = "/test/requestParamTest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String testRequestParam1(@RequestBody Map<String, String> param);


    @PostMapping(value = "/test/requestParamTest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String testRequestParam2(@RequestParam String realName);

    @PostMapping(value = "/test/requestParamTest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String testRequestParam3(@RequestParam RequestTestDTO requestTestDTO);

    @PostMapping(value = "/test/requestParamTest", produces = "application/json;charset=UTF-8", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String testRequestParam4(@RequestParam String realName);

    @PostMapping(value = "/test/requestParamTest")
    String testRequestParam5(@RequestParam String realName);

    @PostMapping(value = "/test/requestParamTest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String testRequestParam6(@RequestParam Map<String, String> param);

    @PostMapping(value = "/test/requestParamTest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String testRequestParam7(Map<String, String> param);

    @PostMapping(value = "/test/requestParamTest")
    String testRequestParam8(String realName);

    @PostMapping(value = "/test/requestParamTest")
    String testRequestParam9(Map<String, String> param);

    @PostMapping(value = "/test/requestParamTest", consumes = MediaType.APPLICATION_JSON_VALUE)
    String testRequestParam10(Map<String, String> param);


}
