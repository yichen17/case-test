package com.yichen.casetest.controller;

import com.yichen.casetest.service.CacheService;
import com.yichen.casetest.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/10/25 9:10
 * @describe  redis 相关测试 controller
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CacheService cacheService;


    @RequestMapping("/add")
    public String addToRedis(String key)throws Exception{
        String end="2022-05-29 13:00:00";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date endDate = sdf.parse(end);
        stringRedisTemplate.opsForValue().set(key,"111");
        stringRedisTemplate.expireAt(key,endDate);
        Long between = TimeUtils.getTimestampBetweenTwoDays(sdf.format(new Date()), end);
        return "ok ===>   "+between;
    }

    /**
     * 获取对应key 的毫秒数
     * @param key 查询的key
     * @return
     */
    @RequestMapping("/validTime")
    public String getKeyValidTime(String key){
        Long expire = stringRedisTemplate.getExpire(key);
        return ""+expire;
    }

    /**
     *   ===============     测试  @cache  ===============
     */
    @RequestMapping("/cache/get")
    public String cacheGet(@RequestParam("name") String name){
        String value=cacheService.get(name);
        return value;
    }

    @RequestMapping("/cache/save")
    public String cacheSave(@RequestParam("name") String name,@RequestParam("value") String value){
        return cacheService.save(name,value);
    }

    @RequestMapping("/cache/delete")
    public void cacheDelete(@RequestParam("name") String name){
        cacheService.delete(name);
    }



}
