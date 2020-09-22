package com.smart.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author zhangwei
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JWTConfigProperties {
    public static final String REDIS_TOKEN_KEY_PREFIX = "TOKEN_";
    /**
     * 密钥KEY
     */
    private String secret;
    /**
     *
     */
    private String tokenHeader;
    /**
     * Token前缀字符
     */
    private String tokenPrefix;
    /**
     * 过期时间
     */
    private Duration expiration;
    /**
     * 不需要认证的接口
     */
    private String antMatchers;
    /**
     * 有效期
     */
    private Duration validTime;
    /**
     *
     */
}