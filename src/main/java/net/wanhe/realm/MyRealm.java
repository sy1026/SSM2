package net.wanhe.realm;

import net.wanhe.pojo.Permission;
import net.wanhe.pojo.Role;
import net.wanhe.pojo.User;
import net.wanhe.service.RoleServiceI;
import net.wanhe.service.UserServiceI;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2019/12/16.
 */
public class MyRealm extends AuthorizingRealm{

    @Autowired
    private UserServiceI userServiceI;

    @Autowired
    private RoleServiceI roleServiceI;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //        获取当前的用户
        String username = (String) principalCollection.getPrimaryPrincipal();

        List<String> roles = new ArrayList<>();
        List<String> permissons = new ArrayList<>();
        List<Role> roleList = roleServiceI.getRolesByName(username);
        for (Role role : roleList) {
            roles.add(role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                permissons.add(permission.getPermission());
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roles);
        simpleAuthorizationInfo.addStringPermissions(permissons);

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username= (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        User user = userServiceI.getUserByName(username);
        if(user==null){
            throw new RuntimeException("用户不存在");
        }
        String pwd = userServiceI.shiroMD5(password, user.getSalt());
        if(!pwd.equals(user.getPassword())){
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),getName());
    }

    @Override
    public String getName() {
        return "MyRealm";
    }
}
