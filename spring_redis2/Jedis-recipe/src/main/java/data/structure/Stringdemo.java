package data.structure;

import redis.clients.jedis.Jedis;

/**
 * 演示redis的string类型的操作
 * @author shenzhanwang
 *
 */
public class Stringdemo {
	public static void main(String[] args) {
		Jedis conn = new Jedis("192.168.197.128");
        conn.select(15);
        
        conn.set("wsz", "5");
        System.out.println(conn.get("wsz"));
        // 自增
        conn.incr("wsz");
        System.out.println(conn.get("wsz"));
        // 自减
        conn.decr("wsz");
        System.out.println(conn.get("wsz"));
        // 追加
        conn.append("wsz", "asddasfsad");
        System.out.println(conn.get("wsz"));
        // 求子串
        System.out.println(conn.substr("wsz",0,2));
        // 范围覆盖
        conn.setrange("wsz", 2, "nihao");
        System.out.println(conn.get("wsz"));
        
        // 演示过期时间使用,10秒过期
        conn.expire("wsz", 10);
        // 输出key的剩余时间
        System.out.println(conn.ttl("wsz"));
	}
}
