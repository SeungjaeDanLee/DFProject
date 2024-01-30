package com.example.controller.DF00;

import com.example.dto.DF01_MemberDTO;
import com.example.dto.DF02_BoardDTO;
import com.example.dto.DF03_ReplyDTO;
import com.example.service.DF01_MemberService;
import com.example.service.DF02_BoardService;
import com.example.service.DF03_ReplyService;
import com.example.util.AES256Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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

    // AES256Util 의존성 주입
    @Autowired
    AES256Util aes256Util;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/home")
    public String adminHomePage(HttpSession session) {
        // 로그인 멤버의 member_level 확인
        boolean isAdmin = loginMember(session);

        // member_level이 0인 경우에는 admin home 페이지로 이동
        if (isAdmin) {
            logger.info("test");
            return "DF00_admin/DF0000_adminHome";
        } else {
            // member_level이 0이 아닌 경우에는 에러 페이지로 이동
            return "redirect:/error/403";
        }
    }

    @GetMapping("/memberManagement")
    public String adminMemberManagementPage(Model model, HttpSession session) {
        boolean isAdmin = loginMember(session);
        if (isAdmin) {
            List<DF01_MemberDTO> memberDTOList = memberService.findAll();

            // 회원 목록을 순회하면서 이메일과 폰 번호를 복호화하여 추가
            for (DF01_MemberDTO memberDTO : memberDTOList) {
                String decodedEmail = null;
                String decodedPhone = null;
                try {
                    // 이메일 복호화
                    decodedEmail = AES256Util.decrypt(memberDTO.getEmail());
                    // 폰 번호 복호화
                    decodedPhone = AES256Util.decrypt(memberDTO.getPhone());
                } catch (Exception e) {
                    // 복호화 실패 처리
                    e.printStackTrace();
                }

                // 복호화된 값들을 모델에 추가
                memberDTO.setEmail(decodedEmail);
                memberDTO.setPhone(decodedPhone);
            }

            model.addAttribute("memberList", memberDTOList);
            return "DF00_admin/DF0001_memberManagement";
        } else {
            // member_level이 0이 아닌 경우에는 에러 페이지로 이동
            return "redirect:/error/403";
        }
    }

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
    public String adminMemberDelete(@RequestParam("mno") int mno) {
        memberService.member_delete(mno);
        return "redirect:/admin/memberManagement";
    }

    @GetMapping("/boardManagement")
    public String adminBoardManagementPage(Model model, HttpSession session) {
        boolean isAdmin = loginMember(session);

        // member_level이 0인 경우에는 admin home 페이지로 이동
        if (isAdmin) {
            List<DF01_MemberDTO> memberDTOList = memberService.findAll();
            List<DF02_BoardDTO> boardDTOList = boardService.findAll();

            model.addAttribute("memberList", memberDTOList);
            model.addAttribute("boardList", boardDTOList);
            return "DF00_admin/DF0002_boardManagement";
        } else {
            // member_level이 0이 아닌 경우에는 에러 페이지로 이동
            return "redirect:/error/403";
        }
    }

    @GetMapping("/boardManagement/delete")
    public String adminBoardDelete(@RequestParam("bno") int bno) {
        boardService.delete_board(bno);
        return "redirect:/admin/boardManagement";
    }


    @GetMapping("/replyManagement")
    public String adminReplyManagementPage(Model model, HttpSession session) {
        boolean isAdmin = loginMember(session);

        // member_level이 0인 경우에는 admin home 페이지로 이동
        if (isAdmin) {
            List<DF01_MemberDTO> memberDTOList = memberService.findAll();
            List<DF02_BoardDTO> boardDTOList = boardService.findAll();
            List<DF03_ReplyDTO> replyDTOList = replyService.findAll();

            model.addAttribute("memberList", memberDTOList);
            model.addAttribute("boardList", boardDTOList);
            model.addAttribute("replyList", replyDTOList);

            return "DF00_admin/DF0003_replyManagement";
        } else {
            // member_level이 0이 아닌 경우에는 에러 페이지로 이동
            return "redirect:/error/403";
        }
    }

    @GetMapping("/replyManagement/delete")
    public String adminReplyDelete(@RequestParam("rno") int rno) {
        replyService.delete_reply(rno);
        return "redirect:/admin/replyManagement";
    }

    // 세션에서 로그인한 멤버의 loginMemberLevel 가져오기
    private boolean loginMember(HttpSession session) {
        // 세션에 저장된 로그인 멤버의 member_level 값 가져오기
        Integer memberLevel = (Integer) session.getAttribute("loginMemberLevel");

        // member_level 값이 null이거나 0이 아닌 경우에는 false를 반환하여 허용하지 않음
        return memberLevel != null && memberLevel == 0;
    }
}