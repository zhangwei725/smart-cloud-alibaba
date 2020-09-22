package com.smart.auth.controller;

import com.smart.auth.dto.UserLoginParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhangwei
 */
@RestController
@ApiModel("用户登录接口")
public class LoginController {
    @PostMapping("/login")
    @ApiOperation(value = "登录以后返回jwt")
    public String login(@Validated UserLoginParam loginParam) {
        return "login";
    }
}
