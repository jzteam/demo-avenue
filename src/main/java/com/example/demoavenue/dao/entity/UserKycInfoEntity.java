package com.example.demoavenue.dao.entity;

import cn.jzteam.avenue.dao.annotations.AvenueEntity;

import java.io.Serializable;
import java.util.Date;

@AvenueEntity(dbName = "okcoin_user_local", tableName = "user_kyc_info")
public class UserKycInfoEntity implements Serializable {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKycInfo() {
        return kycInfo;
    }

    public void setKycInfo(String kycInfo) {
        this.kycInfo = kycInfo;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
