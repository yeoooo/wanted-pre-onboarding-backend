package com.wanted.subject.service;

import com.wanted.subject.domain.board.PostDTO;
import com.wanted.subject.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    Long save(User user, PostDTO dto);

    Long update(User user, Long from, PostDTO to) throws IllegalAccessException;

    Long delete(Long id);

    PostDTO findById(Long id);

    Page<PostDTO> findAll(Pageable pageable);


}
