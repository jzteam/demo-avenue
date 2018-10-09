package com.example.demoavenue.dao.impl;

import cn.jzteam.avenue.dao.AbstractDao;
import com.example.demoavenue.dao.IUserKycInfoDao;
import com.example.demoavenue.dao.entity.UserKycInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserKycInfoDaoImpl extends AbstractDao<UserKycInfoEntity, Long> implements IUserKycInfoDao {
}
