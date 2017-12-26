package com.spring.boot.jwt.utils;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.spring.boot.jwt.constant.Namespace;
import com.spring.boot.jwt.constant.TokenType;
import com.spring.boot.jwt.domain.UserDetails;
import com.spring.boot.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.collections.CollectionUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 基于JWT的Token工具类
 * </p>
 *
 * @author wangliangx
 * @since 2017/12/19
 */
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_DEVICE_ID = "deviceId";

    private JwtProperties jwtProperties;

    private HazelcastInstance hazelcastInstance;

    public JwtTokenUtil(JwtProperties jwtProperties, HazelcastInstance hazelcastInstance) {
        this.jwtProperties = jwtProperties;
        this.hazelcastInstance = hazelcastInstance;
    }

    /**
     * 生成Token
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails, String tokenType) {
        Date expirationDate;
        if (TokenType.REFRESH_TOKEN.equals(tokenType)) {
            expirationDate = generateExpirationDate(jwtProperties.getRefreshTokenExpiration());
        } else {
            expirationDate = generateExpirationDate(jwtProperties.getAccessTokenExpiration());
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getAccount());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_DEVICE_ID, userDetails.getDeviceId());
        return generateToken(claims, expirationDate);
    }

    String generateToken(Map<String, Object> claims, Date expirationDate) {
        return Jwts.builder()
                .setClaims(claims)//数据声明
                .setExpiration(expirationDate)//过期时间
                .signWith(SignatureAlgorithm.HS512, DatatypeConverter.parseBase64Binary(jwtProperties.getSecret()))//签名（包含算法和密钥）
                .compact();
    }

    private Date generateExpirationDate(Long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 刷新Token
     *
     * @param oldToken
     * @return
     */
    public String refreshToken(String oldToken) {
        String refreshedToken;
        try {
            tokenAddToBlacklist(oldToken);
            final Claims claims = getClaimsFromToken(oldToken);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims, generateExpirationDate(jwtProperties.getAccessTokenExpiration()));
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证Token
     *
     * @param token
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        Boolean result;
        try {
            final String username = getUsernameFromToken(token);

            //数据声明为空只校验token是否过期
            if (null == userDetails) {
                result = !isTokenExpired(token) && !tokenIsOnTheBlacklist(token);
            } else {
//                final Date created = getCreatedDateFromToken(token);
//                final Date expiration = getExpirationDateFromToken(token);
                result = (userDetails.getAccount().equals(username)//匹配帐号
                        && !isTokenExpired(token)//是否过期
                        && !tokenIsOnTheBlacklist(token)//是否在黑名单中
//                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate())
                );
            }
        } catch (Exception e) {
            //don't trust the JWT!
            result = false;
        }
        return result;
    }

    /**
     * 失效Token
     *
     * @param token
     * @return
     */
    public Boolean invalidToken(String token) {
        Boolean result;
        try {
            result = tokenAddToBlacklist(token);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * Token放入黑名单
     *
     * @param token
     * @return
     */
    Boolean tokenAddToBlacklist(String token) {
        Boolean result;
        try {
            if (!isTokenExpired(token)) {
                IMap<String, Set> iMap = hazelcastInstance.getMap(Namespace.BLACKLIST);
                Set<String> tokenSet = iMap.get(Namespace.BLACKLIST);
                if (CollectionUtils.isEmpty(tokenSet)) {
                    tokenSet = new HashSet<>();
                }
                tokenSet.add(token);
                iMap.put(Namespace.BLACKLIST, tokenSet, jwtProperties.getAccessTokenExpiration(), TimeUnit.SECONDS);
            }
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 判断Token是否在黑名单中
     *
     * @param token
     * @return
     */
    Boolean tokenIsOnTheBlacklist(String token) {
        Boolean result = false;
        try {
            IMap<String, Set> iMap = hazelcastInstance.getMap(Namespace.BLACKLIST);
            Set<String> tokenSet = iMap.get(Namespace.BLACKLIST);
            if (CollectionUtils.isNotEmpty(tokenSet)) {
                result = tokenSet.contains(token);
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 获取Token中帐号信息
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 获取Token中设备唯一标识
     *
     * @param token
     * @return
     */
    public String getDeviceIdFromToken(String token) {
        String deviceId;
        try {
            final Claims claims = getClaimsFromToken(token);
            deviceId = (String) claims.get(CLAIM_KEY_DEVICE_ID);
        } catch (Exception e) {
            deviceId = null;
        }
        return deviceId;
    }

    /**
     * 获取Token创建时间
     *
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 获取Token过期时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * 获取Token数据声明
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtProperties.getSecret()))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 判断Token是否过期
     *
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 是否在最后一次密码重置之前创建Token
     *
     * @param created
     * @param lastPasswordReset
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 能否刷新Token
     *
     * @param token
     * @param lastPasswordReset
     * @return
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }

}
