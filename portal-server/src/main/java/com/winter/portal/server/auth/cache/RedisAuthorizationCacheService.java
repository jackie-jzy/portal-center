package com.winter.portal.server.auth.cache;

import com.alibaba.fastjson.JSON;
import com.winter.portal.server.auth.CacheUser;
import com.winter.portal.server.auth.config.properties.JwtTokenProperties;
import com.winter.portal.server.auth.constant.AuthConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Version : 1.0
 * @Description : 基于redis
 * @Author : jzyan
 * @CreateDate : 2020/10/30 15:38
 */
@Service
public class RedisAuthorizationCacheService implements AuthorizationCacheService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private JwtTokenProperties jwtTokenProperties;

    @Override
    public CacheUser getUser(String username) {
        String ob = stringRedisTemplate.opsForValue().get(AuthConstant.AUTH_USER_PREFIX + username);
        CacheUser authUser = JSON.parseObject(ob, CacheUser.class);
        return authUser;
    }

    @Override
    public void putToken(String username, String token) {
        stringRedisTemplate.opsForValue().set(AuthConstant.AUTH_TOKEN_PREFIX + username, token
                , jwtTokenProperties.accessTokenExpireTime.toMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public String getToken(String username) {
        return stringRedisTemplate.opsForValue().get(AuthConstant.AUTH_TOKEN_PREFIX + username);
    }

    @Override
    public void putUser(String username, String user) {
        stringRedisTemplate.opsForValue().set(AuthConstant.AUTH_USER_PREFIX + username, user
                , jwtTokenProperties.getAccessTokenExpireTime().toMillis() + 10, TimeUnit.MILLISECONDS);
    }

    @Override
    public void putRsaKey(String username, String privateKey) {
        stringRedisTemplate.opsForValue().set(AuthConstant.AUTH_RSA_PRIVATE_KEY_PREFIX + username, privateKey);
    }

    @Override
    public String getRsaKey(String username) {
        return stringRedisTemplate.opsForValue().get(AuthConstant.AUTH_RSA_PRIVATE_KEY_PREFIX + username);
    }

    @Override
    public void removeRsaKey(String username) {
        stringRedisTemplate.delete(AuthConstant.AUTH_RSA_PRIVATE_KEY_PREFIX + username);
    }

    @Override
    public void removeToken(String username) {
        stringRedisTemplate.delete(AuthConstant.AUTH_TOKEN_PREFIX + username);
        stringRedisTemplate.delete(AuthConstant.AUTH_USER_PREFIX + username);
    }

    @Override
    public void clearAll(String username) {
        SecurityContextHolder.clearContext();
        this.removeToken(username);
    }

}

