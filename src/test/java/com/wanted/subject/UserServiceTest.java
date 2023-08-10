package com.wanted.subject;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @Autowired
    UserService userService;

    static Long BASE_TEST_USER_ID = 1L;
    @BeforeAll
    void setup() {
        UserDTO user1 = new UserDTO("test@mail.com", "password");
        userService.join(user1);
    }

    @Test
    @DisplayName("회원 등록 테스트")
    void join() {
        //given
        UserDTO user2 = new UserDTO("test@mail.com", "password");
        //when
        Long saved = userService.join(user2);
        //then
        Assertions.assertDoesNotThrow(() -> {
            System.out.println("found : " + userService.findById(saved));
        });

    }

    @Test
    @DisplayName("회원 단일 조회 테스트")
    void findById() {
        //given
        Long target = 1L;
        //when
        //then
        Assertions.assertDoesNotThrow(() -> {
            System.out.println("found : " + userService.findById(target));
        });

    }

}