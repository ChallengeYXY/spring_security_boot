package com.yangxinyu.app.config;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu.app.config
 * @Date : 2023/3/20 15:02
 * @Author : 星宇
 * @Description :
 */
@SpringBootConfiguration//声明配置类
@ConfigurationProperties("spring.jedis")//批量注入属性
@Data
public class JedisConfig {
    private String host;

    private int port;

    private String password;

    private int timeout;

    private int maxTotal;

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,password);
        return jedisPool;
    }
}
