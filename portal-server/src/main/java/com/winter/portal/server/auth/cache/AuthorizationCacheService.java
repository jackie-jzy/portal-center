package com.winter.portal.server.auth.cache;

import com.winter.portal.server.auth.CacheUser;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Version : 1.0
 * @Description : 认证授权缓存 扩展请实现该类
 * @Author : jzyan
 * @CreateDate : 2020/10/30 10:25
 */
public interface AuthorizationCacheService {

    CacheUser getUser(String username);

    void putToken(String username, String token);

    String getToken(String username);

    void putUser(String username, String user);

    void putRsaKey(String username, String privateKey);

    String getRsaKey(String username);

    void removeRsaKey(String username);

    void removeToken(String username);

    void clearAll(String username);

    default CacheUser getUserOfCtx() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CacheUser user = new CacheUser();
        if (ObjectUtils.isNotEmpty(authentication)) {
            user = (CacheUser) authentication.getPrincipal();
        }
        return user;
    }

}
