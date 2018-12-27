package com.example.demoavenue.controller;

import com.alibaba.fastjson.JSON;
import com.example.demoavenue.service.IAvenueMenuService;
import com.example.demoavenue.vo.AvenueMenuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
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

    @RequestMapping("/")
    @ResponseBody
    public Map test(){
        Map<String, String> data = new HashMap<>();
        data.put("test", "hello world ngrok!");
        log.info("webhook: {}", JSON.toJSONString(data));
        return data;
    }
}
