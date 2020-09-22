package com.smart.auth.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("用户显示信息类")
public class AdminVo implements UserDetails {
    private Long id;
    @ApiModelProperty("用户名")
    private String username;

    @JsonIgnore()
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("头像")

    private String icon;
    @ApiModelProperty("邮箱")

    private String email;
    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty(" 最后登录时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
    @ApiModelProperty("帐号启用状态：0->禁用；1->启用")
    private Integer status;

    private List<RoleVo> roles;
    // 返回用户所有角色的封装，一个Role对应一个GrantedAuthority

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 账号是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.status == 1;
    }

    /**
     * 账号凭证是否未过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否被启用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
