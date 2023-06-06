package com.yichen.casetest.controller;

import com.yichen.casetest.model.common.ResultData;
import com.yichen.casetest.model.common.ResultDataUtil;
import com.yichen.casetest.utils.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Qiuxinchao
 * @date 2023/6/5 19:47
 * @describe 工具入口编码
 */
@RestController
@Slf4j
@RequestMapping("/tool")
@Api(tags = "工具入口")
public class ToolController {

    @PostMapping(value = "/dateToTimestamp",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "yyyy-MM-dd hh:mm:ss 时间转为时间戳")
    public ResultData timeToTimestamp(
            @RequestParam(required=true,name = "time") @ApiParam(name = "time",value = "yyyy-MM-dd HH:mm:ss格式时间",example = "2022-01-19 00:00:00" ) String time
    ){
        log.info("时间转为时间戳，入参 {}",time);
        String s = TimeUtils.dateToTimestamp(time);
        if(StringUtils.isEmpty(s)){
            return ResultDataUtil.errorResult("执行出错，请确认日期格式为 yyyy-MM-dd hh:mm:ss");
        }
        return ResultDataUtil.successResult(s);
    }

    @PostMapping(value = "/timestampToDate",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "时间戳转为 yyyy-MM-dd hh:mm:ss 格式 时间")
    public ResultData timestampToTime(
            @RequestParam(required=true,name = "timestamp") @ApiParam(name = "timestamp",value = "时间戳，精确到毫秒",example = "1642404189821" ) String timestamp
    ){
        log.info("时间戳转为日期，入参 {}",timestamp);
        String s = TimeUtils.timestampToDate(timestamp);
        return ResultDataUtil.successResult(s);
    }

    @GetMapping(value = "/getCurrentTimestamp")
    @ApiOperation(value = "获取当前时间的时间戳")
    public ResultData getCurrentTimestamp(){
        log.info("获取当前时间的时间戳");
        return ResultDataUtil.successResult("当前时间的时间戳",System.currentTimeMillis());
    }

    @GetMapping(value = "/getUuid")
    @ApiOperation(value = "获取随机生成的uuid")
    public ResultData getUuid(@RequestParam @ApiParam(name = "replace", value = "true表示替换-为空", example = "0")boolean replace){
        log.info("随机生成uuid type {}", replace);
        if (replace){
            return ResultDataUtil.successResult("生成uuid成功", UUID.randomUUID().toString().replace("-", ""));
        }
        return ResultDataUtil.successResult("生成uuid成功",UUID.randomUUID().toString());
    }

    @PostMapping(value = "/codeReplace")
    @ApiOperation(value = "字段替换")
    public ResultData codeReplace(@RequestParam @ApiParam(name = "str", value = "待处理字符串")String str,
                                  @RequestParam @ApiParam(name = "splitCode", value = "切割字符")String splitCode,
                                  @RequestParam @ApiParam(name = "delimiter", value = "间隔符号")String delimiter,
                                  @RequestParam @ApiParam(name = "left", value = "左填充")String left,
                                  @RequestParam @ApiParam(name = "right", value = "右填充")String right){
        log.info("codeReplace 入参 {} {} {}", splitCode, left, right);
        if (com.yichen.casetest.utils.StringUtils.containsEmpty(str)){
            return ResultDataUtil.errorResult("必填参数为空");
        }
        return ResultDataUtil.successResult("处理成功", Arrays.stream(str.split(splitCode)).collect(Collectors.joining(delimiter, left, right)));
    }

    @PostMapping(value = "/secondPower")
    @ApiOperation(value = "二次幂")
    public ResultData secondPower(@RequestParam @ApiParam(name = "times", value = "幂次数", defaultValue = "2")Integer times){
        log.info("二次幂计算入参{}", times);
        if (Objects.isNull(times)){
            return ResultDataUtil.errorResult("请输入幂次数");
        }
        if (times >= 0){
            return ResultDataUtil.successResult("计算成功", 1 << times);
        }
        return ResultDataUtil.successResult("计算成功", 1.0 / (1 << (-times)));
    }


}
