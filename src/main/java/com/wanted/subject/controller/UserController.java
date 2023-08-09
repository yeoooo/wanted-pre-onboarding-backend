package com.wanted.subject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.wanted.subject.domain.user.UserDTO;
import com.wanted.subject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserDTO user) {
        String email = user.getEmail();
        String password = user.getPassword();

        UserDTO dto = new UserDTO(email, password);
        userService.join(dto);

        return ResponseEntity.ok("회원가입 완료!");
    }

    @GetMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserDTO user) {
        String email = user.getEmail();
        String password = user.getPassword();

        return ResponseEntity.ok(userService.login(email, password));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> loginExceptionHandler(Exception e, BindingResult bindingResult) throws JsonProcessingException {
        Map<String, Object> resp = new HashMap<>();

        resp.put("msg", bindingResult.getFieldError().getDefaultMessage());
        resp.put("error", e.getClass());

        return ResponseEntity.badRequest().body(new JsonMapper().writeValueAsString(resp));
    }
}
