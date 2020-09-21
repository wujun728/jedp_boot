package data.structure;

import redis.clients.jedis.Jedis;

/**
 * 集合set的操作演示
 * @author shenzhanwang
 *
 */
public class Setdemo {
	public static void main(String[] args) {
		Jedis conn = new Jedis("192.168.197.128");
        conn.select(15);
        // 创建一个集合
        conn.sadd("xiaomi", "leijun","liuqi","xiaobai");
        // 移除集合元素
        conn.srem("xiaomi", "leijun");
        // 遍历集合元素
        System.out.println(conn.smembers("xiaomi"));
        
        conn.sadd("baidu", "liyanhong","zhangge","liuqi");
        // 返回差集
        System.out.println(conn.sdiff("xiaomi", "baidu"));
        // 返回交集
        System.out.println(conn.sinter("xiaomi", "baidu"));
        // 返回并集
        System.out.println(conn.sunion("xiaomi", "baidu"));
	}
}
