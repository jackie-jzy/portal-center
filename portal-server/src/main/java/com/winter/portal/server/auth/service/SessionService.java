package com.winter.portal.server.auth.service;

import com.winter.portal.server.auth.CacheUser;
import com.winter.portal.server.auth.cache.AuthorizationCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

/**
 * <p>
 * session util
 * </p>
 *
 * @author jzyan
 * @since 2022-05-07
 */
@Service
public class SessionService {

    @Autowired
    private AuthorizationCacheService authorizationCacheService;

    /**
     * 获取登录用户信息
     *
     * @return
     */
    public CacheUser getUser() {
        return authorizationCacheService.getUserOfCtx();
    }

    /**
     * 获取登录用户id
     *
     * @return
     */
    public long getUserId() {
        return getUser().getId();
    }

    /**
     * 获取登录用户账号
     *
     * @return
     */
    public String getUsername() {
        return getUser().getUsername();
    }

    /**
     * 是否超级管理员
     *
     * @return
     */
    public boolean isRoot() {
        if (getUser().getRoot() == 1) {
            return true;
        }
        return false;
    }

    /**
     * 获取登录ip
     *
     * @return
     */
    public String getLastLoginIp() {
        return getUser().getLastLoginIp();
    }

    /**
     * 获取登录ip
     *
     * @return
     */
    public Date getLoginDate() {
        return getUser().getLoginDate();
    }

    /**
     * 角色id集合
     *
     * @return
     */
    public Set<Long> getRoleIds() {
        return getUser().getRoleIds();
    }

    /**
     * 清理session
     *
     * @param username
     */
    public void clearSession(String username) {
        authorizationCacheService.clearAll(username);
    }

}
