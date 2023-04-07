package com.yangxinyu.app.service.impl;

import com.yangxinyu.app.auth.TokenUtils;
import com.yangxinyu.app.auth.config.TokenProperties;
import com.yangxinyu.app.converter.CurrentUserConverter;
import com.yangxinyu.app.entity.Student;
import com.yangxinyu.app.entity.User;
import com.yangxinyu.app.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Date : 2023/3/21 16:47
 * @Author : 星宇
 * @Description :
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private TokenProperties tokenProperties;

    @Override
    public String login(Student student) {
        //AuthenticationManager进行用户认证
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            log.info("用户已登录！");
            return "用户已经登录！";
        }
        log.info("进行身份认证！");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(student.getUsername(),student.getPassword(),null);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //认证失败
        if (Objects.isNull(authenticate)){
            log.info("身份认证失败！");
            throw new RuntimeException();
        }
        log.info("身份认证成功！进行token创建！");
        //将当前用户设置为登录状态
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        //拿取当前登录用户信息
        User user = (User) authenticate.getPrincipal();
        //认证成功创建token
        String token = TokenUtils.createToken(tokenProperties, CurrentUserConverter.INSTANCE.studentToCurrentUser(user.getStudent()));
        //返回给token
        return token;
    }
}
