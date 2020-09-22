package com.smart.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
    * 后台菜单表
    */
@Data
@TableName(value = "menu")
public class Menu {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 菜单名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 菜单级数
     */
    @TableField(value = "level")
    private Integer level;

    /**
     * 菜单排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 前端名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 前端图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 前端隐藏
     */
    @TableField(value = "hidden")
    private Integer hidden;

    public static final String COL_ID = "id";

    public static final String COL_PARENT_ID = "parent_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_TITLE = "title";

    public static final String COL_LEVEL = "level";

    public static final String COL_SORT = "sort";

    public static final String COL_NAME = "name";

    public static final String COL_ICON = "icon";

    public static final String COL_HIDDEN = "hidden";
}