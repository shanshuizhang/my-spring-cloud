package com.zss.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/30 16:52
 */
@Configuration
public class RedisRateLimiterConfig {

    @Bean
    public KeyResolver userNameKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("username"));
    }

    @Primary
    @Bean
    public KeyResolver ipKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
}
