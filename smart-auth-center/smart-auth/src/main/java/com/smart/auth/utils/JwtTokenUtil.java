package com.smart.auth.utils;

import com.smart.auth.config.JWTConfigProperties;
import com.smart.auth.entity.Admin;
import com.smart.auth.vo.AdminVo;
import com.smart.common.dto.ResultCodeEnum;
import com.smart.common.exception.BaseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@Data
public class JwtTokenUtil implements Serializable {
    public static final String UID = "uid";
    /**
     * 默认过期时间  7天
     */
    private static final Duration DEFAULT_EXPIRE = Duration.ofDays(7);
    private static final String ISSUER = "smart";
    private static final String JWT_AUTHORITIES = "authorities";
    @Resource
    private JWTConfigProperties jwtConfigProperties;
    private String secret;
    private Duration expire;

    @PostConstruct
    private void init() {
        this.secret = jwtConfigProperties.getSecret();
        this.expire = jwtConfigProperties.getExpiration() == null ? DEFAULT_EXPIRE : jwtConfigProperties.getExpiration();
    }

    /**
     * 生成token
     *
     * @param
     * @return
     */
    public String generate(AdminVo admin) {
        return jwtConfigProperties.getTokenPrefix() + Jwts.builder()
                .setId(admin.getId().toString())
                .setSubject(admin.getUsername())
                //签发时间
                .setIssuedAt(LocalDateTime.now())
                // 发行人
                .setIssuer(ISSUER)
                //认证信息
                .claim("authorities", admin.getAuthorities())
                // 失效时间
                .setExpiration(new Date(expire.toMillis()))
                .claim(JWT_AUTHORITIES, admin.getAuthorities())
                // 签名算法、密钥 不使用公钥
                .signWith(SignatureAlgorithm.HS512, jwtConfigProperties.getSecret())
                // 使用公钥
//                .signWith(SignatureAlgorithm.RS512, jwtConfig.getSecret())
                .compact();
    }

    /**
     * 解析Claims
     *
     * @param token
     * @return
     */
    public Claims getClaim(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new BaseException(ResultCodeEnum.ERROR);
        }
        return claims;
    }

    // 从token中获取用户名
    public String getUsername(String token) {
        return getClaim(token).getSubject();
    }

    /**
     * 获取jwt发布时间
     */
    public Date getIssuedAt(String token) {
        return getClaim(token).getIssuedAt();
    }

    /**
     * 获取UID
     */
    public Integer getUid(String token) {
        String uid = (String) getClaim(token).get(UID);
        return Integer.parseInt(uid);
    }

    /**
     * 获取jwt失效时间
     */
    public Date getExpiration(String token) {
        return getClaim(token).getExpiration();
    }

    /**
     * 验证token是否失效
     *
     * @param token
     * @return true:过期   false:没过期
     */
    public boolean isExpired(String token) {
        try {
            final Date expiration = getExpiration(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

}