package com.example.repository;

import com.example.dto.DF02_BoardDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DF02_BoardRepository {
    @Autowired
    private SqlSessionTemplate sql;

    // 게시글 작성
    public void writeBoard(DF02_BoardDTO boardDTO) {
        sql.insert("Board.writeBoard", boardDTO);
    }


    // 게시글 목록 불러오기
//    public List<DF02_BoardDTO> findAllBoard() {
//        return sql.selectList("Board.findAllBoard");
//    }

    // 게시글 bno로 찾아오기
    public DF02_BoardDTO findByBoardBno(int bno) {
        return sql.selectOne("Board.findByBoardBno", bno);
    }

    // 전체 게시글 페이징
    public List<DF02_BoardDTO> pagingList(Map<String, Integer> pagingParams) {
        return sql.selectList("Board.pagingList", pagingParams);
    }

    // 게시글 갯수
    public int boardCount() {
        return sql.selectOne("Board.boardCount");
    }

    // 게시글 조회수
    public void viewCounts(int bno) {
        sql.update("Board.view_counts", bno);
    }


    // 게시글 업데이트
    public void updateBoard(DF02_BoardDTO boardDTO) {
        sql.update("Board.updateBoard", boardDTO);
    }

    // 게시글 삭제
    public void deleteBoard(int bno) {
        sql.delete("Board.deleteBoard", bno);
    }

    // 작성자 고유값 + 게시글 고유값
    public int findAuthorMnoByBoardBno(int bno) {
        return sql.selectOne("Board.findAuthorMnoByBoardBno", bno);
    }

    // 모든 게시글 찾기
    public List<DF02_BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }
}
