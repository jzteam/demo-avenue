package com.example.demoavenue.controller;

import cn.jzteam.avenue.dao.query.QueryCondition;
import com.example.demoavenue.entities.AvenueMenuEntity;
import com.example.demoavenue.form.AvenueMenuForm;
import com.example.demoavenue.service.IAvenueMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/common/menu")
public class MenuController {

    @Autowired
    private IAvenueMenuService avenueMenuService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request){

        final List<AvenueMenuEntity> avenueMenuEntities = avenueMenuService.selectList(new QueryCondition());
        request.setAttribute("list", avenueMenuEntities);
        return "menu/menu-list";

    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, HttpServletRequest request){

        final AvenueMenuEntity avenueMenuEntity = avenueMenuService.selectById(id);
        request.setAttribute("vo", avenueMenuEntity);
        return "menu/menu-edit";

    }

    @GetMapping("/toAdd")
    public String toAdd(HttpServletRequest request){

        return "menu/menu-add";

    }

    @GetMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id") Long id, HttpServletRequest request){

        final AvenueMenuEntity avenueMenuEntity = avenueMenuService.selectById(id);
        request.setAttribute("vo", avenueMenuEntity);
        return "menu/menu-edit";

    }

    @PostMapping("/save")
    @ResponseBody
    public void save(AvenueMenuForm form, HttpServletRequest request){

        avenueMenuService.save(form);

    }

}
