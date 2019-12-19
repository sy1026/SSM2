package net.wanhe.service.impl;

import net.wanhe.mapper.RoleMapper;
import net.wanhe.pojo.Role;
import net.wanhe.service.RoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/12/17.
 */
@Service
public class RoleServiceImpl implements RoleServiceI {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getRolesByName(String username) {
        List<Role> roleList = roleMapper.getRolesByName(username);
        return roleList;
    }
}
