package net.wanhe.service.impl;

import com.alibaba.druid.util.StringUtils;
import net.wanhe.mapper.UserMapper;
import net.wanhe.pojo.Permission;
import net.wanhe.pojo.Role;
import net.wanhe.pojo.User;
import net.wanhe.service.UserServiceI;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2019/12/17.
 */
@Service
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {

        if (user.getUsername() == null || "".equals(user.getUsername())) {
            System.out.println("aaa");
            throw new RuntimeException("用户名不能为空");
        }
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            System.out.println("aaa");
            throw new RuntimeException("密码不能为空");
        }
        List<User> list = userMapper.getAllUsers();

        for(User u:list){

            if(u.getUsername().equals(user.getUsername())){
                throw new RuntimeException("用户名重复");
            }
        }
        //盐值加密
        //生成盐值
        System.out.println("waaa");
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        user.setPassword(shiroMD5(user.getPassword(), salt));
        userMapper.addUser(user);


    }

    @Override
    public User getUserByName(String username) {
        User user = userMapper.getUserByName(username);
        return user;
    }

    @Override
    public String shiroMD5(String password, String salt) {
        //加密方式
        String algorithmName = "MD5";
        //salt转ByteSource型
        ByteSource saltSource = ByteSource.Util.bytes(salt);
        //加密次数
        int hashIterations = 2;
        Object obj = new SimpleHash(algorithmName, password, saltSource, hashIterations);
        return obj.toString();
    }



//    @Override
//    public List<User> getAllUsers() {
//        List<User> list = userMapper.getAllUsers();
//        return list;
//    }
}
