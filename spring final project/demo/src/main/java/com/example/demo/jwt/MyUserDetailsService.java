package com.example.demo.jwt;

import com.example.demo.daoPackage.UsersDAO;
import com.example.demo.pocoPackage.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private UsersDAO userDao=new UsersDAO();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userRes = userDao.getUserByUsername(username);
        String ROLE_PREFIX ="ROLE_";
        if(userRes==null)
            throw new UsernameNotFoundException("Could not findUser with username = " + username);
        User user = userRes;
        return new org.springframework.security.core.userdetails.User(
                username,
                user.password,
                Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX+userDao.joinUserWithUserRole(username))));
    }
}