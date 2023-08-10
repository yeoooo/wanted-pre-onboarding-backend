package com.wanted.subject;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class UserDTO {
    @Email
    private String email;

    @Password
    private String password;

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getEmail(), user.getPassword());
    }
}
