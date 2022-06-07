package com.yichen.casetest.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/6 14:24
 * @describe URI 测试
 */
@Slf4j
public class URITest {

    public static void main(String[] args) {
        String s = "redis://user:password@example.com:6379";
        try {
            URI uri = new URI(s);
            log.warn("analysis uri {}", JSON.toJSONString(uri));
        }
        catch (URISyntaxException e){
            log.error("{}", e.getMessage(), e);
        }
    }

}
