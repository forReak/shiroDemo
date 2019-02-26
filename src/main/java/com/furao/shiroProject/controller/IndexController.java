package com.furao.shiroProject.controller;

import com.furao.shiroProject.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class IndexController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("info","");
        return "login";
    }

    @RequestMapping("/login")
    public String homePage(User user, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            model.addAttribute("info",e.getMessage());
            return "login";
        }
        model.addAttribute("userName",user.getUserName());
        model.addAttribute("password",user.getPassword());
        return "homePage";
    }

}
