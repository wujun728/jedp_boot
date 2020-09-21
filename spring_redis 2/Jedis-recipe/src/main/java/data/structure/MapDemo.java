package data.structure;

import redis.clients.jedis.Jedis;

public class MapDemo {
	public static void main(String[] args) {
		Jedis conn = new Jedis("192.168.197.128");
        conn.select(15);
        // 创建一个hashmap
        conn.hset("hehe", "ww", "asd");
        conn.hset("hehe", "tencent", "liangbo");
        conn.hset("hehe", "baidu", "liyan");
        conn.hset("hehe", "ali", "mayun");
        // 读取hashmap
        System.out.println(conn.hget("hehe", "ww"));
        // 读取map的长度
        System.out.println(conn.hlen("hehe"));
        // 删除map
        conn.hdel("hehe", "tencent");
        // 遍历key
        System.out.println(conn.hkeys("hehe"));
        // 遍历value
        System.out.println(conn.hvals("hehe"));
        // 遍历map
        System.out.println(conn.hgetAll("hehe"));
	}
}
