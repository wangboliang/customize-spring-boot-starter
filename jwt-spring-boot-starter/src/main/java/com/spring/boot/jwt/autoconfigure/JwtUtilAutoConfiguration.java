package com.spring.boot.jwt.autoconfigure;

import com.hazelcast.core.HazelcastInstance;
import com.spring.boot.jwt.properties.JwtProperties;
import com.spring.boot.jwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 自动配置类
 * </p>
 *
 * @author wangliang
 * @since 2017/12/22
 */
@Configuration
@ConditionalOnClass(JwtTokenUtil.class)
@EnableConfigurationProperties(JwtProperties.class)
public class JwtUtilAutoConfiguration {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Bean
    @ConditionalOnMissingBean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil(jwtProperties, hazelcastInstance);
    }

}
