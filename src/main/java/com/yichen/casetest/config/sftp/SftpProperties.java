package com.yichen.casetest.config.sftp;

import lombok.Data;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ChenYingxin
 * @date 2021/1/15
 * @description
 **/
@Data
@ConfigurationProperties(prefix = "sftp.client")
public class SftpProperties {
    /**
     * ip地址
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 协议
     */
    private String protocol;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     *
     */
    private String sessionStrictHostKeyChecking;
    /**
     * session连接超时时间
     */
    private Integer sessionConnectTimeout;
    /**
     * channel连接超时时间
     */
    private Integer channelConnectedTimeout;
    /**
     * 连接池
     */
    private Pool pool = new Pool();

    @Data
    public static class Pool extends GenericObjectPoolConfig {

        private static final long DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS = -1L;
        private static final long DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS = 1000L * 60L * 30L;
        private static final boolean DEFAULT_TEST_WHILE_IDLE = true;
        private static final String DEFAULT_JMX_NAME_BASE = "sftp";
        private static final long DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS = 1000L * 60L * 10L;

        private int maxTotal = DEFAULT_MAX_TOTAL;
        private int maxIdle = DEFAULT_MAX_IDLE;
        private int minIdle = DEFAULT_MIN_IDLE;

        /**
         * 对象池存储空闲对象是使用的LinkedBlockingDeque，它本质上是一个支持FIFO和FILO的双向的队列，
         * common-pool2中的LinkedBlockingDeque不是Java原生的队列，而有common-pool2重新写的一个双向队列。
         * 如果为true，表示使用FIFO获取对象。
         * 默认值是true
         */
        private boolean lifo = DEFAULT_LIFO;
        /**
         * common-pool2实现的LinkedBlockingDeque双向阻塞队列使用的是Lock锁。
         * 这个参数就是表示在实例化一个LinkedBlockingDeque时，是否使用lock的公平锁。
         * 默认值是false
         */
        private boolean fairness = DEFAULT_FAIRNESS;
        /**
         * 当没有空闲连接时，获取一个对象的最大等待时间。如果这个值小于0，则永不超时，一直等待，直到有空闲对象到来。
         * 如果大于0，则等待maxWaitMillis长时间，如果没有空闲对象，将抛出NoSuchElementException异常。
         * 可以根据需要自己调整，单位是毫秒。
         * 默认值是5000L
         */
        private long maxWaitMillis = DEFAULT_MAX_WAIT_MILLIS;
        /**
         * 对象最小的空闲时间。如果为小于等于0，最Long的最大值，如果大于0，当空闲的时间大于这个值时，执行移除这个对象操作。
         * 可以避免连接泄漏
         * 默认值是-1
         */
        private long minEvictableIdleTimeMillis = DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
        /**
         * shutdown驱逐线程的超时时间。当创建驱逐线(evictor)程时，如发现已有一个evictor正在运行则会停止该evictor，
         * evictorShutdownTimeoutMillis表示当前线程需等待多长时间让ScheduledThreadPoolExecutor停止该evictor线程。
         * (evictor继承自TimerTask，由ScheduledThreadPoolExecutor进行调度)
         * 默认值是10000L，即10秒
         */
        private long evictorShutdownTimeoutMillis = DEFAULT_EVICTOR_SHUTDOWN_TIMEOUT_MILLIS;
        /**
         * 对象最小的空间时间，如果小于等于0，取Long的最大值，如果大于0，当对象的空闲时间超过这个值，
         * 并且当前空闲对象的数量大于最小空闲数量(minIdle)时，执行移除操作。
         * 这个和上面的minEvictableIdleTimeMillis的区别是，它会保留最小的空闲对象数量。而上面的不会，是强制性移除的。
         * 默认值是1000L * 60L * 30L，即30分钟
         */
        private long softMinEvictableIdleTimeMillis = DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
        /**
         * 检测空闲对象线程每次检测的空闲对象的数量。如果这个值小于0，则每次检测的空闲对象数量等于当前空闲对象数量除以这个值的绝对值，并对结果向上取整。
         * 默认值是3
         */
        private int numTestsPerEvictionRun = DEFAULT_NUM_TESTS_PER_EVICTION_RUN;
        /**
         * 在创建对象时检测对象是否有效，true是，默认值是false。做了这个配置会降低性能。
         * 默认值是false
         */
        private boolean testOnCreate = DEFAULT_TEST_ON_CREATE;
        /**
         * 在从对象池获取对象时是否检测对象有效，true是；默认值是false。做了这个配置会降低性能。
         * 默认值是false
         */
        private boolean testOnBorrow = DEFAULT_TEST_ON_BORROW;
        /**
         * 在向对象池中归还对象时是否检测对象有效，true是，默认值是false。做了这个配置会降低性能。
         * 默认值是false
         */
        private boolean testOnReturn = DEFAULT_TEST_ON_RETURN;
        /**
         * 在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性。
         * true是，默认值是false。建议配置为true，不影响性能，并且保证安全性。
         * 默认值是true
         */
        private boolean testWhileIdle = DEFAULT_TEST_WHILE_IDLE;
        /**
         * 空闲对象检测线程的执行周期，即多长时间执行一次空闲对象检测。
         * 单位是毫秒数。如果小于等于0，则不执行检测线程。
         * 默认值是1000L * 60L * 10L，即10分钟
         */
        private long timeBetweenEvictionRunsMillis = DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;
        /**
         * 当对象池没有空闲对象时，新的获取对象的请求是否阻塞。true阻塞。
         * 默认值是true
         */
        private boolean blockWhenExhausted = DEFAULT_BLOCK_WHEN_EXHAUSTED;
        /**
         * 是否注册JMX
         * 默认值是true
         */
        private boolean jmxEnabled = DEFAULT_JMX_ENABLE;
        /**
         * JMX前缀
         * 默认值是pool
         */
        private String jmxNamePrefix = DEFAULT_JMX_NAME_PREFIX;
        /**
         * 使用jmxNameBase + jmxNamePrefix + i来生成ObjectName
         * 默认值是sftp
         */
        private String jmxNameBase = DEFAULT_JMX_NAME_BASE;

        public Pool() {
            super();
        }
    }

}
