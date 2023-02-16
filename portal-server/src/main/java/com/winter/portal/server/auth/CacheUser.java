package com.winter.portal.server.auth;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * <p>
 * session user
 * </p>
 *
 * @author jzyan
 * @since 2023-02-02
 */
@Data
public class CacheUser {

    private Long id;
    private String username;
    private String nickname;
    /**
     * 超级用户 1-是，0-否
     */
    private Integer root;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 最后一次登陆ip
     */
    private String lastLoginIp;
    /**
     * 首次登录 1-是，0-否
     */
    private Integer firstLogin;
    /**
     * 登录时间
     */
    private Date loginDate;

    private Set<Long> roleIds;

}
