package com.cricfant.service;

import com.cricfant.constant.Role;
import com.cricfant.dto.CustomPrincipal;
import com.cricfant.dto.UserDto;
import com.cricfant.model.User;
import com.cricfant.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("appUserService")
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s).orElseThrow(
                () -> new UsernameNotFoundException("username [" + s + "] not found"));
        return getUserDetails(user);
    }

    public UserDto register(UserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("email already exists");
        }
        User u = new User();
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        u.setRole(Role.ROLE_USER);
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        User save = userRepository.save(u);
        return getUserDto(save);
    }

    private UserDto getUserDto(User user) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto, "password");
        return dto;
    }

    private UserDetails getUserDetails(User user) {
        CustomPrincipal principal = new CustomPrincipal();
        BeanUtils.copyProperties(user,principal);
        return principal;
    }

    public UserDto getUserProfile(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("no such user"));
        return getUserDto(user);
    }
}
