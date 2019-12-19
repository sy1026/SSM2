package net.wanhe.task;

import net.wanhe.pojo.Student;
import net.wanhe.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2019/12/18.
 */

public class MyTask1 {
    @Autowired
    private StudentServiceI studentServiceI;
    @Autowired
    private JedisPool jedisPool;
//    @Scheduled(cron = "0/5 * * * * ?")
    public void task1(){
        System.out.println("任务一被执行");
    }

//    @Scheduled(cron = "0/5 * * * * ?")
    public void task2(){
        List<Student> stus = studentServiceI.getAllStus();
        Jedis jedis = jedisPool.getResource();
        for (Student student : stus) {
            jedis.zadd("zset",student.getAge(), String.valueOf(student));
        }
        Set<String> zset = jedis.zrevrange("zset", 0, 9);
        System.out.println(zset);
    }
}
