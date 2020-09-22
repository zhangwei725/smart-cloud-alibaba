package com.smart.auth.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

/**
 * WebSecurityConfigurerAdapter
 * UserDetailService
 * UserDetail
 * GrantedAuthority
 * 自定义认证
 * 其它可选功能
 */


@Data
@ApiModel("角色信息")
public class RoleVo implements GrantedAuthority {
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;

    /**
     * 后台用户数量
     */

    private Integer adminCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;

    private Integer sort;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
