package com.winter.portal.server.auth.handler;

import com.alibaba.fastjson.JSONObject;
import com.winter.portal.server.auth.utils.RestResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.jzyan.framework.core.enums.ResCodeEnum;
import org.jzyan.framework.core.response.CommonRes;
import org.jzyan.framework.core.utils.IpUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 未授权处理器
 * </p>
 *
 * @author jzyan
 * @since 2023-02-02
 */
@Slf4j
@Component
public class UnAuthorizationHandler implements AccessDeniedHandler {
    /**
     * Handles an access denied failure.
     *
     * @param request               that resulted in an <code>AccessDeniedException</code>
     * @param response              so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     * @throws IOException      in the event of an IOException
     * @throws ServletException in the event of a ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String ip = IpUtils.getIpAddr(request);
        log.warn("[{}][{}]未授权访问：{}", request.getRequestURI(), ip, accessDeniedException.getMessage());
        RestResponseUtil.response(response, JSONObject.toJSONString(CommonRes.failed(ResCodeEnum.FORBIDDEN)));
    }

}
