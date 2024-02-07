package com.example.controller;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF02_BoardDTO;
import com.example.dto.DF02_PageDTO;
import com.example.dto.DF03_ReplyDTO;
import com.example.service.DF01_MemberService;
import com.example.service.DF02_BoardService;
import com.example.service.DF03_ReplyService;
import com.example.service.DF04_LikePointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class DF02_BoardController {
    @Autowired
    DF01_MemberService memberService;

    @Autowired
    DF02_BoardService boardService;

    @Autowired
    DF03_ReplyService replyService;

    @Autowired
    DF04_LikePointService likePointService;

    private int loginMno(HttpSession session) throws Exception {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");
        // 아이디로 데이터베이스에서 모든정보 조회
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);
        return memberDTO.getMno();
    }

    // 게시글 작성
    @GetMapping("/write")
    public String writeBoardPage() {
        return "DF02_board/DF0201_boardWrite";
    }

    @PostMapping("/write")
    public String writeBoard(@ModelAttribute DF02_BoardDTO boardDTO, HttpSession session) throws Exception {

        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든정보 조회
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);
        if (memberDTO != null) {
            int mno = memberDTO.getMno();
            boardDTO.setMno(mno);
            boardService.write_board(boardDTO);
        } else {
            // 아이디에 해당하는 회원이 없는 경우 처리
            // 예: 유효하지 않은 세션 상태에 대한 오류 처리
        }
        return "redirect:/board/paging";
    }

    // 이미지 보기
    @ResponseBody
    @GetMapping({"/display"})
    public ResponseEntity<byte[]> showImageGET(@RequestParam("fileName") String fileName,
                                               @RequestParam("datePath") String datePath) {
        // 톰캣 내부 저장경로 (*윈도우)
        String uploadFolder = "C:\\Users\\Epcot\\Desktop\\DFProject-0129\\apache-tomcat-9.0.84\\webapps\\upload";

        File file = new File(uploadFolder + File.separator + datePath + File.separator + fileName);
        if (file.exists()){
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-type", Files.probeContentType(file.toPath()));
                byte[] fileBytes = FileCopyUtils.copyToByteArray(file);
                return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
            } catch (IOException e) {
                e.printStackTrace();
//                logger.info("[ 이미지 보기 ] Fail :: "+fileName+" File IOException Error");
            }
        }
        return ResponseEntity.notFound().build();
    }


    // 상세 페이지
    @GetMapping
    public String findByBoardBno(@RequestParam("bno") int bno,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 Model model, HttpSession session) throws Exception {

        if (session.getAttribute("loginId") == null) {
            return "redirect:/error/401";
        }

        boardService.view_counts(bno);

        DF02_BoardDTO boardDTO = boardService.findByBoardBno(bno);
        boolean isBoardAuthor = authorUpdateAndDeleteBoard(bno, session);

        model.addAttribute("board", boardDTO);
        model.addAttribute("page", page);
        model.addAttribute("isBoardAuthor", isBoardAuthor);

        List<DF01_MemberDTO> memberDTOList = memberService.findAll();
        List<DF03_ReplyDTO> replyDTOList = replyService.findAllReply(bno);

        model.addAttribute("memberList", memberDTOList);
        model.addAttribute("replyList", replyDTOList);

//        // 세션에서 아이디 가져오기
//        String loginId = (String) session.getAttribute("loginId");
//
//        // 아이디로 데이터베이스에서 모든 정보 조회
//        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);
//
//        int loginMno = memberDTO.getMno();

        model.addAttribute("loginMno", loginMno(session));

        boolean isLiked = likePointService.isLiked(bno, loginMno(session));
        model.addAttribute("isLiked", isLiked);

        return "DF02_board/DF0202_boardView";
    }

    // 상세 페이지
//    @GetMapping
//    public String findByBoardBno(@RequestParam("bno") int bno,
//                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
//                                 Model model) {
//
//        boardService.view_counts(bno);
//        DF02_BoardDTO boardDTO = boardService.findByBoardBno(bno);
//        boolean isBoardAuthor = false; // 세션 없이도 볼 수 있으므로 작성자 여부는 항상 false로 설정
//
//        model.addAttribute("board", boardDTO);
//        model.addAttribute("page", page);
//        model.addAttribute("isBoardAuthor", isBoardAuthor);
//
//        List<DF01_MemberDTO> memberDTOList = memberService.findAll();
//        List<DF03_ReplyDTO> replyDTOList = replyService.findAllReply(bno);
//
//        model.addAttribute("memberList", memberDTOList);
//        model.addAttribute("replyList", replyDTOList);
//
//        return "DF02_board/DF0202_viewBoard";
//    }


    // 게시글 수정
    @GetMapping("/update")
    public String updatePage(@RequestParam("bno") int bno, Model model, HttpSession session) throws Exception {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 조회
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        if (memberDTO == null) {
            // 작성자가 아닌 경우, 권한 없음 메시지를 뷰로 전달하거나 리디렉션
            model.addAttribute("message", "수정 권한이 없습니다.");
            return "redirect:/board?bno=" + bno; // 권한 없음을 알리는 뷰 페이지
        }

        int memberLevel = memberDTO.getMember_level();

        if (authorUpdateAndDeleteBoard(bno, session) || memberLevel == 0) {
            // 작성자인 경우, 수정 페이지로 이동
            DF02_BoardDTO boardDTO = boardService.findByBoardBno(bno);
            model.addAttribute("board", boardDTO);
            return "DF02_board/DF0205_boardUpdate";
        } else {
            // 작성자가 아닌 경우, 권한 없음 메시지를 뷰로 전달하거나 리디렉션
            model.addAttribute("message", "수정 권한이 없습니다.");
            return "redirect:/board?bno=" + bno; // 권한 없음을 알리는 뷰 페이지
        }


    }

    @PostMapping("/update")
    public String updateBoard(@ModelAttribute DF02_BoardDTO boardDTO, @RequestParam("bno") int bno, Model model) {
        boardService.update_board(boardDTO);
        DF02_BoardDTO dto = boardService.findByBoardBno(bno);
        model.addAttribute("board", dto);

        return "redirect:/board?bno=" + bno;
    }


    // 게시글 삭제
    @GetMapping("/delete")
    public String deleteBoard(@RequestParam("bno") int bno, HttpSession session) throws Exception {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 조회
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);
        if (memberDTO != null) {
            int mno = memberDTO.getMno();

            // 게시글의 작성자 MNO 가져오기
            int boardAuthorMno = boardService.findAuthorMnoByBoardBno(bno);

            int memberLevel = memberDTO.getMember_level();

            // 현재 로그인한 사용자와 게시글 작성자가 동일한 경우에만 삭제 수행
            if (mno == boardAuthorMno) {
                boardService.delete_board(bno);
                return "redirect:/board/paging";
            } else if (memberLevel == 0) {
                boardService.delete_board(bno);
                return "redirect:/admin/boardManagement";
            } else {
                // 다른 사용자의 글을 삭제하려는 경우에 대한 처리
                // 예: 권한이 없는 상태에 대한 오류 처리
                return "redirect:/board/bno=" + bno;
            }
        } else {
            // 아이디에 해당하는 회원이 없는 경우 처리
            // 예: 유효하지 않은 세션 상태에 대한 오류 처리
        }
        return "";
    }


    // 전체 게시글 목록
//    @GetMapping("/boardList")
//    public String boardListPage(Model model) {
//        List<DF02_BoardDTO> boardDTOList = boardService.findAllBoard();
//        model.addAttribute("boardList", boardDTOList);
//        return "DF02_board/DF0203_boardList";
//    }

    // 페이지 보여주기
    // /board/paging?page=2
    // 처음 페이지 요청은 1페이지를 보여줌
    // 전체 게시판
    @GetMapping("/paging")
    public String paging(Model model, HttpSession session,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        if (session.getAttribute("loginId") == null) {
            return "redirect:/error/401";
        }

        // 해당 페이지에서 보여줄 글 목록
        List<DF01_MemberDTO> memberDTOList = memberService.findAll();
        List<DF02_BoardDTO> pagingList = boardService.pagingList(page);
        DF02_PageDTO pageDTO = boardService.pagingParam(page);

        model.addAttribute("memberList", memberDTOList);
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);
        return "DF02_board/DF0204_boardPaging";
    }

    // 자유 게시판
    @GetMapping("/paging/free")
    public String pagingFree(Model model, HttpSession session,
                             @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        if (session.getAttribute("loginId") == null) {
            return "redirect:/error/401";
        }

        // 해당 페이지에서 보여줄 글 목록
        List<DF01_MemberDTO> memberDTOList = memberService.findAll();
        List<DF02_BoardDTO> pagingList = boardService.pagingListFree(page);
        DF02_PageDTO pageDTO = boardService.pagingParamFree(page);

        model.addAttribute("memberList", memberDTOList);
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);
        return "DF02_board/DF0204_boardPagingFree";
    }

    // 정보 게시판
    @GetMapping("/paging/info")
    public String pagingInfo(Model model, HttpSession session,
                             @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        if (session.getAttribute("loginId") == null) {
            return "redirect:/error/401";
        }

        // 해당 페이지에서 보여줄 글 목록
        List<DF01_MemberDTO> memberDTOList = memberService.findAll();
        List<DF02_BoardDTO> pagingList = boardService.pagingListInfo(page);
        DF02_PageDTO pageDTO = boardService.pagingParamInfo(page);

        model.addAttribute("memberList", memberDTOList);
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);
        return "DF02_board/DF0204_boardPagingInfo";
    }

    // 공지 게시판
    @GetMapping("/paging/noti")
    public String pagingNoti(Model model, HttpSession session,
                             @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        if (session.getAttribute("loginId") == null) {
            return "redirect:/error/401";
        }

        // 해당 페이지에서 보여줄 글 목록
        List<DF01_MemberDTO> memberDTOList = memberService.findAll();
        List<DF02_BoardDTO> pagingList = boardService.pagingListNoti(page);
        DF02_PageDTO pageDTO = boardService.pagingParamNoti(page);

        model.addAttribute("memberList", memberDTOList);
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);
        return "DF02_board/DF0204_boardPagingNoti";
    }


    // 마이 페이지와 관련된 것들
    // 해당 회원이 쓴 게시글
    @GetMapping("/paging/myBoard")
    public String memberBoard(Model model,
                              @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                              HttpSession session) throws Exception {

        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 가져오기
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        if (memberDTO != null) {
            // 세션에서 mno 가져오기
            int mno = memberDTO.getMno();

            List<DF02_BoardDTO> pagingList = boardService.pagingListMyBoard(page, mno);
            DF02_PageDTO pageDTO = boardService.pagingParamMyBoard(page, mno);

            model.addAttribute("member", memberDTO);
            model.addAttribute("boardList", pagingList);
            model.addAttribute("paging", pageDTO);

            return "DF01_member/DF0105_memberMyBoard";
        } else {
            return "members/login";
        }
    }

    // 해당 회원이 좋아하는 게시글
    @GetMapping("/paging/myLike")
    public String memberLike(Model model,
                             @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                             HttpSession session) throws Exception {

        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 가져오기
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        if (memberDTO != null) {
            // 세션에서 mno 가져오기
            int mno = memberDTO.getMno();

            List<DF02_BoardDTO> pagingList = boardService.pagingListMyLike(page, mno);
            DF02_PageDTO pageDTO = boardService.pagingParamMyLike(page, mno);

            model.addAttribute("member", memberDTO);
            model.addAttribute("boardList", pagingList);
            model.addAttribute("paging", pageDTO);

            return "DF01_member/DF0106_memberMyLike";
        } else {
            return "members/login";
        }
    }

    // 전체 게시판 검색 기능
//    @GetMapping("/entireBoardSearch")
//    public String entireBoardSearch(){
//
//    }

    // 당사자만 게시글의 수정 삭제 가능
    public boolean authorUpdateAndDeleteBoard(int bno, HttpSession session) throws Exception {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);
        if (memberDTO != null) {
            int mno = memberDTO.getMno();

            // 게시글의 작성자 MNO 가져오기
            int authorMno = boardService.findAuthorMnoByBoardBno(bno);

            // 현재 로그인한 사용자와 게시글 작성자가 동일한 경우에만 권한 부여
            return mno == authorMno;
        }

        return false;
    }
}
