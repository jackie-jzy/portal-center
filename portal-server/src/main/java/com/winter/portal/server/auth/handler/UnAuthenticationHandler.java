package com.winter.portal.server.auth.handler;

import com.alibaba.fastjson.JSONObject;
import com.winter.portal.server.auth.utils.RestResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.jzyan.framework.core.enums.ResCodeEnum;
import org.jzyan.framework.core.response.CommonRes;
import org.jzyan.framework.core.utils.IpUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Version : 1.0
 * @Description : 未认证访问处理器
 * @Author : jzyan
 * @CreateDate : 2020/09/21 14:21
 */
@Slf4j
@Component
public class UnAuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String ip = IpUtils.getIpAddr(request);
        log.warn("[{}][{}] 未认证访问：{}", request.getRequestURI(), ip, authException.getMessage());
        RestResponseUtil.response(response, JSONObject.toJSONString(CommonRes.failed(ResCodeEnum.UNAUTHORIZED)));
    }

}
