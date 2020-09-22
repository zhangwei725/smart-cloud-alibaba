package com.smart.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.errorprone.annotations.Var;
import com.smart.auth.dto.UserRegisterParam;
import com.smart.auth.entity.Admin;
import com.smart.auth.entity.Menu;
import com.smart.auth.mapper.AdminMapper;
import com.smart.auth.service.UserService;
import com.smart.auth.vo.AdminVo;
import com.smart.common.dto.ResultCodeEnum;
import com.smart.common.exception.ServiceException;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zhangwei
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    AdminMapper adminMapper;
    @Resource
    BCryptPasswordEncoder encoder;

//    @Override
//    public Admin findAdminByName(String username) {
//        Admin admin = adminMapper.selectOne(Wrappers.lambdaQuery(Admin.class)
//                .eq(Admin::getUsername, username));
//        return admin;
//    }

    @Override
    public Admin findAdminByName(String username) {
        return Optional.ofNullable(adminMapper.selectOne(Wrappers.lambdaQuery(Admin.class)
                .eq(Admin::getUsername, username)))
                .orElseThrow(() ->
                        new ServiceException(ResultCodeEnum.ACCOUNT_LOGIN_ERROR)
                );
    }

    @Override
    public boolean register(UserRegisterParam user) {
        Admin admin = new Admin();
        user.setPassword(encoder.encode(user.getPassword()));
        BeanUtils.copyProperties(user, admin);
        int insert = adminMapper.insert(new Admin());
        return insert != 0;
    }

    @Override
    public List<Menu> getMenus() {
        return new ArrayList<>();
    }
}
