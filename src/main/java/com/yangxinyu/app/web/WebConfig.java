package com.yangxinyu.app.web;

import com.yangxinyu.app.auth.config.TokenProperties;
import com.yangxinyu.app.web.interceptor.CommonInterceptor;
import com.yangxinyu.app.web.resolver.CurrentUserArgumentResolver;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : 星宇
 * @date : 2023/4/4 13:50
 * @description :
 */
@SpringBootConfiguration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 通用拦截器
     * @return
     */
    @Resource
    private CommonInterceptor commonInterceptor;
    /**
     * CurrentUser的方法参数解析器
     */
    @Resource
    private CurrentUserArgumentResolver currentUserArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor)
                .addPathPatterns("/student/**")
                .order(15);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserArgumentResolver);
    }
}
