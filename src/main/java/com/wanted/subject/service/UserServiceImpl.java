package com.wanted.subject.service;

import com.wanted.subject.domain.user.UserDTO;
import com.wanted.subject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public Long join(UserDTO dto) {
        return null;
    }

    @Override
    public UserDTO findById(Long id) {
        return null;
    }
}
