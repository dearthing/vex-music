package cn.dearth.vexmusic.core.filter;

import cn.dearth.vexmusic.core.constant.AuthenticationConfigConstants;
import cn.dearth.vexmusic.core.entity.User;
import cn.dearth.vexmusic.core.exception.BizException;
import cn.dearth.vexmusic.core.exception.ExceptionType;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

/**
 * 用户在登录时基于用户名密码的验证, 并且为验证成功的用户颁发Token
 * @author dearth
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {




    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    /**
     * 用户在登录时 验证请求体中的账号名以及密码
     * 之后会用到这个方法
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 通过jackson把请求体中的数据转化为User对象
            User unauthorizedUser = new ObjectMapper().readValue(request.getInputStream(), User.class);
            // 通过AuthenticationManager这个类做验证
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            unauthorizedUser.getUsername(),
                            unauthorizedUser.getPassword(),
                            new ArrayList<>() // todo 添加权限
                    )
            );
        } catch (IOException e) {
            // 如果验证失败抛出用户不存在异常
            throw new BizException(ExceptionType.FORBIDDEN);
        }
    }

    /**
     * 验证成功, 颁发token令牌
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User)authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME) )
                .sign(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes(StandardCharsets.UTF_8)));
        response.addHeader(AuthenticationConfigConstants.HEADER_STRING,AuthenticationConfigConstants.TOKEN_PREFIX + token);
    }
}
