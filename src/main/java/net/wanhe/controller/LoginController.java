package net.wanhe.controller;

import net.wanhe.pojo.User;
import net.wanhe.service.UserServiceI;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2019/12/17.
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserServiceI userServiceI;
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("register")
    public String register(User user){
        System.out.println("a");
        userServiceI.addUser(user);
        return "success";
    }

    @RequestMapping("check")
    public String check(User user,String rememberme){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        if("rememberme".equals(rememberme)){
            token.setRememberMe(true);
        }

        subject.login(token);
        return "redirect:/student/getAllStus";
    }
}
