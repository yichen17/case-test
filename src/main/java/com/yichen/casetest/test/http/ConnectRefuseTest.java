package com.yichen.casetest.test.http;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author Qiuxinchao
 * @date 2023/5/9 13:52
 * @describe 请求连接拒绝测试 connect refuse测试
 *      https://juejin.cn/post/7155997400484544543#heading-1
 */
@Slf4j
public class ConnectRefuseTest {

    public static void main(String[] args)throws Exception {
        tomcatRequestTest();
    }

    private static void tomcatRequestTest() throws Exception{
        int n = 5;
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for(int i=0; i<n; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.info("请求开始");
//                        String s = HttpUtil.get("http://localhost:8088/test/get", 10000);
                        String s = HttpUtil.get("http://59.63.212.105:8085/get", 10000);
//                        String s = HttpUtil.get("http://59.63.212.105:8085/health", 10000);
                        log.info("==> {}", s);
                    }
                    catch (Exception e){
                        log.error("执行异常 {}", e.getMessage(), e);
                    }
                    finally {
                        countDownLatch.countDown();
                    }
                }
            }, String.format("tomcatRequestTest-%s", i)).start();
        }
        countDownLatch.await();
    }


}
