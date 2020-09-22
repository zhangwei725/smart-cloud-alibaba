package com.smart.auth.service;


import com.smart.auth.dto.UserRegisterParam;
import com.smart.auth.entity.Admin;
import com.smart.auth.entity.Menu;

import java.util.List;

public interface UserService {
    /**
     * 通过用户名查询用户信息
     *
     * @param username
     * @return
     */
    Admin findAdminByName(String username);

    boolean register(UserRegisterParam user);

    List<Menu> getMenus();
}

