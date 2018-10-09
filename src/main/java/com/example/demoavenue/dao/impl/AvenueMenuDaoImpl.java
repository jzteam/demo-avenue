package com.example.demoavenue.dao.impl;

import cn.jzteam.avenue.dao.AbstractDao;
import com.example.demoavenue.dao.IAvenueMenuDao;
import com.example.demoavenue.dao.entity.AvenueMenuEntity;
import org.springframework.stereotype.Repository;

@Repository
public class AvenueMenuDaoImpl extends AbstractDao<AvenueMenuEntity, Long> implements IAvenueMenuDao {
}
