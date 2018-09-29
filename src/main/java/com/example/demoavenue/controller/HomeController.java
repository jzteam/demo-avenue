package com.example.demoavenue.controller;

import com.example.demoavenue.service.IAvenueMenuService;
import com.example.demoavenue.vo.AvenueMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private IAvenueMenuService avenueMenuService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        // 查询所有菜单
        final AvenueMenuVo rootMenu = avenueMenuService.getMenuTree();
        request.setAttribute("rootMenu", rootMenu);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
