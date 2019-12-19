package net.wanhe.mapper;

import net.wanhe.pojo.Permission;
import net.wanhe.pojo.Role;
import net.wanhe.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2019/12/17.
 */
public interface UserMapper {
    void addUser(User user);

    User getUserByName(String username);

    List<User> getAllUsers();



}
