package com.winter.portal.server.auth.filter;

import com.winter.portal.server.auth.cache.AuthorizationCacheService;
import com.winter.portal.server.auth.constant.AuthConstant;
import com.winter.portal.server.auth.service.JwtTokenService;
import com.winter.portal.server.auth.CacheUser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

/**
 * @Version : 1.0
 * @Description : token 验证器
 * @Author : jzyan
 * @CreateDate : 2020/09/21 17:34
 */
@Data
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private JwtTokenService jwtTokenService;
    private AuthorizationCacheService cacheService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) {
            authHeader = request.getParameter(HttpHeaders.AUTHORIZATION);
        }
        if (!StringUtils.isEmpty(authHeader)) {
            String token = null;
            try {
                token = new String(Base64.getDecoder().decode(authHeader), "UTF-8");
            } catch (Exception e) {
                log.error("Token解析异常: {}", e.getMessage());
            }
            if (!StringUtils.isEmpty(token) && token.startsWith(AuthConstant.TOKEN_HEAD)) {
                String authToken = token.substring(AuthConstant.TOKEN_HEAD.length());
                if (StringUtils.hasText(authToken)) {
                    if (jwtTokenService.validateToken(authToken)) {
                        String username = jwtTokenService.getUserName(authToken);
                        CacheUser authUser = cacheService.getUser(username);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authUser, null, new ArrayList<>());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
