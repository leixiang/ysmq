package com.nsyun.server.services.common;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nsyun.server.domain.common.UserRepositoryUserDetails;
import com.nsyun.server.domain.users.Authority;
import com.nsyun.server.domain.users.User;
import com.nsyun.server.repository.users.UserRepository;
import com.nsyun.utils.exception.UserNotActivatedException;



@Service("customUserDetailsService")
//@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

   
    @Autowired
    private UserRepository userRepository;
 

   // @Autowired
    //private Md5PasswordEncoder md5PasswordEncoder;
  	
   // @Autowired
   // private SecureRandom secureRandom;
    
    @Override
    public UserDetails loadUserByUsername(final String login) {

        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase();

        User userFromDatabase;
        if(lowercaseLogin.contains("@")) {
            userFromDatabase = userRepository.findByEmail(lowercaseLogin);
        } else {
            userFromDatabase = userRepository.findByUsernameCaseInsensitive(lowercaseLogin);
        }
        
        if (userFromDatabase == null) {
        	userFromDatabase = userRepository.findUserByMobile(lowercaseLogin);
        } 

        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database");
        } else if (!userFromDatabase.getActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " is not activated");
        }

       Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : userFromDatabase.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }

        //String decodePassword = md5PasswordEncoder.userFromDatabase.getPassword();
        
       // UserCredentials userCredentials = UserCredentials.fromUserDetails(userFromDatabase);
       // return userCredentials;
        return new UserRepositoryUserDetails(userFromDatabase);
        
      //return new org.springframework.security.core.userdetails.User(userFromDatabase.getUsername(), userFromDatabase.getPassword(), grantedAuthorities);
    }
    
    
   
 
}
