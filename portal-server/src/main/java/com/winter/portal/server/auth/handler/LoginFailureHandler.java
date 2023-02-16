package com.winter.portal.server.auth.handler;

import com.alibaba.fastjson.JSON;
import com.winter.portal.server.auth.utils.RestResponseUtil;
import com.winter.portal.server.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.jzyan.framework.core.response.CommonRes;
import org.jzyan.framework.core.utils.IpUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Version : 1.0
 * @Description : 认证失败处理器
 * @Author : jzyan
 * @CreateDate : 2020/09/16 18:07
 */
@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        String ip = IpUtils.getIpAddr(request);
        log.warn("[{}][{}]登录失败：{}", request.getRequestURI(), ip, exception.getMessage());
        if (exception instanceof UsernameNotFoundException) {
            RestResponseUtil.response(response, JSON.toJSONString(CommonRes.failed(ErrorCodeEnum.CODE_1402001)));
        }
        if (exception instanceof BadCredentialsException) {
            RestResponseUtil.response(response, JSON.toJSONString(CommonRes.failed(ErrorCodeEnum.CODE_1402001)));
        }
        if (exception instanceof DisabledException) {
            RestResponseUtil.response(response, JSON.toJSONString(CommonRes.failed(ErrorCodeEnum.CODE_1402002)));
        }
        if (exception instanceof AuthenticationServiceException) {
            RestResponseUtil.response(response, JSON.toJSONString(CommonRes.failed(ErrorCodeEnum.CODE_1402003.getCode()
                    , exception.getMessage())));
        }
        if (exception instanceof InternalAuthenticationServiceException) {
            RestResponseUtil.response(response, JSON.toJSONString(CommonRes.failed(ErrorCodeEnum.CODE_1402003.getCode()
                    , exception.getMessage())));
        }
    }

}
