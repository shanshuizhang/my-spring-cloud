package com.zss.api.limit.aop;

import com.google.common.collect.ImmutableList;
import com.zss.api.limit.annotation.AccessLimit;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Configuration
@Aspect
@Slf4j
public class LimitAop {

    private static final String UNKNOWN = "unknown";

    @Autowired
    private RedisTemplate redisTemplate;

    @Around("execution(public * *(..)) && @annotation(com.zss.api.limit.annotation.AccessLimit)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
        AccessLimit.LimitType limitType = accessLimit.limitType();

        String name = accessLimit.name();
        String key;
        int limitPeriod = accessLimit.period();
        int limitCount = accessLimit.count();

        switch (limitType){
            case IP:
                key = getIpAddress();
                break;
            case CUSTOMER:
                key = accessLimit.key();
                break;
            default:
                key = Strings.toRootUpperCase(method.getName());
        }

        ImmutableList<String> keys = ImmutableList.of(String.join(accessLimit.prefix(),key));

        String luaScript = buildLuaScript();
        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
        Number count = (Number) redisTemplate.execute(redisScript,keys,limitCount,limitPeriod);
        if(count != null && count.intValue() <= limitCount){
            return pjp.proceed();
        }else {
            throw new RuntimeException("You have been dragged into the blacklist");
        }
    }

    private String buildLuaScript(){
        StringBuilder lua = new StringBuilder();
        lua.append("local c");
        lua.append("\nc = redis.call('get',KEYS[1])");
        // 调用不超过最大值，则直接返回
        lua.append("\nif c and tonumber(c) > tonumber(ARGV[1]) then");
        lua.append("\nreturn c;");
        lua.append("\nend");
        // 执行计算器自加
        lua.append("\nc = redis.call('incr',KEYS[1])");
        lua.append("\nif tonumber(c) == 1 then");
        // 从第一次调用开始限流，设置对应键值的过期
        lua.append("\nredis.call('expire',KEYS[1],ARGV[2])");
        lua.append("\nend");
        lua.append("\nreturn c;");
        return lua.toString();
    }

    private String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
