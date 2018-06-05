package com.cricfant.service;

import com.cricfant.constant.Role;
import com.cricfant.model.User;
import com.cricfant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUid(s).orElseThrow(
                () -> new UsernameNotFoundException("username ["+s+"] not found"));
        return getUserDetails(user);
    }

    public UserDetails createUser(String uid, String name, String email, Role role) {
        User u = new User();
        u.setUid(uid);
        u.setName(name);
        u.setEmail(email);
        u.setRole(role);
        User save = userRepository.save(u);
        return getUserDetails(save);
    }

    private UserDetails getUserDetails(User user) {
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return AuthorityUtils.commaSeparatedStringToAuthorityList(
                        user.getRole().toString());
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return user.getUid();
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
        return userDetails;
    }
}
