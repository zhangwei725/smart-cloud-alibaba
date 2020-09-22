package com.smart.auth.fliter;

import com.smart.auth.config.JWTConfigProperties;
import com.smart.auth.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.spec.ECField;
import java.util.ArrayList;

/**
 * JWT接口请求过滤器
 * 主要对token是否过期和合法做校验
 * 进行鉴权操作
 */
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {
    @Resource
    JwtTokenUtil jwtTokenUtil;
    @Resource
    private JWTConfigProperties jwtConfigProperties;
    @Resource
    UserDetailsService userDetailsService;

    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String token = request.getHeader(jwtConfigProperties.getTokenHeader());
            if (token == null || token.startsWith(jwtConfigProperties.getTokenPrefix())) {
                chain.doFilter(request, response);
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
            super.doFilterInternal(request, response, chain);
            chain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            System.out.println("token过期");
        } catch (Exception e) {
            System.out.println("token 无效");
        }

    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String username = jwtTokenUtil.getUsername(token);
        return username != null ? new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>()) : null;
    }
}
