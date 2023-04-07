package com.yangxinyu.app.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yangxinyu.app.auth.config.TokenProperties;
import com.yangxinyu.app.auth.entity.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu.app.auth
 * @Date : 2023/3/21 9:57
 * @Author : 星宇
 * @Description :
 */
@Slf4j
public class TokenUtils {
    /**
     * token在请求头重的名称
     */
    public static final String AUTH_KEY = "Authorization";

    /**
     * 对盐进行加密
     * @param secret
     * @return
     */
    private static Algorithm getAlgorithm(String secret) {
        return Algorithm.HMAC256(secret);
    }

    /**
     * 从请求头重拿取token
     * @param request
     * @return
     */
    static String findToken(HttpServletRequest request) {
        String token = request.getHeader(AUTH_KEY);
        return token;
    }

    /**
     * 创建token
     * @param props
     * @param currentUser
     * @return
     */
    public static String createToken(TokenProperties props, CurrentUser currentUser) {
        JWTCreator.Builder builder = JWT.create();
        return builder
                .withClaim("uid", currentUser.getId())//存放键值对，注意存的值的类型，要与下面as..对应
                .withIssuer(props.getIssuer())//签发者
                .withExpiresAt(props.getExpiresAt())//过期时间
                .sign(getAlgorithm(props.getSecret()));//加密后的盐
    }

    /**
     * 从解析后的token重拿取CurrentUser
     * @param props
     * @param token
     * @return
     */
    public static CurrentUser fromToken(TokenProperties props, String token) {
        DecodedJWT decodedJWT = verify(props, token);
        try {
            return CurrentUser.builder().id(decodedJWT.getClaim("uid").as(Integer.class)).build();
        } catch (NullPointerException e) {
            log.error("Token解码失败导致无法获取token信息！");
        }
        return null;
    }

    /**
     * 解析token
     * @param props
     * @param token
     * @return
     */
    private static DecodedJWT verify(TokenProperties props, String token) {
        JWTVerifier verifier = JWT.require(getAlgorithm(props.getSecret()))
                .withIssuer(props.getIssuer())
                .build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (TokenExpiredException ex) {
            log.info("Token已经失效::{},{}", ex.getMessage(), token);
        } catch (AlgorithmMismatchException | SignatureVerificationException | InvalidClaimException ex) {
            log.warn("Token签名校验失败::{},{}", ex.getMessage(), token);
        } catch (Exception ex) {
            log.error("Token解码失败::{}, {}", ex.getMessage(), token);
        }
        return null;
    }
}
