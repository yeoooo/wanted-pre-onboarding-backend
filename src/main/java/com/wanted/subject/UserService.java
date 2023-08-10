package com.wanted.subject;

import com.wanted.subject.UserDTO;

public interface UserService {
    Long join(UserDTO dto);

    UserDTO findById(Long id);

    String login(String email, String password);

}
