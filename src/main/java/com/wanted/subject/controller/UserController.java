package com.wanted.subject.controller;

import com.wanted.subject.domain.user.Password;
import com.wanted.subject.domain.user.UserDTO;
import com.wanted.subject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody Map<String, Object> req) {
        Map<String, String> user = (Map<String, String>) req.get("user");
        UserDTO dto = new UserDTO(user.get("email"), user.get("password"));
        userService.join(dto);

        return ResponseEntity.ok("회원가입 완료!");
    }

    @GetMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody Map<String, Object> map) {
        @Email
        String email = map.get("email").toString();

        @Password
        String password = map.get("password").toString();

        return ResponseEntity.ok(userService.login(email, password));
    }
}
