package com.yichen.casetest.test.future;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Qiuxinchao
 * @date 2023/5/31 8:58
 * @describe
 *  参考文章   https://mp.weixin.qq.com/s/NXaP1xPL7_At9e-MVad4cQ
 */
@Slf4j
@Component
public class CompletableFutureTest {

    @Qualifier("serviceKafkaTemplate")
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public static void main(String[] args) {

    }

    public void linear(){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("yichen.test", "{\"name\":\"aa\"}");
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("kafka：" + Thread.currentThread().getName());
                log.info("发送成功：" + result.toString());
            }
        });
        log.info("当前主线程名: " + Thread.currentThread().getName());

        // kafka发送完成并且第二个线程都执行完成时执行第三个线程
        future.completable().runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            log.info("第二个线程名: " + Thread.currentThread().getName());
            return "marc";
        }), () -> {
            log.info("第三个线程名: " + Thread.currentThread().getName());
        });
    }

    /**
     *
     */
    public void race(){

// 谁先完成谁先执行print
        CompletableFuture.supplyAsync(() -> {
            log.info("turtle thread name:" + Thread.currentThread().getName());
            return "i'm turtle";
        }).acceptEitherAsync(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("rabbit thread name" + Thread.currentThread().getName());
            return "i'm rabbit";
        }), System.out::println);
    }

    /**
     * 多任务全部成功才满足
     */
    public void waitAllExec(){
        // 所有任务都完成了才执行thenRunAsync
        CompletableFuture.allOf(
                CompletableFuture.completedFuture("marc est beau"),
                CompletableFuture.supplyAsync(() -> {
                    log.info("marc est beau 1");
                    return "ok";
                }),
                CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        log.info("ok againnnnn");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
        ).thenRunAsync(() -> {
            log.info(Thread.currentThread().getName());
        });
    }

    /**
     * 多任务一个成功就满足
     */
    public void anySuccess(){

// 只有要一个完成了就执行thenRunAsync
        CompletableFuture.anyOf(
                CompletableFuture.completedFuture("marc est beau"),
                CompletableFuture.supplyAsync(() -> {
                    log.info("marc est beau 1");
                    return "ok";
                }),
                CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        log.info("ok againnnnn");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
        ).thenRunAsync(() -> {
            log.info(Thread.currentThread().getName());
        });
    }

    /**
     * 关注第一个成功
     */
    public void firstExec(){
        // 只关注第一名
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "marc est beau";
        }).applyToEitherAsync(CompletableFuture.supplyAsync(() -> "marc est beau 1"), s -> {
            log.info("who is first ====> " + s);
            return s;
        });
    }

}
