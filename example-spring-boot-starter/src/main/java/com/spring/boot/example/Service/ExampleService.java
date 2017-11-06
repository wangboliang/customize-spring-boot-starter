package com.spring.boot.example.Service;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author wangliang
 * @since 2017/11/6
 */
public class ExampleService {

    private String prefix;
    private String suffix;

    public ExampleService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String wrap(String word) {
        return prefix + word + suffix;
    }

}
