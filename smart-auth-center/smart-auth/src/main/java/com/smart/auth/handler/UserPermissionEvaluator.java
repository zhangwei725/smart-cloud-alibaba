package com.smart.auth.handler;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * 定义权限认证
 */
public class UserPermissionEvaluator implements PermissionEvaluator {
    /**
     *
     * @param authentication
     * @param targetDomainObject
     * @param permission
     * @return
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
