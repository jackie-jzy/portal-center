package com.winter.portal.server.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winter.portal.server.auth.cache.AuthorizationCacheService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jzyan.framework.core.utils.RSAEncrypUtil;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Processes an authentication form submission. Called
 * {@code AuthenticationProcessingFilter} prior to Spring Security 3.0.
 * <p>
 * Login forms must present two parameters to this filter: a username and password. The
 * default parameter names to use are contained in the static fields
 * {@link #SPRING_SECURITY_FORM_USERNAME_KEY} and
 * {@link #SPRING_SECURITY_FORM_PASSWORD_KEY}. The parameter names can also be changed by
 * setting the {@code usernameParameter} and {@code passwordParameter} properties.
 * <p>
 * This filter by default responds to the URL {@code /login}.
 *
 * @author Ben Alex
 * @author Colin Sampaleanu
 * @author Luke Taylor
 * @since 3.0
 */
@Data
@Slf4j
public class PasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthorizationCacheService authorizationCacheService;

    public PasswordAuthenticationFilter() {
        super();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().toUpperCase().equals("POST")) {
            throw new AuthenticationServiceException("请求方法不支持:" + request.getMethod());
        }

        if (!(request.getContentType().replace(" ", "").equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().replace(" ", "").equals(MediaType.APPLICATION_JSON_VALUE))) {
            throw new AuthenticationServiceException("请求类型不支持:" + request.getContentType());
        }

        String username;
        String password;
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = request.getInputStream()) {
            Map<String, String> authenticationBean = mapper.readValue(is, Map.class);
            username = authenticationBean.get("username");
            password = authenticationBean.get("password");
        } catch (IOException e) {
            log.error("密码登录接口解析参数异常：", e);
            throw new AuthenticationServiceException("密码登录异常");
        }

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new AuthenticationServiceException("账号和密码不能为空");
        }
        // 解密
        try {
            String privateKey = authorizationCacheService.getRsaKey(username);
            password = RSAEncrypUtil.buildRSADecryptByPrivateKey(password, privateKey);
            authorizationCacheService.removeRsaKey(username);
        } catch (Exception e) {
            log.error("RSA解密异常", e);
            throw new AuthenticationServiceException("账号或密码错误");
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username.trim(), password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
