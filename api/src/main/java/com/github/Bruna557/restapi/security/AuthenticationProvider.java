package com.github.Bruna557.restapi.security;

import com.github.Bruna557.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        Object token= usernamePasswordAuthenticationToken.getCredentials();
        if(userService.findByToken(token.toString()).isPresent()) {
            return new User(
                userService.findByToken(token.toString()).get().getUserName(),
                userService.findByToken(token.toString()).get().getUserPassword(),
                true, true, true, true,
                AuthorityUtils.createAuthorityList("USER")
            );
        }
        throw new UsernameNotFoundException("Cannot find user with authentication token=" + token);
    }
}