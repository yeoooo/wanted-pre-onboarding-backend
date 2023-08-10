package com.wanted.subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserDTO {
    @Email
    private String email;

    @Password
    private String password;

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getEmail(), user.getPassword());
    }
}
