package com.example.controller;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF02_BoardDTO;
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
@RequiredArgsConstructor
@RequestMapping("/reply")
public class DF03_ReplyController {

    @Autowired
    DF01_MemberService memberService;

    @Autowired
    DF02_BoardService boardService;

    @Autowired
    DF03_ReplyService replyService;


    // 댓글 쓰기
    // ResponseBody는 json 형식으로 데이터를 돌려받는다.
    @PostMapping("/write")
    public @ResponseBody List<DF03_ReplyDTO> writeReply(@ModelAttribute DF03_ReplyDTO replyDTO,
                                                        @RequestParam("bno") int bno,
                                                        HttpSession session) {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든정보 조회
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        replyDTO.setMno(memberDTO.getMno());
        replyDTO.setBno(bno);
        replyService.replyDTO(replyDTO);

        // 해당 게시글에 작성된 댓글 리스트를 가져옴
        return replyService.findAllReply(replyDTO.getBno());
    }


    // 해당 게시물의 댓글 목록 가져오기
    @GetMapping("/{bno}")
    public @ResponseBody List<DF03_ReplyDTO> getReplyList(@PathVariable("bno") int bno) {
        // 해당 게시글에 작성된 모든 댓글을 가져옴
        return replyService.findAllReply(bno);
    }

    

}
