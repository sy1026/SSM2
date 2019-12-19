package net.wanhe.service;

import net.wanhe.pojo.Permission;
import net.wanhe.pojo.Role;
import net.wanhe.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/12/17.
 */

public interface UserServiceI {
    void addUser(User user);

    User getUserByName(String username);

    String shiroMD5(String password,String salt);



//    List<User> getAllUsers();
}
