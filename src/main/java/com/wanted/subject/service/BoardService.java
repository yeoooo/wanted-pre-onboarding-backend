package com.wanted.subject.service;

import com.wanted.subject.domain.board.BoardDTO;

import java.util.List;

public interface BoardService {

    Long save(BoardDTO dto);

    Long update(Long from, BoardDTO to);

    Long delete(Long id);

    BoardDTO findById(Long id);

    List<BoardDTO> findAll();


}
