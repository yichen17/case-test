package com.yichen.casetest.controller;

import com.alibaba.fastjson.JSON;
import com.yichen.casetest.model.ParamFromStreamDto;
import com.yichen.casetest.model.dto.TestFormDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/7 15:27
 * @describe 请求参数 测试 controller
 */
@RestController
public class RequestParamController {

    private static Logger logger= LoggerFactory.getLogger(RequestParamController.class);

    @RequestMapping("/getParamFromStream")
    public String testGetParamFromStream(HttpServletRequest request){
        String reqString=getReqString(request);
        logger.info("reqString {}",reqString);
        ParamFromStreamDto dto = JSON.parseObject(reqString, ParamFromStreamDto.class);
        return dto.toString();
    }

    /**
     * 表单数据可以通过这种方式接收数据    单个参数的话  @RequestParam
     * 接收json数据需要加 @RequestBody
     * @param dto
     * @return
     */
    @RequestMapping("/testForm")
    public String testForm(TestFormDTO dto, HttpServletRequest request){
        logger.info("testForm arrive, request string {}",getReqString(request));
        return JSON.toJSONString(dto);
    }


    private String getReqString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return String.valueOf(sb);
    }

}
