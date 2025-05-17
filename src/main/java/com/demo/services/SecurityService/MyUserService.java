package com.demo.services.SecurityService;

import com.demo.model.User;
import com.demo.repositories.UserRepository;
import com.demo.services.SecurityService.MyUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> client = userRepository.getSecurityUserByLogin(login);
        return client.map(MyUserDetails::new).orElseThrow( () -> new UsernameNotFoundException(login + "There is not such user in Data base"));
    }
}
