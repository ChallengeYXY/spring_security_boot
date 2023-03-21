package com.yangxinyu.app.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu.app.auth.config
 * @Date : 2023/3/21 10:00
 * @Author : 星宇
 * @Description :
 */
@Data
@Component
@ConfigurationProperties("app.auth.token")
public class TokenProperties {
    private String secret = "dfaccaFWSEFds";//盐

    private Duration expires = Duration.ofHours(8);//过期时间

    private String issuer = "yangxinyu.com";//签发者

    public Date getExpiresAt() {
        return Date.from(Instant.now().plus(getExpires()));
    }
}
