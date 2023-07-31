package com.wanted.subject.service;

import com.wanted.subject.domain.user.UserDTO;

public interface UserService {
    Long join(UserDTO dto);

    UserDTO findById(Long id);

}
