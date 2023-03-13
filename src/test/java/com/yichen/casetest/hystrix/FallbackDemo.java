package com.yichen.casetest.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/3/12 17:20
 * @describe
 */
public class FallbackDemo extends HystrixCommand<String> {
    private final String name;

    public FallbackDemo(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("fallbackDemoGroup"));
        this.name = name;
    }

    @Override
    protected String run() {
        System.out.printf("健康信息：%s，断路器是否打开：%s\n", getMetrics().getHealthCounts(), circuitBreaker.isOpen());
        if (name == null) {
            throw new NullPointerException();
        }
        return "Hello " + name + "!";
    }

    @Override
    protected String getFallback() {
        Throwable e = getExecutionException(); // 导致目标方法执行失败的异常类型
        if (!(e instanceof NullPointerException)) {
            System.out.printf("异常类型：%s，信息：%s\n", e.getClass().getSimpleName(), e.getMessage());
        }
        return "this is fallback msg";
    }
}
