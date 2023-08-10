package com.wanted.subject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.wanted.subject.domain.board.PostDTO;
import com.wanted.subject.domain.user.User;
import com.wanted.subject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/api/board/post")
    public ResponseEntity<String> post(@RequestBody Map<String, Object> req, @AuthenticationPrincipal User user) {
        Map<String, Object> post = (Map<String, Object>) req.get("post");

        postService.save(user.getId(), new PostDTO(post.get("title").toString(), post.get("contents").toString(), (Long) post.get("writer")));

        return ResponseEntity.ok("게시 완료!");
    }

    @GetMapping("/api/boards")
    public ResponseEntity<Page<PostDTO>> findAll(Pageable pageable) {
        Page<PostDTO> founds = postService.findAll(pageable);

        return ResponseEntity.ok(founds);
    }

    @GetMapping("/api/board/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable("id") Long id) {
        PostDTO found = postService.findById(id);

        return ResponseEntity.ok(found);
    }

    @PostMapping("/api/board/{id}/update")
    public ResponseEntity<String> update(@PathVariable("id") Long target, @RequestBody Map<String, Object> req, @AuthenticationPrincipal User user) throws IllegalAccessException {
        Map<String, Object> post = (Map<String, Object>) req.get("post");

        PostDTO dto = new PostDTO(post.get("title").toString(), post.get("content").toString(), user.getId());
        postService.update(user.getId(), target, dto);

        return ResponseEntity.ok("게시글 수정 완료!");
    }

    @PostMapping("/api/board/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable("id") Long target, @AuthenticationPrincipal User user) throws IllegalAccessException {
        postService.delete(user.getId(), target);

        return ResponseEntity.ok("게시글 삭제 완료!");
    }

    @ExceptionHandler({NoSuchElementException.class, IllegalAccessException.class})
    public ResponseEntity<String> postExceptionHandler(Exception e) throws JsonProcessingException {
        Map<String, Object> resp = new HashMap<>();

        resp.put("msg", e.getMessage() == null ? "해당 게시글이 존재하지 않습니다." : e.getMessage());
        resp.put("error", e.getClass());

        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(new JsonMapper().writeValueAsString(resp));
    }
}
