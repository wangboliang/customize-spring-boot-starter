package com.spring.boot.jwt.constant;

/**
 * <p>
 * 授权过程中涉及到的常量字符串
 * </p>
 *
 * @author wangliang
 * @since 2017/12/20
 */
public class AuthConst {
    // 会话是否授权标志
    public static String LOGIN_URL = "/login";
    // 登录请求
    public static String LOGOUT_URL = "/logoutUrl";
    // 注销url
    public static String CLIENT_URL = "/clientUrl";
    // 客户端url
    public static String IS_LOGIN = "isLogin";
    // 注销请求
    public static String LOGOUT_REQUEST = "/logoutRequest";
    // 访问令牌
    public static String TOKEN = "accessToken";
    // 验证令牌请求
    public static String VALIDATE_TOKEN = "/validateToken";
    // 刷新令牌请求
    public static String REFRESH_TOKEN = "/refreshToken";
}
