package com.wanted.subject;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.NoSuchElementException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostServiceTest {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    static Long BASE_TEST_POST_ID = 1L;
    static Long BASE_TEST_USER_ID = 1L;

    @BeforeAll
    void setup() {
        UserDTO user1 = new UserDTO("test@mail.com", "password");
        userService.join(user1);

        PostDTO post1 = new PostDTO("test_title_1", "test_content_1", BASE_TEST_USER_ID);
        postService.save(BASE_TEST_USER_ID, post1);
    }

    @Test
    @DisplayName("게시글 등록 테스트")
    void save() {
        //given
        PostDTO post2 = new PostDTO("test_title_2", "test_content_2", BASE_TEST_USER_ID);

        //when
        Long saved = postService.save(BASE_TEST_POST_ID, post2);

        //then
        Assertions.assertDoesNotThrow(() -> {
            System.out.println(postService.findById(saved).toString());
        });

    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void update() throws IllegalAccessException {
        //given
        PostDTO before = postService.findById(BASE_TEST_POST_ID);
        PostDTO to = new PostDTO("modified_title_2", "modified_content_2", BASE_TEST_USER_ID);
        //when
        postService.update(BASE_TEST_USER_ID, BASE_TEST_POST_ID, to);
        PostDTO after = postService.findById(BASE_TEST_POST_ID);
        //then
        org.assertj.core.api.Assertions.assertThat(before.getTitle()).isNotEqualTo(after.getTitle());
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void delete() throws IllegalAccessException {
        //when
        PostDTO post2 = new PostDTO("test_title_2", "test_content_2", BASE_TEST_USER_ID);
        Long saved = postService.save(BASE_TEST_USER_ID, post2);
        //when
        postService.delete(BASE_TEST_USER_ID, saved);
        //then
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            postService.findById(saved);
        });
    }

    @Test
    @DisplayName("게시글 단일 조회 테스트")
    void findById() {
        //given
        Long target = BASE_TEST_POST_ID;
        //when
        //then
        Assertions.assertDoesNotThrow(() -> {
            System.out.println("target found = " +postService.findById(target));
        });

    }

    @Test
    @DisplayName("게시글 전체 조회, Pagination 테스트")
    void findAll() {
        //given
        int size = postService.findAll(Pageable.unpaged()).getSize();
        PostDTO post2 = new PostDTO("test_title_2", "test_content_2", BASE_TEST_USER_ID);
        PostDTO post3 = new PostDTO("test_title_3", "test_content_3", BASE_TEST_USER_ID);
        PostDTO post4 = new PostDTO("test_title_4", "test_content_4", BASE_TEST_USER_ID);
        PostDTO post5 = new PostDTO("test_title_5", "test_content_5", BASE_TEST_USER_ID);
        PostDTO post6 = new PostDTO("test_title_6", "test_content_6", BASE_TEST_USER_ID);

        //when
        postService.save(BASE_TEST_USER_ID, post2);
        postService.save(BASE_TEST_USER_ID, post3);
        postService.save(BASE_TEST_USER_ID, post4);
        postService.save(BASE_TEST_USER_ID, post5);
        postService.save(BASE_TEST_USER_ID, post6);

        int new_size = postService.findAll(Pageable.unpaged()).getSize();
        Page founds = postService.findAll(Pageable.unpaged());

        //then
        org.assertj.core.api.Assertions.assertThat(size).isLessThan(new_size);
        org.assertj.core.api.Assertions.assertThat(founds).isInstanceOf(Page.class);


    }
}