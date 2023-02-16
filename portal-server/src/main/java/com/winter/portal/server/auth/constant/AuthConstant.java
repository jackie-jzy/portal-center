package com.winter.portal.server.auth.constant;

/**
 * @author jzyan
 */
public interface AuthConstant {
    String TOKEN_HEAD = "Bearer ";
    String AUTH_PREFIX = "PORTAL_CENTER:AUTH:";
    String AUTH_USER_PREFIX = AUTH_PREFIX + "USER:";
    String AUTH_TOKEN_PREFIX = AUTH_PREFIX + "TOKEN:";
    String AUTH_RSA_PRIVATE_KEY_PREFIX = AUTH_PREFIX + "RSA_PRIVATE_KEY:";
}
