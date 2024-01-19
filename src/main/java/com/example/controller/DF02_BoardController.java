package com.example.controller;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF02_BoardDTO;
import com.example.dto.DF02_PageDTO;
import com.example.service.DF01_MemberService;
import com.example.service.DF02_BoardService;
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

    @GetMapping("/write")
    public String writeBoardPage(){
        return "DF02_board/DF0201_wirteBoard";
    }

    @PostMapping("/write")
    public String writeBoard(@ModelAttribute DF02_BoardDTO boardDTO, HttpSession session){

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
        return "redirect:/";
    }

    @GetMapping("/view")
    public String viewBoardPage(){
        return "DF02_board/DF0202_viewBoard";
    }


    // 전체 게시글 목록
    @GetMapping("/boardList")
    public String boardListPage(Model model) {
        List<DF02_BoardDTO> boardDTOList = boardService.findAllBoard();
        model.addAttribute("boardList", boardDTOList);
        return "DF02_board/DF0203_boardList";
    }

    // 페이지 보여주기
    // /board/paging?page=2
    // 처음 페이지 요청은 1페이지를 보여줌
    @GetMapping("/paging")
    public String paging(Model model,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        System.out.println("page = " + page);
        // 해당 페이지에서 보여줄 글 목록
        List<DF02_BoardDTO> pagingList = boardService.pagingList(page);
        System.out.println("pagingList = " + pagingList);
        DF02_PageDTO pageDTO = boardService.pagingParam(page);
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);
        return "paging";
    }

    // 상세 페이지
    @GetMapping
    public String findByBoardId(@RequestParam("bno") int bno,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           Model model) {
        boardService.view_counts(bno);
        DF02_BoardDTO boardDTO = boardService.findByBoardBno(bno);
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", page);
//        List<CommentDTO> commentDTOList = commentService.findAll(bno);
//        model.addAttribute("commentList", commentDTOList);
        return "DF02_board/DF0202_viewBoard";
    }

//    @GetMapping("/delete")
//    public String delete(@RequestParam("id") Long id) {
//        boardService.delete(id);
//        return "redirect:/board/";
//    }
//
//    @GetMapping("/update")
//    public String updateForm(@RequestParam("id") Long id, Model model) {
//        DF02_BoardDTO boardDTO = boardService.findById(id);
//        model.addAttribute("board", boardDTO);
//        return "update";
//    }
//
//    @PostMapping("/update")
//    public String update(@ModelAttribute DF02_BoardDTO boardDTO, Model model) {
//        boardService.update(boardDTO);
//        DF02_BoardDTO dto = boardService.findById(boardDTO.getId());
//        model.addAttribute("board", dto);
//        return "detail";
////        return "redirect:/board?id="+boardDTO.getId();
//    }
//

}
