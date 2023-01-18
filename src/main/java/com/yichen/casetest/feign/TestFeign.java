package com.yichen.casetest.feign;

import com.yichen.casetest.model.dto.RequestTestDTO;
import feign.Request;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    String testRequestParam9(Map<String, ?> param);

    @PostMapping(value = "/test/requestParamTest", consumes = MediaType.APPLICATION_JSON_VALUE)
    String testRequestParam10(Map<String, String> param);

    /**
     * multipart/form-data 这种格式如果用 Map存取，只能Map<String,?> 其他的不行
     */
    @PostMapping(value = "/test/requestParamTest", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String testRequestParam11(Map<String, ?> param);

    @PostMapping(value = "/test/requestParamTest", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String testRequestParam12(RequestTestDTO requestTestDTO);

    @PostMapping(value = "/test/requestParamTest", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String testRequestParam13(@RequestParam RequestTestDTO requestTestDTO);

    @PostMapping(value = "/test/requestParamTest", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String testRequestParam14(@RequestBody RequestTestDTO requestTestDTO);

    @PostMapping(value = "/test/requestParamTest")
    String testRequestParam15(@RequestParam String realName);

    @PostMapping(value = "/test/requestParamTest")
    String testRequestParam16(@RequestBody String realName);

    @PostMapping(value = "/test/requestParamTest")
    String testRequestParam17(@RequestParam String realName, Date time);

    @PostMapping(value = "/test/requestParamTest", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String testRequestParam18(@RequestParam String realName, Date time);

    @PostMapping(value = "/test/requestParamTest", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String testRequestParam19(@RequestParam String realName, Date time, @RequestHeader Map<String, String> header,
                              @SpringQueryMap @PathVariable @MatrixVariable @RequestPart String path);


    @PostMapping(value = "/test/requestParamTest")
    String testRequestParam20(@RequestParam String realName, @RequestParam Map<String, String> params);

    @PostMapping(value = "/test/requestParamTest")
    String testRequestParam21(@RequestParam String realName, @RequestParam Map<String, Object> params);

    /**
     * 可以通过在入参中加入 Request.Options 实现动态配置请求
     */
    @PostMapping(value = "/test/requestParamTest")
    String testRequestParam22(@RequestParam String realName, @RequestParam Map<String, Object> params, Request.Options options);


}
