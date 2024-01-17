package com.example.controller;

import com.example.dto.DF01_MemberDTO;
import com.example.service.DF01_MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class DF01_MemberController {
    @Autowired
    DF01_MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    // 회원가입
    @GetMapping("/new")
    public String joinPage() {
        return "DF01_member/DF0101_join";
    }

    @PostMapping("/new")
    public String memberJoin(@ModelAttribute DF01_MemberDTO memberDTO) {

        memberService.member_join(memberDTO);
        return "redirect:/members/login";
    }

    @PostMapping("/checkId")
    public @ResponseBody String checkId(@RequestParam("inputId") String inputId) {
        System.out.println("inputId = " + inputId);
        String checkResult = memberService.checkId(inputId);
        return checkResult;
    }

    @PostMapping("/checkNickName")
    public @ResponseBody String checkNickName(@RequestParam("inputNickName") String inputNickName) {
        System.out.println("inputNickName = " + inputNickName);
        String checkResult = memberService.checkNickName(inputNickName);
        return checkResult;
    }


    // 로그인
    @GetMapping("/login")
    public String loginPage() {
        return "DF01_member/DF0102_login";
    }

    @PostMapping("/login")
    public String memberLogin(@ModelAttribute DF01_MemberDTO memberDTO, HttpSession session) {
        boolean loginResult = memberService.member_login(memberDTO);
        if (loginResult) {
            session.setAttribute("loginId", memberDTO.getId());
            session.setMaxInactiveInterval(-1);
            return "redirect:/";
        } else {
            return "redirect:/members/login";
        }
    }

    // 마이 페이지
    @GetMapping("/my")
    public String myPage() {
        return "DF01_member/DF0103_mypage";
    }

    @GetMapping
    public String findbyMno(@RequestParam("mno") int mno, Model model) {
        DF01_MemberDTO memberDTO = memberService.findbyMno(mno);
        model.addAttribute("member", memberDTO);
        return "DF01_member/DF0103_mypage";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginId");
        return "redirect:/";
    }

    // 회원 탈퇴
    @GetMapping("/delete")
    public String memberDelete(@RequestParam("mno") int mno){
        memberService.member_delete(mno);
        return "redirect:/";
    }
}
