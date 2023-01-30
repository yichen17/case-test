package com.yichen.casetest.test.queue;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Qiuxinchao
 * @date 2023/1/30 9:47
 * @describe
 */
public class Order implements Delayed {
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private long time;
    String name;

    public Order(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        Order order = (Order) o;
        long diff = this.time - order.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
