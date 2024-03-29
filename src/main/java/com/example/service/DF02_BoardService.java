package com.example.service;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF02_BoardDTO;
import com.example.dto.DF02_PageDTO;
import com.example.filter.RequestWrapper;
import com.example.repository.DF02_BoardRepository;
import com.example.repository.DF04_LikePointRepository;
import com.example.util.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DF02_BoardService {
    @Autowired
    DF01_MemberService memberService;

    @Autowired
    DF02_BoardRepository boardRepository;

    @Autowired
    DF04_LikePointRepository likePointRepository;

    @Autowired
    DF05_FileService fileService;

    // 게시글 작성
    public int write_board(DF02_BoardDTO boardDTO) {
        return boardRepository.writeBoard(boardDTO);
    }

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

    // 내가 좋아하는 글 가져오기
    public List<DF02_BoardDTO> pagingListMyLike(int page, int mno) {
        int pagingStart = (page - 1) * pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        List<DF02_BoardDTO> pagingListMyLike = boardRepository.pagingListMyLike(pagingParams, mno);

        return pagingListMyLike;
    }

    public DF02_PageDTO pagingParamMyLike(int page, int mno) {
        // 전체 글 갯수 조회
        int boardCount = boardRepository.boardCountMyLike(mno);
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


    // 게시글 조회수 상위 10개 목록 찾기
    public List<DF02_BoardDTO> findViewBoard10() {
        return boardRepository.findViewBoard10();
    }

    // 게시글 좋아요 등락
    public void increaseLikePoints(int bno) {
        boardRepository.increaseLikePoints(bno);
    }

    public void decreaseLikePoints(int bno) {
        boardRepository.decreaseLikePoints(bno);
    }

    // 당사자만 게시글의 수정 삭제 가능
    public boolean authorUpdateAndDeleteBoard(int bno, HttpSession session) throws Exception {

        DF01_MemberDTO loginMemberDTO = memberService.loginMemberDTO(session);
        if (loginMemberDTO != null) {
            int mno = loginMemberDTO.getMno();

            // 게시글의 작성자 MNO 가져오기
            int authorMno = findAuthorMnoByBoardBno(bno);

            // 현재 로그인한 사용자와 게시글 작성자가 동일한 경우에만 권한 부여
            return mno == authorMno;
        }

        return false;
    }
}
