package com.yichen.casetest.problem.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/26 9:46
 * @describe
 */
@RestController("bot-test-controller")
@Slf4j
@RequestMapping("/bot")
public class TestController {

    @RequestMapping(value = "/registlogin", method = { RequestMethod.POST })
    @DecryptParam(encryptType = {"ios-json", "android-json", "h5-json"})
    @ResponseBody
    public String registlogin(@RequestBody RequestData<RegistLoginRequestEntity> requestData) {
        // 获取参数信息
        HashMap<String, String> bodyParams = new HashMap<String, String>();
        bodyParams.put("mobile", requestData.getData().getMobile());
        bodyParams.put("type", requestData.getData().getType());
        bodyParams.put("verifyCode", requestData.getData().getVerifyCode());
        bodyParams.put("t", requestData.getData().getT());
        bodyParams.put("ticket", requestData.getData().getTicket());
        bodyParams.put("pwd", requestData.getData().getPwd());
        bodyParams.put("certSign", requestData.getData().getCertSign());
        return "ok";
    }




}
