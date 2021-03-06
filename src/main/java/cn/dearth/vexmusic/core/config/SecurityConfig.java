package cn.dearth.vexmusic.core.config;

import cn.dearth.vexmusic.core.constant.AuthenticationConfigConstants;
import cn.dearth.vexmusic.core.filter.JWTAuthorizationFilter;
import cn.dearth.vexmusic.core.service.UserService;
import cn.dearth.vexmusic.core.exception.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * spring security 的总体配置类
 * @author dearth
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
        jsr250Enabled = true,
        securedEnabled = true,
        prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    PasswordEncoder passwordEncoder;


    UserService userService;


    RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    /**
     * 核心配置
     * 把跨域开启,跨站脚本攻击配置给关了
     * 匹配到注册接口放行, 其它所有接口都需要鉴权
     * 添加自定义的用户名密码认证filter以及token认证token
     * 添加异常处理的entryPoint
     * 并且把session的策略给声明为无状态
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, AuthenticationConfigConstants.CREATE_TOKEN_URL).permitAll()
                .antMatchers(HttpMethod.POST, AuthenticationConfigConstants.SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                //.addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), userService))
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * 重写spring security的UserService 用自己的
     * 并且在UserService重写loadUserByUsername方法使其返回的User为我们自己的User
     * * 只需要在User上实现UserDetails接口并重写一些特定方法即可
     * 而不是spring security的UserDetail对象
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger**/**")//
                .antMatchers("/webjars/**")//
                .antMatchers("/v3/**")//
                .antMatchers("/doc.html")
                .antMatchers("/wechat/**");
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRestAuthenticationEntryPoint(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }
}
