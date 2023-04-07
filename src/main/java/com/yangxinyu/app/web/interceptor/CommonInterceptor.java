package com.yangxinyu.app.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangxinyu.app.auth.TokenUtils;
import com.yangxinyu.app.auth.config.TokenProperties;
import com.yangxinyu.app.auth.entity.CurrentUser;
import com.yangxinyu.app.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author : 星宇
 * @date : 2023/4/4 14:10
 * @description :
 */
@Component
@Slf4j
public class CommonInterceptor implements HandlerInterceptor {
    @Resource
    private TokenProperties tokenProperties;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断请求头中是否存在token
        String token = request.getHeader(TokenUtils.AUTH_KEY);
        if (Objects.isNull(token)){
            log.warn("token不存在！");
            errorMessage(response);
            return false;
        }
        //校验token是否合法
        CurrentUser currentUser = TokenUtils.fromToken(tokenProperties, token);
        if (Objects.isNull(currentUser)){
            log.warn("token不合法！");
            errorMessage(response);
            return false;
        }
        log.info("token校验成功！");
        return true;
    }

    //未登录的信息
    private void errorMessage(HttpServletResponse response) throws IOException {
        //设置响应
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new ObjectMapper().writeValueAsString(Result.err(403, "未登录！")).getBytes(StandardCharsets.UTF_8);
        outputStream.write(bytes);
        outputStream.close();
    }
}
