package com.wanted.subject.service;

import com.wanted.subject.domain.board.BoardDTO;
import com.wanted.subject.domain.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Long save(BoardDTO dto) {
        return null;
    }

    @Override
    public Long update(Long from, BoardDTO to) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public BoardDTO findById(Long id) {
        return null;
    }

    @Override
    public List<BoardDTO> findAll() {
        return null;
    }
}
