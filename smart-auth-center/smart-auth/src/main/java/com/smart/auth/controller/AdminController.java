package com.smart.auth.controller;

import com.smart.auth.dto.UserRegisterParam;
import com.smart.auth.entity.Menu;
import com.smart.auth.service.UserService;
import com.smart.common.dto.BaseResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = "AdminController", description = "后台用户管理")
public class AdminController {
    @Resource
    UserService userService;

    @PostMapping("/register")
    public BaseResult<Boolean> register(@RequestBody UserRegisterParam user) {
        boolean register = userService.register(user);
        return BaseResult.success(register);
    }

    @GetMapping("/menus")
    public BaseResult<List<Menu>> getMenus() {
        List<Menu> menus = userService.getMenus();
        return BaseResult.success(menus);
    }
}
