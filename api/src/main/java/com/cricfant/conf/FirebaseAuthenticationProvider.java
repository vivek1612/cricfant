package com.cricfant.conf;

import com.cricfant.constant.Role;
import com.cricfant.service.UserService;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FirebaseAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    public boolean supports(Class<?> authentication) {
        return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }
        FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;
        FirebaseToken creds = (FirebaseToken) authenticationToken.getCredentials();
        UserDetails details = null;
        try {
            details = userService.loadUserByUsername(creds.getUid());
        } catch (UsernameNotFoundException e) {
            log.info("creating user: "+creds.getUid());
            details = userService.createUser(creds.getUid(),
                    creds.getName(), creds.getEmail(), Role.ROLE_USER);
        }
        authenticationToken = new FirebaseAuthenticationToken(details, authentication.getCredentials(),
                details.getAuthorities());
        return authenticationToken;
    }

}

