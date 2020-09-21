package data.structure;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

/**
 * 演示jedis操作列表数据结构的操作
 * @author shenzhanwang
 *
 */
public class Listdemo {
	public static void main(String[] args) {
		Jedis conn = new Jedis("192.168.197.128");
        conn.select(15);
        // 往列表尾部追加元素
        conn.rpush("bbq", "qq");
        conn.rpush("bbq", "sad");
        conn.rpush("bbq", "dsf");
        conn.rpush("bbq", "rf");
        conn.rpush("bbq", "vf");
        conn.rpush("bbq", "asdas");
        // 往列表头部追加元素
        conn.lpush("bbq", "dy");
        // 展示整个列表
        System.out.println(conn.lrange("bbq", 0, -1));
        // 删除头部元素
        conn.lpop("bbq");
        // 删除尾部元素
        conn.rpop("bbq");
        System.out.println(conn.lrange("bbq", 0, -1));
        // 使用sort按字典顺序进行排序
        SortingParams sortingParameters = new SortingParams();
        sortingParameters.alpha();
        System.out.println(conn.sort("bbq",sortingParameters));
	}
}
