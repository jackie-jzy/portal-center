package com.winter.portal.server.auth.service;

import com.winter.portal.server.auth.config.properties.JwtTokenProperties;
import com.winter.portal.server.auth.constant.AuthConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Map;

/**
 * @Version : 1.0
 * @Description : jwt util
 * @Author : jzyan
 * @CreateDate : 2020/09/18 17:28
 */
@Slf4j
@Service
public class JwtTokenService {
    private static final String JWT_USER_NAME = "username";
    @Autowired
    private JwtTokenProperties jwtTokenProperties;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 签发/生成token
     * issuer 签发人
     * subject 代表这个JWT的主体，即他的所有人，一般是用户ID
     * claims 储存在jwt里的信息(键值对)，一般是放些用户的权限/角色信息
     * ttlMillis 有效时间(毫秒)
     * secret 密钥
     */
    public String generateToken(String issuer, String subject, Map<String, Object> claims, long ttlMillis, String secret) {
        //加密方式
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前时间戳，并转为日期
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //String printBase64Binary(byte[])就是将字节数组做base64编码，byte[] parseBase64Binary(String) 就是将Base64编码后的String还原成字节数组。
        byte[] signingKey = DatatypeConverter.parseBase64Binary(secret);
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder();
        //如果claims不为空，就加到JWT的载荷里面去
        if (null != claims) {
            builder.setClaims(claims);
        }
        if (!StringUtils.isEmpty(subject)) {
            builder.setSubject(subject);
        }
        if (!StringUtils.isEmpty(issuer)) {
            builder.setIssuer(issuer);
        }
        //签发时间
        builder.setIssuedAt(now);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            //过期时间
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        builder.signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    // 刷新token和业务token 只是过期时间不一样

    /**
     * 生成 access_token   正常请求资源时携带的凭证
     */
    public String getAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(jwtTokenProperties.issuer, subject, claims,
                jwtTokenProperties.accessTokenExpireTime.toMillis(), jwtTokenProperties.secretKey);
    }

    /**
     * 生产 PC refresh_token
     */
    public String getRefreshToken(String subject, Map<String, Object> claims) {

        return generateToken(jwtTokenProperties.issuer, subject, claims,
                jwtTokenProperties.refreshTokenExpireTime.toMillis(), jwtTokenProperties.secretKey);
    }

    /**
     * 生产 App端 refresh_token
     */
    public String getRefreshAppToken(String subject, Map<String, Object> claims) {
        return generateToken(jwtTokenProperties.issuer, subject, claims,
                jwtTokenProperties.refreshTokenExpireAppTime.toMillis(),
                jwtTokenProperties.secretKey);
    }

    /**
     * 解析令牌 获取数据声明
     * 拿到用户及用户的角色、权限等信息
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            //用密钥(必字节数组)解析jwt，获取body（有效载荷）
            claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtTokenProperties.secretKey))
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.warn("解析JWT异常：{}", e.getMessage());
            claims = null;
        }
        return claims;
    }

    /**
     * 获取用户id
     */
    public String getUserId(String token) {
        String userId = null;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            log.error("error={}", e);
        }
        return userId;
    }

    /**
     * 获取用户名
     */
    public String getUserName(String token) {
        String username = null;
        try {
            //解析token获取claims
            Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                return null;
            }
            //claims中的key当作自定义的常量
            username = (String) claims.get(JWT_USER_NAME);
        } catch (Exception e) {
            log.warn("解析token获取username异常:{}", e.getMessage());
        }
        return username;
    }

    /**
     * 获取 jti
     */
    public String getJti(String token) {
        String jti = null;
        try {
            //解析token获取claims
            Claims claims = getClaimsFromToken(token);
            //claims中的key当作自定义的常量
            jti = String.valueOf(claims.get(Claims.ID));
        } catch (Exception e) {
            log.error("error={}", e);
        }
        return jti;
    }

    /**
     * 验证token 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            //首先解析，如果能解析成功，证明我服务器签发的
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            //过期时间和当前时间比较，如果过期时间在当前时间之前，返回true，表示已过期；否则返回false，没过期
            return expiration.before(new Date());
        } catch (Exception e) {
            log.error("error={}", e);
            //解析失败，抛出异常，返回true，表示已过期
            return true;
        }
    }

    /**
     * 校验令牌
     */
    public Boolean validateToken(String token) {
        String username = this.getUserName(token);
        if (ObjectUtils.isEmpty(username)) {
            return false;
        }
        String jti = this.getJti(token);
        String redisJti = this.getToken(username);
        return jti.equals(redisJti);
    }

    /**
     * 刷新token
     * 如果是过期刷新，claims/载荷 不变；
     * 如果主动刷新，claims/载荷 改变【一般是权限/角色改变的时候去主动刷新】
     */
    public String refreshToken(String refreshToken, Map<String, Object> claims) {
        String refreshedToken;
        try {
            Claims parserclaims = getClaimsFromToken(refreshToken);
            /**
             * 如果传入的claims为空，说明是过期刷新，原先的用户信息不变，claims引用上个token里的内容
             */
            if (null == claims) {
                claims = parserclaims;
            }
            /**
             * 不为空，根据传入的claims【用户信息】，生成新的Token
             */
            refreshedToken = generateToken(parserclaims.getIssuer(), parserclaims.getSubject(), claims,
                    jwtTokenProperties.accessTokenExpireTime.toMillis(), jwtTokenProperties.secretKey);
        } catch (Exception e) {
            refreshedToken = null;
            log.error("error={}", e);
        }
        return refreshedToken;
    }

    /**
     * 获取token的剩余过期时间
     */
    public long getRemainingTime(String token) {
        long result = 0;
        try {
            long nowMillis = System.currentTimeMillis();
            result = getClaimsFromToken(token).getExpiration().getTime() - nowMillis;
        } catch (Exception e) {
            log.error("error={}", e);
        }
        return result;
    }

    public String getToken(String username) {
        return stringRedisTemplate.opsForValue().get(AuthConstant.AUTH_TOKEN_PREFIX + username);
    }

}
