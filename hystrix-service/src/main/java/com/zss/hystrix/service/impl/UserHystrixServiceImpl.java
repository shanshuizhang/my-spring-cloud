package com.zss.hystrix.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.zss.hystrix.dto.UserDto;
import com.zss.hystrix.service.UserHystrixService;
import com.zss.springcloud.common.dto.RestResultDto;
import com.zss.springcloud.common.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2019/12/26 19:09
 */
@Service
@Slf4j
public class UserHystrixServiceImpl implements UserHystrixService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceURL;

    @Override
    @HystrixCommand(fallbackMethod = "fallBackUser")
    public RestResultDto getUser(Long id) {
        return restTemplate.getForObject(userServiceURL + "/user/{1}",RestResultDto.class,id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallBackUser",
        commandKey = "getUserCommand",
        groupKey = "getUserGroup",
        threadPoolKey = "getUserThreadPool")
    public RestResultDto testCommond(Long id) {
        return restTemplate.getForObject(userServiceURL + "/user/{1}",RestResultDto.class,id);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallBackUser2",ignoreExceptions = {NullPointerException.class})
    public RestResultDto testException(Long id) {
        if(id == 1){
            throw new IndexOutOfBoundsException();
        }else if(id == 2){
            throw new NullPointerException();
        }
        return restTemplate.getForObject(userServiceURL + "/user/{1}",RestResultDto.class,id);
    }

    private RestResultDto fallBackUser(@PathVariable Long id){
        UserDto user = UserDto.builder()
                .username("李四")
                .password("234")
                .age(id.intValue())
                .gender(0)
                .build();
        return RestResultDto.success(user);
    }

    private RestResultDto fallBackUser2(@PathVariable Long id,Throwable e){
        log.error("fallBackUser2 id:[{}],Throwable Class:[{}]",id,e.getClass());
        UserDto user = UserDto.builder()
                .username("李四")
                .password("234")
                .age(id.intValue())
                .gender(0)
                .build();
        return RestResultDto.success(user);
    }

    @Override
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "fallBackUser",commandKey = "cache123")
    public RestResultDto testCache(Long id) {
        log.info("参数id:[{}]",id);
        return restTemplate.getForObject(userServiceURL + "/user/{1}",RestResultDto.class,id);
    }

    public String getCacheKey(Long id) {
        return String.valueOf(id);
    }

    @Override
    @CacheRemove(commandKey = "cache123",cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public RestResultDto testRemoveCache(Long id) {
        return restTemplate.getForObject(userServiceURL + "/user/{1}",RestResultDto.class,id);
    }

    @Override
    @HystrixCollapser(batchMethod = "getUserByIds",collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds",value = "100")
    })
    public Future<UserDto> getUserFutrue(Long id) {
        return new com.netflix.hystrix.contrib.javanica.command.AsyncResult<UserDto>() {
            @Override
            public UserDto invoke() {
                RestResultDto restResultDto = restTemplate.getForObject(userServiceURL + "/user/{1}", RestResultDto.class, id);
                UserDto userDto = (UserDto)restResultDto.getData();
                log.info("getUserFutrue username:{}", userDto.getUsername());
                return userDto;
            }
        };
    }

    @HystrixCommand
    public List<UserDto> getUserByIds(List<Long> ids){
        log.info("getUserByIds,ids:[{}]",ids);
        RestResultDto restResultDto = restTemplate.getForObject(userServiceURL + "/user/getByIds?ids={1}", RestResultDto.class, CollectionUtil.join(ids,","));
        return (List<UserDto>)restResultDto.getData();
    }
}
