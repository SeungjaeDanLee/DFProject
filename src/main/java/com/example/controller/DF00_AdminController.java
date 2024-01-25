package com.example.controller;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF02_BoardDTO;
import com.example.dto.DF03_ReplyDTO;
import com.example.service.DF01_MemberService;
import com.example.service.DF02_BoardService;
import com.example.service.DF03_ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class DF00_AdminController {

    @Autowired
    DF01_MemberService memberService;

    @Autowired
    DF02_BoardService boardService;

    @Autowired
    DF03_ReplyService replyService;

    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    @GetMapping("/home")
    public String adminHomePage(){
        logger.info("test");

        return "DF00_admin/DF0000_adminHome";
    }

    @GetMapping("/memberManagement")
    public String adminMemberManagementPage(Model model){
        List<DF01_MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "DF00_admin/DF0001_memberManagement";
    }

//    @PostMapping("/memberManagement/update")
//    public String adminMemberUpdate(@RequestParam("mno") DF01_MemberDTO memberDTO){
//        memberService.member_update(memberDTO);
//        return "redirect:/admin/memberManagement";
//    }

    @GetMapping("/memberManagement/update")
    public String adminMemberUpdate(@RequestParam("mno") int mno,
                                    @RequestParam("member_level") int member_level) {
        logger.info("mno" + mno);
        logger.info("member_level" + member_level);

        // mno와 member_level을 사용하여 memberDTO를 생성 또는 조회
        DF01_MemberDTO memberDTO = memberService.findByMno(mno);
        memberDTO.setMember_level(member_level);
        memberService.member_update(memberDTO);
        return "redirect:/admin/memberManagement";
    }




    @GetMapping("/memberManagement/delete")
    public String adminMemberDelete(@RequestParam("mno") int mno){
        memberService.member_delete(mno);
        return "redirect:/admin/memberManagement";
    }

    @GetMapping("/boardManagement")
    public String adminBoardManagementPage(Model model){
        List<DF01_MemberDTO> memberDTOList = memberService.findAll();
        List<DF02_BoardDTO> boardDTOList = boardService.findAll();

        model.addAttribute("memberList", memberDTOList);
        model.addAttribute("boardList", boardDTOList);
        return "DF00_admin/DF0002_boardManagement";
    }

    @GetMapping("/boardManagement/delete")
    public String adminBoardDelete(@RequestParam("bno") int bno){
        boardService.delete_board(bno);
        return "redirect:/admin/boardManagement";
    }


    @GetMapping("/replyManagement")
    public String adminReplyManagementPage(Model model){
        List<DF01_MemberDTO> memberDTOList = memberService.findAll();
        List<DF02_BoardDTO> boardDTOList = boardService.findAll();
        List<DF03_ReplyDTO> replyDTOList = replyService.findAll();

        model.addAttribute("memberList", memberDTOList);
        model.addAttribute("boardList", boardDTOList);
        model.addAttribute("replyList", replyDTOList);

        return "DF00_admin/DF0003_replyManagement";
    }

    @GetMapping("/replyManagement/delete")
    public String adminReplyDelete(@RequestParam("rno") int rno){
        replyService.delete_reply(rno);
        return "redirect:/admin/replyManagement";
    }




//    @GetMapping("/replyManagement")
//    public String adminReplyManagementPage(Model model){
//        List<DF02_BoardDTO> boardDTOList = boardService.findAll();
//        List<DF03_ReplyDTO> replyDTOList = replyService.findAll();
//
//        model.addAttribute("boardList", boardDTOList);
//        model.addAttribute("replyList", replyDTOList);
//
//        return "DF00_admin/DF0003_replyManagement";
//    }
}
