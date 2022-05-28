package com.yichen.casetest.service;

import com.yichen.casetest.dao.JsonTestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/15 15:05
 * @describe 缓存service
 */
@Service
@Slf4j
public class CacheService {

    @Autowired
    private JsonTestMapper mapper;

    @CachePut(value = "c10m",key = "#name", cacheManager = "serviceRedisCacheManager")
    public String save(String name,String value){
        log.info("加入缓存 key {} value {}",name,value);
        return "6666";
    };

    @CacheEvict(value = "cache",key = "#name")
    public void delete(String name){
        log.info("删除缓存 key {}",name);
    };

    @Cacheable(value = "cache",key = "#name")
    public String get(String name){
        log.info("获取缓存值 key {}",name);
        return mapper.getById(Long.parseLong(name)).getName();
    }

}
