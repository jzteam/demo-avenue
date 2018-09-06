package com.example.demoavenue.controller;

import cn.jzteam.avenue.dao.query.QueryCondition;
import com.alibaba.fastjson.JSON;
import com.example.demoavenue.dao.IUserKycInfoDao;
import com.example.demoavenue.entities.UserKycInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserKycInfoDao dao;

    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @RequestMapping("/infos/{kycId}")
    public String infos(@PathVariable("kycId") Long kycId){

//        final UserKycInfoEntity userKycInfoEntity = dao.selectById(kycId);

        final QueryCondition queryCondition = new QueryCondition();
        queryCondition.setHasCount(true);
        queryCondition.setPageSize(10);

        final List<UserKycInfoEntity> userKycInfoEntities = dao.selectList(queryCondition);

        return JSON.toJSONString(userKycInfoEntities);

    }
}
