package com.winter.portal.server.auth;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * <p>
 * session user
 * </p>
 *
 * @author jzyan
 * @since 2023-02-02
 */
public class AuthUser extends User {

    private Long id;
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
     * 首次登录 1-是，0-否
     */
    private Integer firstLogin;

    private Set<Long> roleIds;

    public AuthUser() {
        super("", "", new ArrayList<>());
    }

    /**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     *
     * @param username
     * @param password
     * @param authorities
     */
    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    /**
     * Construct the <code>User</code> with the details required by
     * {@link DaoAuthenticationProvider}.
     *
     * @param username              the username presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param password              the password that should be presented to the
     *                              <code>DaoAuthenticationProvider</code>
     * @param enabled               set to <code>true</code> if the user is enabled
     * @param accountNonExpired     set to <code>true</code> if the account has not expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials have not
     *                              expired
     * @param accountNonLocked      set to <code>true</code> if the account is not locked
     * @param authorities           the authorities that should be granted to the caller if they
     *                              presented the correct username and password and the user is enabled. Not null.
     * @throws IllegalArgumentException if a <code>null</code> value was passed either as
     *                                  a parameter or as an element in the <code>GrantedAuthority</code> collection
     */
    public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRoot() {
        return root;
    }

    public void setRoot(Integer root) {
        this.root = root;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Integer firstLogin) {
        this.firstLogin = firstLogin;
    }

    public Set<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }

}
