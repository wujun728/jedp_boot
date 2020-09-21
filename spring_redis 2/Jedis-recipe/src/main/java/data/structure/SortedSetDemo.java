package data.structure;

import redis.clients.jedis.Jedis;

public class SortedSetDemo {
	public static void main(String[] args) {
		Jedis conn = new Jedis("192.168.197.128");
        conn.select(15);
        // 创建一个有序集合
        conn.zadd("sets",3 , "a");
        conn.zadd("sets",4 , "b");
        conn.zadd("sets",5 , "c");
        // 统计个数
        System.out.println(conn.zcard("sets"));
        // 给某个元素加分
        System.out.println(conn.zincrby("sets", 10, "a"));
        // 读取分值
        System.out.println(conn.zscore("sets", "a"));
        // 读取元素排名,最小的排名从0开始
        System.out.println(conn.zrank("sets", "a"));
        // 统计给定区间的分值数目
        System.out.println(conn.zcount("sets", 4, 11));
        // 移除某个元素
        conn.zrem("sets", "a");
        // 遍历有序集合
        System.out.println(conn.zrange("sets", 0, -1));
	}
}
