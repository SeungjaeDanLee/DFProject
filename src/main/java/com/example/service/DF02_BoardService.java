package com.example.service;

import com.example.dto.DF02_BoardDTO;
import com.example.dto.DF02_PageDTO;
import com.example.repository.DF02_BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DF02_BoardService {
    @Autowired
    DF02_BoardRepository boardRepository;

    // 게시글 작성
    public void write_board(DF02_BoardDTO boardDTO) {
        boardRepository.writeBoard(boardDTO);
    }

//    public List<DF02_BoardDTO> findAllBoard() {
//        return boardRepository.findAllBoard();
//    }

    // 게시글 고유값 찾기
    public DF02_BoardDTO findByBoardBno(int bno) {
        return boardRepository.findByBoardBno(bno);
    }

    // 게시글 수정
    public void update_board(DF02_BoardDTO boardDTO) {
        boardRepository.updateBoard(boardDTO);
    }

    // 게시글 삭제
    public void delete_board(int bno) {
        boardRepository.deleteBoard(bno);
    }


    // 페이징으로 전체 게시판 보여주기
    int pageLimit = 10; // 한 페이지당 보여줄 글 갯수
    int blockLimit = 10; // 하단에 보여줄 페이지 번호 갯수

    // 전체 게시판
    public List<DF02_BoardDTO> pagingList(int page) {
        /*
        1페이지당 보여지는 글 갯수 3
            1page => 0
            2page => 3
            3page => 6
         */
        int pagingStart = (page - 1) * pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        List<DF02_BoardDTO> pagingList = boardRepository.pagingList(pagingParams);

        return pagingList;
    }

    public DF02_PageDTO pagingParam(int page) {
        // 전체 글 갯수 조회
        int boardCount = boardRepository.boardCount();
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        DF02_PageDTO pageDTO = new DF02_PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }

    // 자유 게시판
    public List<DF02_BoardDTO> pagingListFree(int page) {
        int pagingStart = (page - 1) * pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        List<DF02_BoardDTO> pagingListFree = boardRepository.pagingListFree(pagingParams);

        return pagingListFree;
    }

    public DF02_PageDTO pagingParamFree(int page) {
        // 전체 글 갯수 조회
        int boardCount = boardRepository.boardCountFree();
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        DF02_PageDTO pageDTO = new DF02_PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }

    // 정보 게시판
    public List<DF02_BoardDTO> pagingListInfo(int page) {
        int pagingStart = (page - 1) * pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        List<DF02_BoardDTO> pagingListInfo = boardRepository.pagingListInfo(pagingParams);

        return pagingListInfo;
    }

    public DF02_PageDTO pagingParamInfo(int page) {
        // 전체 글 갯수 조회
        int boardCount = boardRepository.boardCountInfo();
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        DF02_PageDTO pageDTO = new DF02_PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }

    // 공지 게시판
    public List<DF02_BoardDTO> pagingListNoti(int page) {
        int pagingStart = (page - 1) * pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        List<DF02_BoardDTO> pagingListNoti = boardRepository.pagingListNoti(pagingParams);

        return pagingListNoti;
    }

    public DF02_PageDTO pagingParamNoti(int page) {
        // 전체 글 갯수 조회
        int boardCount = boardRepository.boardCountNoti();
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        DF02_PageDTO pageDTO = new DF02_PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }

    // 내가 쓴 글 가져오기
    public List<DF02_BoardDTO> pagingListMyBoard(int page, int mno) {
        int pagingStart = (page - 1) * pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        List<DF02_BoardDTO> pagingListMyBoard = boardRepository.pagingListMyBoard(pagingParams, mno);

        return pagingListMyBoard;
    }


    public DF02_PageDTO pagingParamMyBoard(int page, int mno) {
        // 전체 글 갯수 조회
        int boardCount = boardRepository.boardCountMyBoard(mno);
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        DF02_PageDTO pageDTO = new DF02_PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }

    // 조회수 보여주기
    public void view_counts(int bno) {
        boardRepository.viewCounts(bno);
    }

    // 회원 번호와 게시글 번호로 게시글 작성자 찾기
    public int findAuthorMnoByBoardBno(int bno) {
        return boardRepository.findAuthorMnoByBoardBno(bno);
    }

    // 게시글 전체 목록 찾기
    public List<DF02_BoardDTO> findAll() {
        return boardRepository.findAll();
    }



}
