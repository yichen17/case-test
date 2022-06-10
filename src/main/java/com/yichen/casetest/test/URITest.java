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
//        String s = "redis://user:password@example.com:6379";
        String s= "https://wxtest.9fbank.com/h5/#/billList?token=ef1503005bb401002e505dca00e04b498b4ae8e1c7573906";
        try {
            URI uri = new URI(s);
            log.warn("analysis uri {}", JSON.toJSONString(uri));
        }
        catch (URISyntaxException e){
            log.error("{}", e.getMessage(), e);
        }
    }

}
