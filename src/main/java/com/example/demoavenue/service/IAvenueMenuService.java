package com.example.demoavenue.service;

import cn.jzteam.avenue.swift.service.IService;
import com.example.demoavenue.dao.entity.AvenueMenuEntity;
import com.example.demoavenue.form.AvenueMenuForm;
import com.example.demoavenue.vo.AvenueMenuVo;

public interface IAvenueMenuService extends IService<AvenueMenuEntity, Long> {
    AvenueMenuVo getMenuTree();

    Long save(AvenueMenuForm form);
}
