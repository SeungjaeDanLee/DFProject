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

    // 회원가입시 아이디 중복 검사
    @PostMapping("/checkId")
    public @ResponseBody String checkId(@RequestParam("inputId") String inputId) {
        String checkResult = memberService.checkId(inputId);
        return checkResult;
    }

    // 회원가입시 닉네임 중복 검사
    @PostMapping("/checkNickName")
    public @ResponseBody String checkNickName(@RequestParam("inputNickName") String inputNickName) {
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
            // 세션으로 로그인 아이디를 넘겨줌
            session.setAttribute("loginId", memberDTO.getId());

            // 세션 무한대
            session.setMaxInactiveInterval(-1);
            return "redirect:/";
        } else {
            return "redirect:/members/login";
        }
    }

    @GetMapping("/my")
    public String myPage() {
        return "DF01_member/DF0103_mypage";
    }

    // 회원 상세 정보
    @GetMapping
    public String memberDetail(HttpSession session) {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든 정보 가져오기
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        if (memberDTO != null) {
            // mno를 이용하여 회원 상세 정보 가져오기
            memberService.member_detail(memberDTO);
        } else {
            // 아이디에 해당하는 회원이 없는 경우 처리
            // 예: 유효하지 않은 세션 상태에 대한 오류 처리
        }

        return "DF01_member/DF0103_mypage";
    }

//    @GetMapping
//    public String findbyMno(@RequestParam("mno") int mno, Model model) {
//        DF01_MemberDTO memberDTO = memberService.findbyMno(mno);
//        model.addAttribute("member", memberDTO);
//        return "DF01_member/DF0103_mypage";
//    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginId");
        return "redirect:/";
    }


    // 회원 탈퇴
    @GetMapping("/delete")
    public String memberDelete(HttpSession session) {
        // 세션에서 아이디 가져오기
        String loginId = (String) session.getAttribute("loginId");

        // 아이디로 데이터베이스에서 모든정보 조회
        DF01_MemberDTO memberDTO = memberService.findByLoginId(loginId);

        if (memberDTO != null) {
            // mno를 이용하여 회원 탈퇴 수행
            memberService.member_delete(memberDTO.getMno());
        } else {
            // 아이디에 해당하는 회원이 없는 경우 처리
            // 예: 유효하지 않은 세션 상태에 대한 오류 처리
        }

        // 세션에서 아이디 제거
        session.removeAttribute("loginId");
        return "redirect:/";
    }

}
