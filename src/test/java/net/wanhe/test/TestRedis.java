package net.wanhe.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2019/12/13.
 */
public class TestRedis {

    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.set("username","zhangsan");
        String username = jedis.get("username");
        System.out.println(username);
        jedis.close();
    }

    @Test
    public void testRedis2(){
        JedisPool jedisPool = new JedisPool("127.0.0.1",6379);
        Jedis jedis = jedisPool.getResource();
        jedis.set("username","lisi");
        String username = jedis.get("username");
        System.out.println(username);
        jedis.close();
        jedisPool.close();
    }
}
