package com.winter.portal.server.auth.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * <p>
 * jwt 属性配
 * </p>
 *
 * @author jzyan
 * @since 2023-02-14
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenProperties {

    public String secretKey;
    public Duration accessTokenExpireTime;
    public Duration refreshTokenExpireTime;
    public Duration refreshTokenExpireAppTime;
    public String issuer;

}
