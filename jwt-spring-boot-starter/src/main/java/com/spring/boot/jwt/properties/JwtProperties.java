package com.spring.boot.jwt.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * Jwt配置信息
 * </p>
 *
 * @author wangliang
 * @since 2017/12/22
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     * default enabled false.
     */
    private boolean enabled = false;
    /**
     * default secret vpclub.
     */
    private String secret = "vpclub";
    /**
     * default accessToken expiration 7200 seconds.
     */
    private Long accessTokenExpiration = 7200L;
    /**
     * default refreshToken expiration 86400 seconds.
     */
    private Long refreshTokenExpiration = 86400L;
}
