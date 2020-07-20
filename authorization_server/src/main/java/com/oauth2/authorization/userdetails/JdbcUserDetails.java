package com.oauth2.authorization.userdetails;

import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class JdbcUserDetails implements UserDetailsService {

    @Autowired
    private CredentialsDao credentialsDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Credentials credentials = credentialsDao.findByName(username);
        if (credentials == null) {
            throw new UsernameNotFoundException("User '" + username + "' can not be found");
        }

        //此处授权也可以用credentials.getAuthorities(),但是在资源服务器解析的时候要引入自定义的com.oauth2.authorization.userdetails.Authority类否则会报找不到类对象而解析失败
        return new User(credentials.getName(), credentials.getPassword(), credentials.isEnabled(), true, true, true, credentials.getGrantedAuthorities());
    }

}
