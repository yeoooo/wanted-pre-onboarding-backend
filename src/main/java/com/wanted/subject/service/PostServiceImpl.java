package com.wanted.subject.service;

import com.wanted.subject.domain.board.PostDTO;
import com.wanted.subject.domain.board.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Long save(PostDTO dto) {
        return null;
    }

    @Override
    public Long update(Long from, PostDTO to) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public PostDTO findById(Long id) {
        return null;
    }

    @Override
    public Page<PostDTO> findAll(Pageable pageable) {
        return null;
    }
}
