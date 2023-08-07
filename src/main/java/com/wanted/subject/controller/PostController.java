package com.wanted.subject.controller;

import com.wanted.subject.domain.board.PostDTO;
import com.wanted.subject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/api/board/post")
    public ResponseEntity<String> post(@RequestBody Map<String, Object> req) {
        PostDTO newBoard = (PostDTO) req.get("board");
        postService.save(newBoard);

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
    public ResponseEntity<String> update(@PathVariable("id") Long target, @RequestBody Map<String, Object> req) {
        PostDTO dto = (PostDTO) req.get("board");
        postService.update(target, dto);

        return ResponseEntity.ok("게시글 수정 완료!");
    }

    @PostMapping("/api/board/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable("id") Long target, @RequestBody Map<String, Object> req) {
        postService.delete(target);

        return ResponseEntity.ok("게시글 삭제 완료!");
    }
}
