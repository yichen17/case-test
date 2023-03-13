package com.yichen.casetest.hystrix;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.TimeUnit;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/3/12 17:20
 * @describe
 */
@SpringBootTest
@TestPropertySource(value = "classpath:application-test.properties")
public class HystrixTest {

    @Test
    public void fun1() throws InterruptedException {
        // 10秒钟大于20个请求 失败数超过50%就触发熔断
        // 35个请求，还可以看到半开状态哦~~
        for (int i = 0; i < 35; i++) {
            String name = i % 2 == 0 ? null : "demo"; // 用于模拟50%的错误率
            FallbackDemo demo = new FallbackDemo(name);
            demo.execute(); //同步执行

            // 因为10秒内要至少放20个请求进去
            // 因为第一个请求先发出再休眠，所以此处取值500ms是没有问题的
            TimeUnit.MILLISECONDS.sleep(500);
        }

    }

}
