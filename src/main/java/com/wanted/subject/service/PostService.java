package com.wanted.subject.service;

import com.wanted.subject.domain.board.PostDTO;
import com.wanted.subject.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Long save(Long user, PostDTO dto);

    Long update(Long user, Long from, PostDTO to) throws IllegalAccessException;

    Long delete(Long user, Long target) throws IllegalAccessException;

    PostDTO findById(Long id);

    Page<PostDTO> findAll(Pageable pageable);


}
