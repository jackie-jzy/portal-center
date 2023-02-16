//package com.winter.portal.server.auth.config;
//
//import com.winter.portal.server.auth.cache.AuthorizationCacheService;
//import com.winter.portal.server.auth.filter.JwtAuthenticationTokenFilter;
//import com.winter.portal.server.auth.filter.PasswordAuthenticationFilter;
//import com.winter.portal.server.auth.handler.LoginFailureHandler;
//import com.winter.portal.server.auth.handler.LoginSuccessHandler;
//import com.winter.portal.server.auth.handler.UnAuthenticationHandler;
//import com.winter.portal.server.auth.handler.UnAuthorizationHandler;
//import com.winter.portal.server.auth.service.JwtTokenUtil;
//import com.winter.portal.server.auth.voter.UrlAccessDecisionVoter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDecisionVoter;
//import org.springframework.security.access.vote.AffirmativeBased;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Version : 1.0
// * @Description : web 认证授权 spring boot < 2.7
// * @Author : jzyan
// * @CreateDate : 2020/09/17 18:00
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UnAuthenticationHandler unAuthenticationHandler;
//    @Autowired
//    private UnAuthorizationHandler unAuthorizationHandler;
//    @Autowired
//    private LogoutSuccessHandler logoutSuccessHandler;
//    @Autowired
//    private LoginSuccessHandler loginSuccessHandler;
//    @Autowired
//    private LoginFailureHandler loginFailureHandler;
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//    @Autowired
//    private AuthorizationCacheService cacheService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }
//
//    @Override
//    public void configure(WebSecurity webSecurity) {
//        webSecurity.ignoring().antMatchers("/",
//                "/css/**",
//                "/images/**",
//                "/js/**",
//                "/lib/**",
//                "/error",
//                "/route/**",
//                "/favicon.ico",
//                "/swagger-resources/**",
//                "/webjars/**",
//                "/v2/api-docs/**",
//                "/doc.html",
//                "/**/auth/image/code",
//                "/auth/image/code",
//                "/**/admin/user/forget/password",
//                "/admin/user/forget/password",
//                "/api/**",
//                "/**/api/**",
//                "/actuator/**"
//        ).antMatchers(HttpMethod.OPTIONS, "/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 由于使用的是JWT，我们这里不需要csrf
//        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                // 权限控制
//                .accessDecisionManager(accessDecisionManager())
//                // 登录配置
//                .and()
//                .formLogin()
//                .permitAll()
//                // 登出配置
//                .and()
//                .logout()
//                .logoutSuccessHandler(logoutSuccessHandler);
//
//        // 添加自定义 filter
//        http.addFilterBefore(passwordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(jwtAuthenticationTokenFilter(), PasswordAuthenticationFilter.class);
//        // 禁用缓存
//        http.headers().cacheControl();
//        // 添加自定义未授权和未认证异常处理类
//        http.exceptionHandling()
//                .accessDeniedHandler(unAuthorizationHandler)
//                .authenticationEntryPoint(unAuthenticationHandler);
//    }
//
//    public PasswordAuthenticationFilter passwordAuthenticationFilter() throws Exception {
//        PasswordAuthenticationFilter passwordAuthenticationFilter = new PasswordAuthenticationFilter();
//        passwordAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        passwordAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler);
//        passwordAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandler);
//        passwordAuthenticationFilter.setAuthorizationCacheService(cacheService);
//        return passwordAuthenticationFilter;
//    }
//
//    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
//        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
//        jwtAuthenticationTokenFilter.setCacheService(cacheService);
//        jwtAuthenticationTokenFilter.setJwtTokenUtil(jwtTokenUtil);
//        return jwtAuthenticationTokenFilter;
//    }
//
//    public AccessDecisionManager accessDecisionManager() {
//        List<AccessDecisionVoter<?>> accessDecisionVoters = new ArrayList<>();
//        UrlAccessDecisionVoter urlAccessDecisionVoter = new UrlAccessDecisionVoter();
//        urlAccessDecisionVoter.setAuthorizationCacheService(cacheService);
//        accessDecisionVoters.add(urlAccessDecisionVoter);
//        return new AffirmativeBased(accessDecisionVoters);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
