package com.springmvc.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zsq on 2017/2/16.
 * 获取 Redis 连接操作类
 */
public class RedisUtils {

    private static String HOST = "127.0.0.1";    //Redis 主机地址

    private static int  PORT = 6379;    //Redis 端口号

    private static String AUTH = "6633";   //访问密码

    // 可用连接实例的最大数据, 默认为8
    // 如果赋值为-1,表示不限制; 如果pool已经分配了max_active个jedis实例,则此时pool的状态为exhausted(耗尽)
    private static int MAX_ACTIVE = 1024;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例,默认值也是8
    private static int MAX_IdLE = 200;

    // 等待可用连接的最大时间, 单位是毫秒,默认值为-1,表示永不超时.如果超过等待时间,则直接超出JedisConnectionException
    private static int MAX_WAIT = 10000;

    private static int TIME_OUT = 10000;

    // 在borrow 一个jedis 实例时,是否提前进行validate 操作; 如果为true,则得到的Jedis实例是可用的
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool;

    /**
     * redis过期时间,以秒为单位
     */
    public final static int EXRP_HOUR = 60*60;          //一小时
    public final static int EXRP_DAY = 60*60*24;        //一天
    public final static int EXRP_MONTH = 60*60*24*30;   //一个月


    //初始化 Redis 连接池 在类加载时 只初始化一次.
    static {
        try{
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IdLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, HOST, PORT, TIME_OUT,  AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 jedis 实例  单例模式 懒汉式, 线程安全
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if  (jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                return jedis;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 释放 jedis 资源
     */
    public static void close(final Jedis jedis){
        if (jedisPool != null && jedis != null) {
            //jedisPool.returnResource(jedis); 方法已过期
            jedis.close();  //逻辑和 returnResource()逻辑相同
        }
    }

    /**********************另一种初始化 jedis 的方法 *****************************************************/

    /* java端在使用jedispool 连接redis的时候，在高并发的时候经常卡死，或报连接异常，JedisConnectionException，或者getResource 异常等各种问题

    在使用jedispool 的时候一定要注意两点

    1。 在获取 jedisPool和jedis的时候加上线程同步，保证不要创建过多的jedispool 和 jedis

    2。 用完Jedis实例后需要返还给JedisPool

    */

   /* private static void initPool() {
        try{
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IdLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, HOST, PORT, TIME_OUT,  AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //多线程环境中 同步初始化
    private synchronized static void getJedisPool() {
        if (jedisPool == null) {
            initPool();
        }
    }

    public synchronized static Jedis getJedis_2() {
        if (jedisPool == null) {
            initPool();
        }
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(jedis);
        }
        return jedis;
    }*/
}
