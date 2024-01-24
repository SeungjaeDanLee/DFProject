package com.example.controller;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF02_BoardDTO;
import com.example.dto.DF02_PageDTO;
import com.example.dto.DF03_ReplyDTO;
import com.example.service.DF01_MemberService;
import com.example.service.DF02_BoardService;
import com.example.service.DF03_ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    // 게시글 작성
    @GetMapping("/write")
    public String writeBoardPage() {
        return "DF02_board/DF0201_wirteBoard";
    }

    @PostMapping("/write")
    public String writeBoard(@ModelAttribute DF02_BoardDTO boardDTO, HttpSession session) {

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


    // 상세 페이지
    @GetMapping
    public String findByBoardBno(@RequestParam("bno") int bno,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 Model model, HttpSession session) {

        boardService.view_counts(bno);
        DF02_BoardDTO boardDTO = boardService.findByBoardBno(bno);
        boolean isBoardAuthor = authorUpdateAndDeleteBoard(bno, session);

        model.addAttribute("board", boardDTO);
        model.addAttribute("page", page);
        model.addAttribute("isBoardAuthor", isBoardAuthor);

        List<DF03_ReplyDTO> replyDTOList = replyService.findAllReply(bno);
        model.addAttribute("replyList", replyDTOList);


//        boolean isReplyAuthor = authorUpdateAndDeleteAllReply(replyDTOList, session);
//        model.addAttribute("isReplyAuthor", isReplyAuthor);


        return "DF02_board/DF0202_viewBoard";
    }


    // 게시글 수정
    @GetMapping("/update")
    public String updatePage(@RequestParam("bno") int bno, Model model, HttpSession session) {
        if (!authorUpdateAndDeleteBoard(bno, session)) {
            // 작성자가 아닌 경우, 권한 없음 메시지를 뷰로 전달하거나 리디렉션
            model.addAttribute("message", "수정 권한이 없습니다.");
            return "DF00_error/DF0001_error"; // 권한 없음을 알리는 뷰 페이지
        }

        // 작성자인 경우, 수정 페이지로 이동
        DF02_BoardDTO boardDTO = boardService.findByBoardBno(bno);
        model.addAttribute("board", boardDTO);
        return "DF02_board/DF0205_updateBoard";
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
    public String deleteBoard(@RequestParam("bno") int bno, HttpSession session) {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 조회
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);
        if (memberDTO != null) {
            int mno = memberDTO.getMno();

            // 게시글의 작성자 MNO 가져오기
            int boardAuthorMno = boardService.findAuthorMnoByBoardBno(bno);

            // 현재 로그인한 사용자와 게시글 작성자가 동일한 경우에만 삭제 수행
            if (mno == boardAuthorMno) {
                boardService.delete_board(bno);
            } else {
                // 다른 사용자의 글을 삭제하려는 경우에 대한 처리
                // 예: 권한이 없는 상태에 대한 오류 처리
            }
        } else {
            // 아이디에 해당하는 회원이 없는 경우 처리
            // 예: 유효하지 않은 세션 상태에 대한 오류 처리
        }

        return "redirect:/board/paging";
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
    @GetMapping("/paging")
    public String paging(Model model,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        // 해당 페이지에서 보여줄 글 목록
        List<DF02_BoardDTO> pagingList = boardService.pagingList(page);
        DF02_PageDTO pageDTO = boardService.pagingParam(page);
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);
        return "DF02_board/DF0204_boardPaging";
    }



    // 당사자만 게시글의 수정 삭제 가능
    public boolean authorUpdateAndDeleteBoard(int bno, HttpSession session) {
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

    // 당사자만 댓글의 수정 삭제 가능
    public boolean authorUpdateAndDeleteReply(int rno, HttpSession session) {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);
        if (memberDTO != null) {
            int mno = memberDTO.getMno();

            // 게시글의 작성자 MNO 가져오기
            int authorMno = replyService.findAuthorMnoByReplyRno(rno);

            // 현재 로그인한 사용자와 게시글 작성자가 동일한 경우에만 권한 부여
            return mno == authorMno;
        }

        return false;
    }
}
