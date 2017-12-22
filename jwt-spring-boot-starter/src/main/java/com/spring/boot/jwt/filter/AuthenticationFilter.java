package com.spring.boot.jwt.filter;

import com.hazelcast.util.JsonUtil;
import com.spring.boot.jwt.constant.AuthConst;
import com.spring.boot.jwt.utils.JwtTokenUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * filter自动注入方式有两种：
 * 1.实现FilterRegistrationBean接口，自动配置
 * 2.在 SpringBootApplication 上使用@ServletComponentScan 注解后,在filter类上加@WebFilter注解自动注入
 *
 * @author wangliang
 * @since 2017/12/20
 */
//@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    private FilterConfig config;

    private JwtTokenUtil jwtTokenUtil;

    public AuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void destroy() {
    }

    /**
     * 验证token
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//        HttpSession session = request.getSession();

        String path = request.getRequestURI();
        //登录、刷新令牌、验证令牌请求放行
        if (AuthConst.LOGIN_URL.equals(path) || AuthConst.VALIDATE_TOKEN.equals(path) || AuthConst.REFRESH_TOKEN.equals(path)) {
            chain.doFilter(req, res);
            return;
        }

        //已经登录，放行
//        if (session.getAttribute(AuthConst.IS_LOGIN) != null) {
//            chain.doFilter(req, res);
//            return;
//        }

        //验证token，有效则放行
        String token = request.getHeader(AuthConst.TOKEN);
        if (token != null) {
            //验证失败
            if (!jwtTokenUtil.validateToken(token, null)) {
                AuthenticationFailure(req, res);
                return;
            }
            //将当前局部会话标记为“已登录”
//            session.setAttribute(AuthConst.IS_LOGIN, true);

            chain.doFilter(req, res);
            return;
        }

        AuthenticationFailure(req, res);
        return;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    /**
     * 验证失败返回错误信息
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void AuthenticationFailure(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        httpResponse.getWriter().write("{\n" +
                "    \"returnCode\": \"1009\",\n" +
                "    \"message\": \"login-command: 身份验证失败\",\n" +
                "    \"dataInfo\": null\n" +
                "}");
    }

}
