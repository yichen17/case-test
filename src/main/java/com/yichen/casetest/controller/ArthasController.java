package com.yichen.casetest.controller;

import com.yichen.casetest.constants.CommonConstants;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/9/28 14:48
 * @describe arthas 命令相关研究
 */
@RestController
@RequestMapping("/rthas")
@Api(value = "arthas测试", tags = "arthas测试")
@Slf4j
public class ArthasController {

    /**
     * 前缀匹配命令
     * tt -t -m 1 -n 5 com.yichen.casetest.controller.ArthasController rePrint 'params[0].startsWith("shan")'
     */
    @PostMapping("/rePrint")
    public String rePrint(@RequestParam String s) {
        return s;
    }

    /**
     * 数组取数校验
     * 数组长度校验   tt -t -m 1 -n 1 com.yichen.casetest.controller.ArthasController listPrint 'params[0].size > 0'
     * 数据单项校验   tt -t -m 1 -n 1 com.yichen.casetest.controller.ArthasController listPrint 'params[0][1].startsWith("yi")'
     */
    @PostMapping("/listPrint")
    public String listPrint(@RequestParam List<String> lists) {
        return String.join(CommonConstants.COMMA, lists);
    }


}
