package com.example.controller;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF02_BoardDTO;
import com.example.service.DF01_MemberService;
import com.example.service.DF02_BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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



        boardService.write_board(boardDTO);

        return "redirect:/";
    }

    @GetMapping("/view")
    public String viewBoardPage(){
        return "DF02_board/DF0202_viewBoard";
    }

    @GetMapping("/boardList")
    public String boardListPage(){
        return "DF02_board/DF0203_boardList";
    }
}
