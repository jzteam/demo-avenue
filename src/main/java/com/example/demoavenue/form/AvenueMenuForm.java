package com.example.demoavenue.form;

import cn.jzteam.avenue.dao.annotations.AvenueEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AvenueEntity(dbName = "okcoin_user_local", tableName = "avenue_menu")
public class AvenueMenuForm implements Serializable {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 父菜单id
     */
    private Long parentId;
    /**
     * 菜单类型
     */
    private Integer type;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 菜单图片
     */
    private String iconfont;
    /**
     * 菜单链接
     */
    private String url;
    /**
     * 删除标记
     */
    private Integer deleteFlag;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建时间
     */
    private Date modifyTime;

}
