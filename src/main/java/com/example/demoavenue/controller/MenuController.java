package com.example.demoavenue.controller;

import cn.jzteam.avenue.dao.query.QueryCondition;
import com.example.demoavenue.entities.AvenueMenuEntity;
import com.example.demoavenue.form.AvenueMenuForm;
import com.example.demoavenue.service.IAvenueMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/common")
public class MenuController {

    @Autowired
    private IAvenueMenuService avenueMenuService;

    @RequestMapping("/menu/list")
    public String listMenu(HttpServletRequest request){

        final List<AvenueMenuEntity> avenueMenuEntities = avenueMenuService.selectList(new QueryCondition());
        request.setAttribute("list", avenueMenuEntities);
        return "menu/menu-list";

    }

    @GetMapping("/menu/detail/{id}")
    public String detailMenu(@PathVariable("id") Long id, HttpServletRequest request){

        final AvenueMenuEntity avenueMenuEntity = avenueMenuService.selectById(id);
        request.setAttribute("detail", avenueMenuEntity);
        return "menu/menu-edit";

    }

    @PostMapping("/menu/edit")
    public String editMenu(AvenueMenuForm form, HttpServletRequest request){

        avenueMenuService.editForForm(form);

        return "forward:/common/menu/list";

    }

}
