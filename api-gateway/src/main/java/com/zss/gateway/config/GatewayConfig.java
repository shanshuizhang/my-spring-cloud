package com.zss.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/30 11:18
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route2",p->p.path("/user/getByUsername2")
                        .uri("http://localhost:7766/user/getByUsername"))
                .build();
    }
}
