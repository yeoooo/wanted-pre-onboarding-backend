package com.wanted.subject.domain.board;

import com.wanted.subject.domain.user.User;

import javax.persistence.*;

@Table(name = "Boards")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String contents;

    @ManyToOne
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
}
