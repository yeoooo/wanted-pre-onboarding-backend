package com.wanted.subject.service;

import com.wanted.subject.config.JwtUtil;
import com.wanted.subject.domain.user.User;
import com.wanted.subject.domain.user.UserDTO;
import com.wanted.subject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;

    @Override
    public Long join(UserDTO dto) {
        User newOne = new User(null, dto.getEmail(), passwordEncoder.encode(dto.getPassword()), null);

        userRepository.save(newOne);
        log.info("새로운 회원 = {}", newOne);
        return newOne.getId();
    }

    @Override
    public UserDTO findById(Long id) {
        return UserDTO.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException()));
    }

    @Override
    public String login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return jwtUtil.generateToken(email);
    }

}
