package com.wanted.subject.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Email
    private String email;

    @Password
    private String password;

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getEmail(), user.getPassword());
    }
}
