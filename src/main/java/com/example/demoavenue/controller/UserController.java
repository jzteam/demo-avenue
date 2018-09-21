package com.example.demoavenue.controller;

import cn.jzteam.avenue.dao.query.QueryCondition;
import com.example.demoavenue.entities.UserKycInfoEntity;
import com.example.demoavenue.form.UserKycInfoForm;
import com.example.demoavenue.query.UserKycInfoQuery;
import com.example.demoavenue.service.IUserKycInfoService;
import com.example.demoavenue.vo.UserKycInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserKycInfoService userKycInfoService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @RequestMapping("/infos/{kycId}")
    public List<UserKycInfoEntity> infos(@PathVariable("kycId") Long kycId){

        final QueryCondition queryCondition = new QueryCondition();
        queryCondition.setHasCount(true);
        queryCondition.setPageSize(10);

        final List<UserKycInfoEntity> userKycInfoEntities = userKycInfoService.selectList(queryCondition);

        return userKycInfoEntities;

    }

    /**
     * 增加/更新
     * @return
     */
    @PostMapping("/save")
    public Long save(@RequestBody UserKycInfoForm form){
        final UserKycInfoEntity entity = new UserKycInfoEntity();
        BeanUtils.copyProperties(form, entity);
        return userKycInfoService.save(entity);
    }

    /**
     * 删除
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        userKycInfoService.delete(id);
    }

    /**
     * 查询详情
     * @return
     */
    @GetMapping("/get/{id}")
    public UserKycInfoVo get(@PathVariable("id") Long id){
        final UserKycInfoEntity userKycInfoEntity = userKycInfoService.selectById(id);
        final UserKycInfoVo vo = new UserKycInfoVo();
        if(userKycInfoEntity == null){
            return vo;
        }
        BeanUtils.copyProperties(userKycInfoEntity, vo);
        return vo;
    }

    /**
     * 列表
     * @return
     */
    @PostMapping("/list")
    public List<UserKycInfoVo> list(@RequestBody UserKycInfoQuery query){
        final List<UserKycInfoEntity> entityList = userKycInfoService.selectList(query);
        List<UserKycInfoVo> result = new ArrayList<>();
        if(CollectionUtils.isEmpty(entityList)){
            return result;
        }
        entityList.stream().forEach(x->{
            final UserKycInfoVo vo = new UserKycInfoVo();
            BeanUtils.copyProperties(x, vo);
            result.add(vo);
        });
        return result;
    }
}
