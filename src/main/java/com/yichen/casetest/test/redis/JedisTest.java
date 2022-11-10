package com.yichen.casetest.test.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Qiuxinchao
 * @date 2022/11/9 18:01
 * @describe
 */
public class JedisTest {

    public static void main(String[] args) throws Exception{
        jedisPoolTest();
    }

    public static void jedisTest()throws Exception{
        while(true){
            //创建一个连接
            Jedis jedis = new Jedis("localhost", 6379);
            jedis.auth("yichen");
            //ping一下Redis服务端是否在线，成功则返回 “PONG” 反之报错超时
            String ping = jedis.ping();
            System.out.println(ping);
            Thread.sleep(100000000);
        }
    }

    public static void jedisPoolTest(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
        Jedis jedis = null;
        try {
            //从连接池获取jedis对象
            jedis = jedisPool.getResource();
            jedis.auth("yichen");

            while (true){
                //执行操作
                jedis.set("java", "good");
                System.out.println(jedis.get("java"));
                Thread.sleep(120000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                //这里使用的close不代表关闭连接，指的是归还资源
                jedis.close();
            }
        }
    }

}
