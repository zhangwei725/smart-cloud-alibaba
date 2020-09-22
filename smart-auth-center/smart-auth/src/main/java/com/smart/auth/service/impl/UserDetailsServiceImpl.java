package com.smart.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.smart.auth.entity.Admin;
import com.smart.auth.service.UserService;
import com.smart.auth.vo.AdminVo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 自定义用户登录
 *
 * @author zhangwei
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = userService.findAdminByName(username);
        assert admin != null;
        AdminVo adminVo = new AdminVo();
        BeanUtil.copyProperties(admin, adminVo);
        return adminVo;
    }
}
