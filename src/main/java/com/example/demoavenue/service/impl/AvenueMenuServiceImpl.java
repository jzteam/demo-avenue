package com.example.demoavenue.service.impl;

import cn.jzteam.avenue.dao.query.QueryCondition;
import cn.jzteam.avenue.swift.exception.BizException;
import cn.jzteam.avenue.swift.service.impl.AbstractServiceImpl;
import com.example.demoavenue.entities.AvenueMenuEntity;
import com.example.demoavenue.enums.EnumCommonError;
import com.example.demoavenue.form.AvenueMenuForm;
import com.example.demoavenue.service.IAvenueMenuService;
import com.example.demoavenue.vo.AvenueMenuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AvenueMenuServiceImpl extends AbstractServiceImpl<AvenueMenuEntity, Long> implements IAvenueMenuService {

    @Override
    public AvenueMenuVo getMenuTree() {
        AvenueMenuVo result = new AvenueMenuVo();
        final List<AvenueMenuEntity> menuList = this.selectList(new QueryCondition());
        if(CollectionUtils.isEmpty(menuList)){
            return result;
        }

        Map<Long, AvenueMenuVo> voMap = new HashMap<>();
        menuList.stream().forEach(x->{
            final AvenueMenuVo tempVo = new AvenueMenuVo();
            BeanUtils.copyProperties(x, tempVo);
            voMap.put(tempVo.getId(), tempVo);
        });

        for(AvenueMenuVo vo : voMap.values()){
            final AvenueMenuVo parentVo = voMap.get(vo.getParentId());
            if(parentVo == null){
                // 只有根菜单没有父菜单
                result = vo;
                continue;
            }
            parentVo.getSubMenuList().add(vo);
        }

        return result;
    }

    @Override
    public Long save(AvenueMenuForm form) {
        if(form == null){
            throw new BizException(EnumCommonError.BIZ_PARAMS_EMPTY);
        }
        final AvenueMenuEntity entity = new AvenueMenuEntity();
        BeanUtils.copyProperties(form, entity);

        if(entity.getId() == null){
            return this.insert(entity);
        }else {
            this.update(entity);
            return entity.getId();
        }
    }
}
