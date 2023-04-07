package com.yangxinyu.app.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

/**
 * @author : 星宇
 * @date : 2023/3/31 11:13
 * @description :
 */
@SpringBootConfiguration
public class SecurityConfig {

    @Resource
    private AuthenticationConfiguration authenticationConfiguration;
    /**
     *密码编码器
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * SecurityFilterChain
     * 校验配置
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                //关闭csrf
                .csrf().disable()
                //关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //开启全局拦截认证
                .authorizeRequests()
                //放行登录接口，允许未登录状态访问（前后端完全分离后就需要放行所有接口然后进行jwt校验）
                .antMatchers("/**").anonymous()
                .anyRequest().authenticated().and().build();
    }

    /**
     * AuthenticationManager
     * 身份认证
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager () throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
