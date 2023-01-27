package com.rayce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class loginController {

    @RequestMapping("/user/login")
    public String login(Model model, HttpSession session,
                        @RequestParam("username") String username ,
                        @RequestParam("password") String password){
        if(username !=null && "123456".equals(password)){
            System.out.println("username= " + username);
            System.out.println("密码：" + password);
            //将值（用户名）传进来
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","用户名或密码错误！");
            return "index";
        }
    }
    @RequestMapping("/user/LogOut")
    public String LogOut(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }
}
