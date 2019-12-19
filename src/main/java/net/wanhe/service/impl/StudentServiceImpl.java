package net.wanhe.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.wanhe.constant.PageConstant;
import net.wanhe.mapper.StudentMapper;
import net.wanhe.pojo.Student;
import net.wanhe.service.StudentServiceI;
import net.wanhe.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by Administrator on 2019/12/10.
 */
@Service
public class StudentServiceImpl implements StudentServiceI {
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAllStus() {
        List<Student> stus = studentMapper.getAllStus();
        return stus;
    }

    @Override
    public void delStu(Integer id) {
        studentMapper.delStu(id);
    }

    @Override
    public void addStu(Student student) {
        studentMapper.addStu(student);
    }

    @Override
    public Student getStuById(Integer id) {
        Student student = studentMapper.getStuById(id);
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public PageInfo<Student> getAllStusPage(Integer pageNum, String gender, String address) {
//        Jedis jedis = jedisPool.getResource();
//        String json = jedis.get("stus");
//        if(!StringUtils.isEmpty(json)){
//            //缓存中有
//            PageInfo<Student> pageInfo = JsonUtils.jsonToPojo(json, PageInfo.class);
//            System.out.println("从缓存中获取+++++++++++++++++++++");
//            return pageInfo;
//        }
        //缓存中没有
        PageHelper.startPage(pageNum, 5);
        List<Student> stus = studentMapper.getAllStusPage(gender, address);
        PageInfo<Student> pageInfo=new PageInfo<>(stus);
//        System.out.println("从数据库中获取+++++++++++++++++++++");
//        //缓存存储
//        String s = JsonUtils.objectToJson(pageInfo);
//        jedis.set("stus",s);
//        jedis.expire("stus",7200);
        return pageInfo;
    }
}
