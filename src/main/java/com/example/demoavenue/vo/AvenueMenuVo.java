package com.example.demoavenue.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AvenueMenuVo {

    private Long id;
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
     * 菜单图片
     */
    private String iconfont;
    /**
     * 菜单链接
     */
    private String url;
    /**
     * 子菜单列表
     */
    private List<AvenueMenuVo> subMenuList = new ArrayList<>();


}
