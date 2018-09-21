package com.example.demoavenue.query;

import cn.jzteam.avenue.dao.query.QueryCondition;
import lombok.Data;

import java.util.Date;

@Data
public class UserKycInfoQuery extends QueryCondition {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 认证模板id
     */
    private Integer templateId;
    /**
     * 国家编码
     */
    private String countryId;
    /**
     * 认证类型 0 个人  1 企业
     */
    private Integer type;
    /**
     * 身份证号/护照号（做唯一性校验用）
     */
    private String idCard;
    /**
     * 身份证有效日期
     */
    private Date expDate;
    /**
     * kyc级别 1-kyc级别1 2-kyc级别2 3-kyc级别3
     */
    private Integer level;
    /**
     * 用户认证状态 1-已提交（待审核） 2-已通过 3-已驳回
     */
    private Integer status;
    /**
     * 表单内容（json格式）
     */
    private String kycInfo;
    /**
     * 失败原因（可以展示给用户的）
     */
    private String failReason;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建时间
     */
    private Date updateTime;
}
