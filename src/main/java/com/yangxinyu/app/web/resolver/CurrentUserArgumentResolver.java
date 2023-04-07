package com.yangxinyu.app.web.resolver;

import com.yangxinyu.app.auth.TokenUtils;
import com.yangxinyu.app.auth.config.TokenProperties;
import com.yangxinyu.app.auth.entity.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

/**
 * @author : 星宇
 * @date : 2023/4/4 13:48
 * @description :
 */
@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Resource
    private TokenProperties tokenProperties;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //判断接口方法上是否存在CurrentUser参数
        return parameter.getParameterType().isAssignableFrom(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return TokenUtils.fromToken(tokenProperties,webRequest.getParameter("Authorization"));
    }
}
