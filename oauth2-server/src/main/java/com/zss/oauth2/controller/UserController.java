package com.zss.oauth2.controller;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2020/1/12 17:25
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) {
        String tokenHeader = request.getHeader("Authorization");
        String token = StrUtil.subAfter(tokenHeader,"bearer ",false);
        log.info("token ==> {}",token);
        return Jwts.parser()
                .setSigningKey("miyao_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
