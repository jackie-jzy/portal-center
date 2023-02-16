package com.winter.portal.server.auth.handler;

import com.alibaba.fastjson.JSON;
import com.winter.portal.server.auth.AuthUser;
import com.winter.portal.server.auth.cache.AuthorizationCacheService;
import com.winter.portal.server.auth.constant.AuthConstant;
import com.winter.portal.server.auth.service.JwtTokenService;
import com.winter.portal.server.auth.utils.RestResponseUtil;
import com.winter.portal.server.auth.CacheUser;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.jzyan.framework.core.response.CommonRes;
import org.jzyan.framework.core.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Version : 1.0
 * @Description : 登录成功处理器
 * @Author : jzyan
 * @CreateDate : 2020/09/16 18:05
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private AuthorizationCacheService cacheService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException {
        Object obj = authentication.getPrincipal();
        AuthUser authUser = (AuthUser) obj;
        // 获取登录用户
        Map<String, Object> claims = new HashMap<>(2);
        String jti = UUID.randomUUID() + "-" + System.currentTimeMillis();
        claims.put("username", authUser.getUsername());
        claims.put(Claims.ID, jti);
        // 缓存用户信息
        CacheUser cacheUser = new CacheUser();
        cacheUser.setId(authUser.getId());
        cacheUser.setUsername(authUser.getUsername());
        cacheUser.setNickname(authUser.getNickname());
        cacheUser.setRoot(authUser.getRoot());
        cacheUser.setPhone(authUser.getPhone());
        cacheUser.setEmail(authUser.getEmail());
        cacheUser.setFirstLogin(authUser.getFirstLogin());
        cacheUser.setRoleIds(authUser.getRoleIds());
        cacheUser.setLoginDate(new Date());
        cacheUser.setLastLoginIp(IpUtils.getIpAddr(request));
        cacheService.putToken(cacheUser.getUsername(), jti);
        cacheService.putUser(cacheUser.getUsername(), JSON.toJSONString(cacheUser));
        String token = jwtTokenService.getAccessToken(cacheUser.getUsername(), claims);
        token = Base64.getEncoder().encodeToString((AuthConstant.TOKEN_HEAD + token).getBytes("UTF-8"));
        Map<String, Object> map = new HashMap<>(3);
        map.put("token", token);
        map.put("username", cacheUser.getUsername());
        map.put("nickname", cacheUser.getNickname());
        RestResponseUtil.response(response, JSON.toJSONString(CommonRes.success(map)));
    }

}
