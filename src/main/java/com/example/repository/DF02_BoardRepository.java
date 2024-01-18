package com.example.repository;

import com.example.dto.DF02_BoardDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DF02_BoardRepository {
    @Autowired
    private SqlSessionTemplate sql;

    public void writeBoard(DF02_BoardDTO boardDTO) {
        sql.insert("Board.writeBoard", boardDTO);
    }
}
