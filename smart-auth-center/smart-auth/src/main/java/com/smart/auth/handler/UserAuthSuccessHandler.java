package com.smart.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.auth.config.JWTConfigProperties;
import com.smart.auth.utils.JwtTokenUtil;
import com.smart.auth.utils.ResponseUtils;
import com.smart.auth.vo.AdminVo;
import com.smart.common.dto.BaseResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录成功
 *
 * @author zhangwei
 */

@Component
public class UserAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    JwtTokenUtil tokenUtil;
    @Resource
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AdminVo adminVo = (AdminVo) authentication.getPrincipal();
        String token = tokenUtil.generate(adminVo);
        ResponseUtils.responseToJson(httpServletResponse, objectMapper.writeValueAsString(BaseResult.success(token)));
    }
}
