package com.spring.boot.jwt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * Token数据声明
 * </p>
 *
 * @author wangliang
 * @since 2017/12/20
 */
@Data
@AllArgsConstructor
public class UserDetails implements Serializable {

    /**
     * 帐号
     */
    private String account;

    /**
     * 设备唯一标识
     */
    private String deviceId;

}
