package com.mos.eboot.api.jwt.common.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mos.eboot.api.jwt.common.jwtfilter.JWTAuthenticationFilter;
import com.mos.eboot.api.jwt.common.jwtfilter.JWTLoginFilter;
import com.mos.eboot.api.jwt.common.security.CustomAuthenticationProvider;

/**
 * SpringSecurity的配置
 *
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 * securedEnabled = true 注解控制方法权限 prePostEnabled = true jsr250Enabled = true
 *
 * @date 20180831 15:19:23
 * @since v1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /***
     * 设置HTTP验证规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //排除注册请求
                .antMatchers(HttpMethod.POST, "/user/signUp").permitAll()
                // Swagger2 权限放行                
                .antMatchers("/swagger-ui.html").permitAll()                
                .antMatchers("/resources/**").permitAll()                
                .antMatchers("/webjars/**").permitAll()                
                .antMatchers("/swagger-resources/**").permitAll()                
                .antMatchers("/v2/**").permitAll()
                //其余所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                //添加拦截器
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()));
    }

    /***
     * 使用自定义身份验证组件
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService,bCryptPasswordEncoder));
    }

}
