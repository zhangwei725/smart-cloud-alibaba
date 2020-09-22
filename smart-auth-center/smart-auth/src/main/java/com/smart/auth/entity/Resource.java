package com.smart.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
    * 后台资源表
    */
@Data
@TableName(value = "`resource`")
public class Resource {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 资源名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 资源URL
     */
    @TableField(value = "url")
    private String url;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 资源分类ID
     */
    @TableField(value = "category_id")
    private Long categoryId;

    public static final String COL_ID = "id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_NAME = "name";

    public static final String COL_URL = "url";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CATEGORY_ID = "category_id";
}