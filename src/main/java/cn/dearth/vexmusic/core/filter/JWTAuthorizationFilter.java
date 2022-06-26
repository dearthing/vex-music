package cn.dearth.vexmusic.core.filter;


import cn.dearth.vexmusic.core.constant.AuthenticationConfigConstants;
import cn.dearth.vexmusic.core.service.UserService;
import cn.dearth.vexmusic.core.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 用户在请求接口时 检查Token 是否在SecurityContext上下文容器中
 * @author dearth
 */

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    UserService userService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. 获取请求头中的Authorization这个key的value
        String header = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);
        // 2. 验证这个value是否为空,或者前缀是否为"Bearer "
        if (header == null || !header.startsWith(AuthenticationConfigConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 3. 解析这个Token返回UsernamePasswordAuthenticationToken对象
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        // 4. 把验证成功的username放到SecurityContextHolder上下文容器中,如果为null则说明验证失败了
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);
        if (token != null) {
            // 解析token 获取到颁发的token中的subject (username是唯一的
            String username = JWT.require(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(AuthenticationConfigConstants.TOKEN_PREFIX, ""))
                    .getSubject();
            if (username != null) {
                User user = userService.loadUserByUsername(username);
                return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
