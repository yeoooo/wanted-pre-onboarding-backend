package com.wanted.subject.service;

import com.wanted.subject.domain.board.Post;
import com.wanted.subject.domain.board.PostDTO;
import com.wanted.subject.domain.board.PostRepository;
import com.wanted.subject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public Long save(User user, PostDTO dto) {
        Post newOne = new Post(null, dto.getTitle(), dto.getContent(), user);
        postRepository.save(newOne);
        return newOne.getId();
    }

    @Override
    @Transactional
    public Long update(User user, Long from, PostDTO to) throws IllegalAccessException {
        Post found = postRepository.findById(from).orElseThrow(() -> new NoSuchElementException());
        if (found.getUser().equals(user)) {
            postRepository.save(
                    new Post(found.getId()
                            , to.getTitle().length() > 0 ? to.getTitle() : found.getTitle()
                            , to.getContent().length() > 0 ? to.getContent() : found.getContents(),
                            user)
            );
        }else{
            throw new IllegalAccessException("허용되지 않은 사용자입니다.");
        }
        return from;
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        postRepository.deleteById(id);
        return id;
    }

    @Override
    @Transactional
    public PostDTO findById(Long id) {
        return PostDTO.toDTO(postRepository.findById(id).orElseThrow(() -> new NoSuchElementException()));
    }

    @Override
    @Transactional
    public Page<PostDTO> findAll(Pageable pageable) {
        return postRepository.findAll(pageable).map(x -> PostDTO.toDTO(x));
    }
}
