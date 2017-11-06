package com.spring.boot.example.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 配置信息类
 * </p>
 *
 * @author wangliang
 * @since 2017/11/6
 */
@ConfigurationProperties("example.service")
public class ExampleServiceProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
