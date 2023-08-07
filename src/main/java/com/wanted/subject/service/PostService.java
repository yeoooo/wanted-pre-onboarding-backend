package com.wanted.subject.service;

import com.wanted.subject.domain.board.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Long save(PostDTO dto);

    Long update(Long from, PostDTO to);

    Long delete(Long id);

    PostDTO findById(Long id);

    Page<PostDTO> findAll(Pageable pageable);


}
