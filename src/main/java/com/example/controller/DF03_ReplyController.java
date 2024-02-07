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
    public @ResponseBody List<DF03_ReplyDTO> writeReply(@ModelAttribute DF03_ReplyDTO replyDTO, @RequestParam("bno") int bno, HttpSession session) throws Exception {

        replyDTO.setMno(memberService.loginMno(session));
        replyDTO.setBno(bno);
        replyService.writeReply(replyDTO);

        // 해당 게시글에 작성된 댓글 리스트를 가져옴
        return replyService.findAllReply(replyDTO.getBno());
    }


    // 해당 게시물의 댓글 목록 가져오기
    @GetMapping("/{bno}")
    public @ResponseBody List<DF03_ReplyDTO> getReplyList(@PathVariable("bno") int bno, Model model) {
        // 해당 게시글에 작성된 모든 댓글을 가져옴
        return replyService.findAllReply(bno);
    }

    // 댓글 수정
    @PostMapping("/update")
    public String updateReply(@ModelAttribute DF03_ReplyDTO replyDTO, @RequestParam("rno") int rno, HttpSession session, Model model) throws Exception {

        DF01_MemberDTO loginMemberDTO = memberService.loginMemberDTO(session);

        if (loginMemberDTO != null) {
            int mno = loginMemberDTO.getMno();
            int memberLevel = loginMemberDTO.getMember_level();

            // 댓글 작성자의 MNO 가져오기
            int replyAuthorMno = replyService.findAuthorMnoByReplyRno(rno);

            // 현재 로그인한 사용자와 게시글 작성자가 동일한 경우에만 수정 수행
            if (mno == replyAuthorMno || memberLevel == 0) {
                replyService.update_reply(replyDTO);
                DF03_ReplyDTO MyReplyDTO = replyService.findByReplyRno(rno);
                model.addAttribute("reply", MyReplyDTO);
            } else {
                // 다른 사용자의 글을 수정하려는 경우에 대한 처리
                // 예: 권한이 없는 상태에 대한 오류 처리
                return "redirect:/error/403";
            }
        } else {
            // 아이디에 해당하는 회원이 없는 경우 처리
            // 예: 유효하지 않은 세션 상태에 대한 오류 처리
            return "redirect:/members/login";
        }

        return "redirect:/board/paging";
    }


    // 댓글 삭제
    @GetMapping("/delete")
    public String deleteReply(@RequestParam("rno") int rno, @RequestParam("bno") int bno, HttpSession session) throws Exception {

        DF01_MemberDTO loginMemberDTO = memberService.loginMemberDTO(session);

        if (loginMemberDTO != null) {
            int mno = loginMemberDTO.getMno();

            int memberLevel = loginMemberDTO.getMember_level();

            // 댓글 작성자의 MNO 가져오기
            int replyAuthorMno = replyService.findAuthorMnoByReplyRno(rno);

            // 현재 로그인한 사용자와 게시글 작성자가 동일한 경우에만 삭제 수행
            if (mno == replyAuthorMno || memberLevel == 0) {
                replyService.delete_reply(rno);
            } else {
                // 다른 사용자의 글을 삭제하려는 경우에 대한 처리
                // 예: 권한이 없는 상태에 대한 오류 처리
                return "redirect:/error/403";
            }
        } else {
            // 아이디에 해당하는 회원이 없는 경우 처리
            // 예: 유효하지 않은 세션 상태에 대한 오류 처리
            return "redirect:/members/login";
        }

        return "redirect:/board?bno=" + bno;
    }
}
