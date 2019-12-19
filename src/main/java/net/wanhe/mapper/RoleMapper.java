package net.wanhe.mapper;

import net.wanhe.pojo.Role;

import java.util.List;

/**
 * Created by Administrator on 2019/12/17.
 */
public interface RoleMapper {
    List<Role> getRolesByName(String username);
}
