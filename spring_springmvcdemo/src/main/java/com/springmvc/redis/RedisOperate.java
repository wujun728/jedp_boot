package com.springmvc.redis;


import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by zsq on 2017/2/16.
 * java 使用 jedis 操作redis
 */
public class RedisOperate {

    private static Jedis jedis = RedisUtils.getJedis();

    /***
     * redis 存储字符串
     */
    @Test
    public void testString() {
        //添加数据
        jedis.set("name", "zsq");
        System.out.println(jedis.get("name"));

        jedis.append("name", " is a better man");  //拼接字符串
        System.out.println(jedis.get("name"));

        jedis.del("name");  //删除某个键
        System.out.println(jedis.get("name"));

        jedis.mset("name", "zsq", "age", "20", "addr", "hangzhou");
        jedis.incr("age"); //对age进行+1操作
        System.out.println(jedis.get("name")+"-"+jedis.get("age")+"-"+jedis.get("addr"));
    }

    /**
     * redis 操作 map
     */
    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "HongWan");
        map.put("age", "20");
        jedis.hmset("user", map);

        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数

        List<String> list = jedis.hmget("user", "id", "name", "age");
        System.out.println(list);
        list.stream().forEach(System.out::println);  //stream 的 遍历方式

        System.out.println("------------------------------------");


        jedis.hdel("user", "age");

        System.out.println(jedis.hmget("user", "age"));  //因为删除了,所有返回null
        System.out.println(jedis.hlen("user"));  //Redis 中key user 的长度
        System.out.println(jedis.exists("user")); //是否存在 user
        System.out.println(jedis.hkeys("user"));  //返回map 中所有的key
        System.out.println(jedis.hvals("user"));  //返回map 中所有的value

        Iterator<String> iterator = jedis.hkeys("user").iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key +"--"+jedis.hget("user", key));
        }

        System.out.println(jedis.hgetAll("user"));  //获取 map 包括键值 {name=zsq, addr=hangzhou, id=1}
    }

    /**
     * redis 操作 list (Redis 的list 是双向链表)
     */
    @Test
    public void testList() {
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));

        // 先向key java framework中存放三条数据 在 list 的头部添加 元素
        jedis.lpush("java framework", "spring");
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");

        // 再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("java framework", 0, -1));

        jedis.del("java framework");

        // 在list的 尾部添加元素
        jedis.rpush("java framework","spring");
        jedis.rpush("java framework","struts");
        jedis.rpush("java framework","hibernate");
        System.out.println(jedis.lrange("java framework",0, -1));
    }

    /**
     * Redis 操作 set
     */
    @Test
    public void testSet() {
        jedis.sadd("language", "java");
        jedis.sadd("language", "c");
        jedis.sadd("language", "c++");
        jedis.sadd("language", "c#");
        jedis.sadd("language", "php");
        jedis.sadd("language", "php");
        jedis.sadd("language", "python");

        //删除 python
        jedis.srem("language", "python");

        System.out.println(jedis.smembers("language")); //获取所有的value
        System.out.println(jedis.sismember("language", "python")); // 判断python 是否是 set中集合
        System.out.println(jedis.srandmember("language")); // 随机返回 key 中的一个元素
        System.out.println(jedis.scard("language"));  //返回集合的元素个数
    }

    /**
     * Redis 操作 sorted set
     */
    @Test
    public void testSortedSet() {
        jedis.zadd("yuyan", 1, "java");
        jedis.zadd("yuyan", 2, "c");
        jedis.zadd("yuyan", 3, "c++");
        jedis.zadd("yuyan", 4, "c#");
        jedis.zadd("yuyan", 5, "php");
        jedis.zadd("yuyan", 6, "php");
        jedis.zadd("yuyan", 7, "python");

        jedis.zrem("yuyan", "python");

        System.out.println(jedis.zrange("yuyan", 0, -1));
        System.out.println(jedis.zrangeWithScores("yuyan", 0, -1));

        Set<Tuple> tupleSet = jedis.zrangeWithScores("yuyan", 0, -1);
        Iterator<Tuple> tupleIterator = tupleSet.iterator();
        while (tupleIterator.hasNext()) {
            Tuple tuple = tupleIterator.next();
            System.out.println(tuple.getScore()+"-"+tuple.getElement());
        }


        System.out.println(jedis.zrangeByScoreWithScores("yuyan", 2, 4));  //按score 区间返回元素值, 并带上score


        System.out.println(jedis.zrangeByScore("yuyan", "2", "4"));  //按 score 取数据

        System.out.println(jedis.zcard("yuyan"));
    }

    @Test
    public void test() {
        jedis.del("a");
        jedis.rpush("a", "1");

        jedis.lpush("a", "6");
        jedis.lpush("a", "3");
        jedis.lpush("a", "9");

        System.out.println(jedis.lrange("a", 0, -1));  // [9, 3, 6, 1]
        System.out.println(jedis.sort("a"));  // [1,3,6,9]
        System.out.println(jedis.lrange("a", 0, -1));  //[9, 3, 6, 1]

       //  System.out.println(jedis.scard("a"));  scard 命令用于在集合set中操作
    }

    @Test
    public void testDemo() throws UnsupportedEncodingException {
        String[] element = {"java", "c", "c++", "c#", "php"};
        for (String str : element) {

            System.out.println("byte default (ISO-8859-1) arrays: "+ str.getBytes());  //字节数组对象
            System.out.println("byte UTF-8 arrays: "+ str.getBytes("UTF-8"));

            System.out.println("-----------------------------------------------------");

            //转为String后,以下两者相同,因为是同一个数组对象,并且String 常量
            System.out.println("transfer string (ISO-8859-1): "+ Arrays.toString(str.getBytes()));
            System.out.println("transfer string (UTF-8): "+ Arrays.toString(str.getBytes("UTF-8")));

            System.out.println("-----------------------------------------------------");
        }

    }

    /*public static void main(String[] args) {
        Jedis jedis = RedisUtil.getJedis();
        jedis.set("jedis", "java operate redis");
        String key = jedis.get("jedis");
        System.out.println(key);1
        RedisUtil.close(jedis);
    }*/
}

