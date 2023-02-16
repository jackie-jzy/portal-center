package com.winter.portal.server.auth.handler;

import com.alibaba.fastjson.JSON;
import com.winter.portal.server.auth.AuthUser;
import com.winter.portal.server.auth.cache.AuthorizationCacheService;
import com.winter.portal.server.auth.utils.RestResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.jzyan.framework.core.response.CommonRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Version : 1.0
 * @Description : 退出成功处理器
 * @Author : jzyan
 * @CreateDate : 2020/09/16 18:08
 */
@Slf4j
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    @Autowired
    private AuthorizationCacheService cacheService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        AuthUser user = (AuthUser) authentication.getPrincipal();
        cacheService.clearAll(user.getUsername());
        RestResponseUtil.response(response, JSON.toJSONString(CommonRes.success()));
    }

}
