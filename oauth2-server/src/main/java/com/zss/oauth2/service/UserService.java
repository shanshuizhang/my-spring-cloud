package com.zss.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.zss.oauth2.domain.User;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2020/1/12 16:10
 */
@Service
public class UserService implements UserDetailsService {

    private List<User> userList;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData(){
        String password = passwordEncoder.encode("123456");
        userList = new ArrayList<>(4);
        userList.add(User.builder()
                .username("zss")
                .password(password)
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"))
                .build());
        userList.add(User.builder()
                .username("zfg")
                .password(password)
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("client"))
                .build());
        userList.add(User.builder()
                .username("idea")
                .password(password)
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("client"))
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> findUserList = userList.stream()
                .filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList());
        if(CollectionUtils.isEmpty(findUserList)){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return findUserList.get(0);
    }
}
