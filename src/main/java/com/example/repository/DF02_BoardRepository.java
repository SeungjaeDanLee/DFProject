package com.example.repository;

import com.example.dto.DF02_BoardDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DF02_BoardRepository {
    @Autowired
    private SqlSessionTemplate sql;

    // 게시글 작성
    public int writeBoard(DF02_BoardDTO boardDTO) {
        return sql.insert("Board.writeBoard", boardDTO);
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

    // 전체 게시글 갯수
    public int boardCount() {
        return sql.selectOne("Board.boardCount");
    }

    // 자유 게시글 페이징
    public List<DF02_BoardDTO> pagingListFree(Map<String, Integer> pagingParams) {
        return sql.selectList("Board.pagingListFree", pagingParams);
    }

    // 자유 게시글 갯수
    public int boardCountFree() {
        return sql.selectOne("Board.boardCountFree");
    }

    // 정보 게시글 페이징
    public List<DF02_BoardDTO> pagingListInfo(Map<String, Integer> pagingParams) {
        return sql.selectList("Board.pagingListInfo", pagingParams);
    }

    // 정보 게시글 갯수
    public int boardCountInfo() {
        return sql.selectOne("Board.boardCountInfo");
    }

    // 공지 게시글 페이징
    public List<DF02_BoardDTO> pagingListNoti(Map<String, Integer> pagingParams) {
        return sql.selectList("Board.pagingListNoti", pagingParams);
    }

    // 공지 게시글 갯수
    public int boardCountNoti() {
        return sql.selectOne("Board.boardCountNoti");
    }

    // 내가 쓴 게시글 페이징
    public List<DF02_BoardDTO> pagingListMyBoard(Map<String, Integer> pagingParams, int mno) {
        int start = pagingParams.get("start");
        int limit = pagingParams.get("limit");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", start);
        paramMap.put("limit", limit);
        paramMap.put("mno", mno);

        return sql.selectList("Board.pagingListMyBoard", paramMap);
    }


    // 내가 쓴 게시글 갯수
    public int boardCountMyBoard(int mno) {
        return sql.selectOne("Board.boardCountMyBoard", mno);
    }


    // 내가 좋아하는 게시글 페이징
    public List<DF02_BoardDTO> pagingListMyLike(Map<String, Integer> pagingParams, int mno) {
        int start = pagingParams.get("start");
        int limit = pagingParams.get("limit");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", start);
        paramMap.put("limit", limit);
        paramMap.put("mno", mno);

        return sql.selectList("Board.pagingListMyLike", paramMap);
    }

    // 내가 좋아하는 게시글 갯수
    public int boardCountMyLike(int mno) {
        return sql.selectOne("Board.boardCountMyLike", mno);
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

    // 조회수별 상위 10개 게시글 가져오기
    public List<DF02_BoardDTO> findViewBoard10() {
        return sql.selectList("Board.findViewBoard10");
    }

    // 각 게시글별 좋아요수(좋아요 true false와 연계)
    public void increaseLikePoints(int bno) {
        sql.update("Board.increaseLikePoints", bno);
    }

    public void decreaseLikePoints(int bno) {
        sql.update("Board.decreaseLikePoints", bno);
    }

//    public int getNextBno() {
//        sql.selectOne("Board.getNextBno");
//        return getNextBno();
//    }

    public int getNextBno() {
        return sql.selectOne("Board.getNextBno");
    }

}
