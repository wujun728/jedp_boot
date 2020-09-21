package transaction;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * redis的事务用于执行一组命令，中途不被其他的命令所打断
 * 这种一次性发送多个命令，然后等待所有回复的做法称为流水线（pipelining）,它
 * 减少了通信次数可以提高性能
 * @author shenzhanwang
 */
public class TransactionDemo {
	public static void main(String[] args) {
		Jedis conn = new Jedis("192.168.197.128");
        conn.select(15);
        conn.set("tracn", "5");
        // 开启watch命令，若tracn的值发生变化，则事务提交失败
        String watch = conn.watch("tracn");
        // 开启事务
        Transaction transaction2=conn.multi();
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        transaction2.set("tracn","100");
        // 提交事务
        List<Object> list = transaction2.exec();
        System.out.println(list);
        System.out.println(conn.get("tracn"));
	}
}
