package com.example.service;

import com.example.dto.DF02_BoardDTO;
import com.example.repository.DF02_BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DF02_BoardService {
    @Autowired
    DF02_BoardRepository boardRepository;

    public void write_board(DF02_BoardDTO boardDTO) {
        boardRepository.writeBoard(boardDTO);
    }
}
