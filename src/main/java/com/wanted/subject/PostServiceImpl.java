package com.wanted.subject;

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
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long save(Long user_id, PostDTO dto) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new NoSuchElementException());
        Post newOne = new Post(null, dto.getTitle(), dto.getContent(), user);
        user.addPost(newOne);
        postRepository.save(newOne);
        return newOne.getId();
    }

    @Override
    @Transactional
    public Long update(Long user_id, Long from, PostDTO to) throws IllegalAccessException {
        Post found = postRepository.findById(from).orElseThrow(() -> new NoSuchElementException());
        User user = userRepository.findById(user_id).orElseThrow(() -> new NoSuchElementException());
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
    public Long delete(Long user_id, Long target) throws IllegalAccessException {
        User user = userRepository.findById(user_id).orElseThrow(() -> new NoSuchElementException());
        Post found = postRepository.findById(target).orElseThrow(() -> new NoSuchElementException());
        if (found.getUser().equals(user)) {
            postRepository.delete(found);
        }else{
            throw new IllegalAccessException("허용되지 않은 사용자입니다.");
        }
        return target;
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
