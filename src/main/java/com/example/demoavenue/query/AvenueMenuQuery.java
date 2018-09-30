package com.example.demoavenue.query;

import cn.jzteam.avenue.dao.query.QueryCondition;
import lombok.Data;

import java.util.Date;

@Data
public class AvenueMenuQuery extends QueryCondition {
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
     * 创建时间 开始
     */
    private Date begin_createTime;
    /**
     * 创建时间 截止
     */
    private Date end_createTime;
    /**
     * 更新时间 开始
     */
    private Date begin_modifyTime;
    /**
     * 更新时间 截止
     */
    private Date end_modifyTime;

}
