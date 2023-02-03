package com.yichen.casetest.test.weight;

import com.yichen.casetest.utils.StringUtils;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Qiuxinchao
 * @date 2023/2/3 15:37
 * @describe 时间轮测试
 */
@Slf4j
public class HashedWheelTimerTest {

    public static void main(String[] args) throws Exception{
//        testTimer();
        StringUtils.divisionLine();
        testBase();
        StringUtils.divisionLine();
    }

    /**
     * 时间轮基础测试
     */
    public static void testBase(){
        log.info("{} {}", Long.MIN_VALUE, -Long.MAX_VALUE);
    }

    /**
     * 时间轮测试
     * @throws Exception
     */
    public static void testTimer() throws Exception{
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        hashedWheelTimer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                log.info("等待3分钟执行");
                countDownLatch.countDown();
            }
        }, 180, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                log.info("等待1分钟执行");
                countDownLatch.countDown();
            }
        }, 60, TimeUnit.SECONDS);
        hashedWheelTimer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                log.info("等待2分钟执行");
                countDownLatch.countDown();
            }
        }, 120, TimeUnit.SECONDS);
        log.info("启动时间轮");
//        hashedWheelTimer.start();
        countDownLatch.await();
        hashedWheelTimer.stop();
    }

}
