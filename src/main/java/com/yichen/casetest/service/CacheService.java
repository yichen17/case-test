package com.yichen.casetest.service;

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
public class CacheService {

    @CachePut(value = "cache",key = "#name")
    public String save(String name,String value){
        return value;
    };

    @CacheEvict(value = "cache",key = "#name")
    public void delete(String name){

    };

    @Cacheable(value = "cache",key = "#name")
    public String get(String name){
        return null;
    }

}
