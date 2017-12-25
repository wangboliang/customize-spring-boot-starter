package com.spring.boot.jwt.autoconfigure;

import com.spring.boot.jwt.filter.AuthenticationFilter;
import com.spring.boot.jwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自动配置类
 * </p>
 *
 * @author wangliang
 * @since 2017/12/22
 */
@Configuration
@ConditionalOnProperty(prefix = "jwt", value = "enabled", havingValue = "true")
public class AuthenticationFilterAutoConfiguration {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter());//过滤器
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);//过滤请求
        registrationBean.setOrder(1);//优先执行
        return registrationBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationFilter filter() {
        return new AuthenticationFilter(jwtTokenUtil);
    }

}