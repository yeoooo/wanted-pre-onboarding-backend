package com.wanted.subject.domain.board;

import javax.persistence.*;

@Table(name = "Boards")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
