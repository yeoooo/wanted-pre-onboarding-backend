package com.wanted.subject.domain.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    public String title;
    public String content;
    public Long writer;

    public static PostDTO toDTO(Post post) {
        return new PostDTO(post.getTitle(), post.getContents(), post.getUser().getId());
    }
}
